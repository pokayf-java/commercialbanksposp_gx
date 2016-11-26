package com.poka.app.anno.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.OrderInfoService;
import com.poka.app.anno.enity.OrderInfo;
import com.poka.app.enumtype.OrderType;
import com.poka.app.enumtype.StateType;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;
import com.poka.app.vo.PaymentVo;

@Component
public class PaymentBusiness {
	Logger logger = Logger.getLogger(PaymentBusiness.class);
	private OrderInfoService orderInfoService;

	private CxfUtil cxfUtil;

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	@Autowired
	@Qualifier("orderInfoService")
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	/**
	 * 预约交款
	 */
	public void makePayment() {
		try {
			List<OrderInfo> orders = this.orderInfoService.getUnsendOrder(OrderType.PAYMENT);
			if (orders.size() <= 0) {
				return;
			}
			IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
			cxfUtil.recieveTimeOutWrapper(service);
			boolean result = Boolean.FALSE;
			result = service.getTransferFlag("payment");
			if (result) {
				logger.info("未处理交款订单数量为:" + orders.size());
				for (OrderInfo order : orders) {
					String orderId = order.getOrderId().trim();
					logger.info("处理交款订单号:" + orderId);
					PaymentVo vo = orderInfoService.getPaymentVo(orderId);
					if (vo == null || vo.getPayOrder() == null || vo.getPayBags() == null
							|| vo.getPayBundles() == null) {
						logger.info("continue:" + orderId);
						continue;
					}
					result = service.makePayment(vo);
					if (result) {
						logger.info("处理交款订单:" + orderId + "  成功...("
								+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
						this.orderInfoService.updateOrderInfoState(order, StateType.SENDED);
					} else {
						logger.info("处理交款订单:" + orderId + "  失败...("
								+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
					}
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			} else {
				logger.info("人行服务器拒绝预约交款信息同步...");
				return;
			}

		} catch (Exception ex) {
			logger.info("连接服务器失败...");
			ex.printStackTrace();
		}

	}

}
