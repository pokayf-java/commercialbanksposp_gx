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

/**
 * 
 * @author enma.ai
 * @date 2017年6月22日
 * 用于退回交款操作的业务类
 * 
 */
@Component
public class CancelOrderBusiness {
	//创建全局变量
	Logger logger = Logger.getLogger(CancelOrderBusiness.class);
	private OrderInfoService orderInfoService;
	private CxfUtil cxfUtil;
	
	@Autowired
	@Qualifier("orderInfoService")
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}
	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}
	
	/**
	 * 交款退回的方法
	 * 2017年6月22日
	 * @author Enma.ai
	 * @return void
	 */
	public void cancelOrder() {
		try {
			//获取orderType=3类型的所有订单list
			List<OrderInfo>	orders = orderInfoService.getUnsendOrder(OrderType.CANCELORDER);
			if (orders.size() <= 0) {
				return;
			}
			//配置网络
			IPBPospSW ipbPospSW = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
			cxfUtil.recieveTimeOutWrapper(ipbPospSW);
			
			boolean result = Boolean.FALSE;
			result = ipbPospSW.getTransferFlag("canOrder");
			if (result) {
				logger.info("退回订单数量为:" + orders.size());
				for (OrderInfo orderInfo : orders) {
					String orderId = orderInfo.getOrderId().trim();
					logger.info("处理退回订单号:" + orderId);
					//需要处理的OrderId传输到人行
					result = ipbPospSW.cancelOrder(orderId);
					if (result) {
						logger.info("处理退回订单:" + orderId + "成功...("
								+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")"
								);
						this.orderInfoService.updateOrderInfoState(orderInfo, StateType.SENDED);
					}else {
						logger.info("处理退回订单:" + orderId + "失败...("
								+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					}
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}else {
				logger.info("人行服务器拒绝退回订单同步...");
				return;
			}
		} catch (Exception e) {
			logger.info("连接服务器失败...");
			e.printStackTrace();
		}
	}
}
