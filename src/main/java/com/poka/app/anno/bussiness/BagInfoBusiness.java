package com.poka.app.anno.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 横向调拨业务处理
 */
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BagInfoService;
import com.poka.app.anno.enity.BagInfo;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;

/**
 * 横向调拨业务类
 * @author Administrator
 *
 */
@Component
public class BagInfoBusiness {

	Logger logger = Logger.getLogger(BagInfoBusiness.class);

	private BagInfoService bagInfoService;

	private CxfUtil cxfUtil;

	@Autowired
	@Qualifier("bagInfoService")
	public void setBagInfoService(BagInfoService bagInfoService) {
		this.bagInfoService = bagInfoService;
	}

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	/**
	 * 查询横向调拨信息
	 */
	public void sendBagInfo() {
		// 查询是否有订单信息
		List orderInfoList = bagInfoService.doSelectTRS_Order();
		int orderListSize = orderInfoList.size();
		if (null != orderInfoList && orderListSize > 0) {
			for (int i = 0; i < orderListSize; i++) {
				Map map = (Map) orderInfoList.get(i);
				List<BagInfo> bagInfoList = bagInfoService.doSelectBagInfo(map.get("ORDERID").toString());
				if (null != bagInfoList && bagInfoList.size() > 0) {
					handleSendBagInfoData(bagInfoList, map.get("ORDERID").toString());
				}
			}
		} else {
			logger.info("无横向调拨订单信息...");
		}

	}

	public void handleSendBagInfoData(List<BagInfo> list, String orderId) {
		try {
			IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
			cxfUtil.recieveTimeOutWrapper(service);
			boolean result = Boolean.FALSE;
			result = service.getTransferFlag("bagInfo");
			if(result){
				logger.info("横向调拨订单号：" + orderId + "，共计" + list.size() + "条");
				result = service.sendBagInfo(list);
				if (result) {
					// 同步更新状态
					bagInfoService.doUpdateOrderStatus(orderId);
					logger.info("处理横向调拨订单：" + orderId + "成功...("
							+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
				} else {
					logger.info("处理横向调拨订单：" + orderId + "失败...("
							+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
				}
			} else {
				logger.info("人行服务器拒绝横向调拨信息同步...");
				return;
			}

		} catch (Exception ex) {
			logger.info("连接服务器失败...");
			ex.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
