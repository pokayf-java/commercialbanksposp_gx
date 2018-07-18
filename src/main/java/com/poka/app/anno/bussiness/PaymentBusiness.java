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

import antlr.ASdebug.ASDebugStream;

@Component
public class PaymentBusiness {
	//创建日志对象
	Logger logger = Logger.getLogger(PaymentBusiness.class);
	
	private OrderInfoService orderInfoService;

	private CxfUtil cxfUtil;

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}
	
	@Autowired
	//@Qualifier:使用Spring框架中@Autowired标签时默认情况下使用 @Autowired 注释进行自动注入时，Spring 容器中匹配的候选 Bean 数目必须有且仅有一个
	@Qualifier("orderInfoService")
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	/**
	 * 预约交款
	 */
	public void makePayment() {
		try {
			//获取OrderType=1(交款),state=0的所有OrderInfo
			List<OrderInfo> orders = this.orderInfoService.getUnsendOrder(OrderType.PAYMENT);
			if (orders.size() <= 0) {
				return;
			}
			//得到预约交款的IPBPospSW(operationName = "makePayment")和URL
			IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
			//获取超时连接的配置
			cxfUtil.recieveTimeOutWrapper(service);
			boolean result = Boolean.FALSE;
			//获取传输指令为payment
			result = service.getTransferFlag("payment");
			if (result) {
				logger.info("未处理交款订单数量为:" + orders.size());
				for (OrderInfo order : orders) {
					//.trim():获取orderId,去掉前后空格
					String orderId = order.getOrderId().trim();
					logger.info("处理交款订单号:" + orderId);
					//根据OrderId获取PaymentVo数据
					PaymentVo vo = orderInfoService.getPaymentVo(orderId);
					if (vo == null || vo.getPayOrder() == null || vo.getPayBags() == null
							|| vo.getPayBundles() == null) {
						logger.info("continue:" + orderId);
						continue;
					}
					//result:预约交款返回的boolean
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
