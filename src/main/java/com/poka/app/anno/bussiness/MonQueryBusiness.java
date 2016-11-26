package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.MoneyDataService;
import com.poka.app.anno.enity.MoneyDataInfo;

@Component
public class MonQueryBusiness {
	Logger logger = Logger.getLogger(MonQueryBusiness.class);
	private MoneyDataService moneyDataService;

	@Autowired
	public void setMoneyDataService(MoneyDataService moneyDataService) {
		this.moneyDataService = moneyDataService;
	}

	public List<MoneyDataInfo> queryGzhList(String dealNo) {
		List<MoneyDataInfo> moneyDataList;
		try {
			logger.info("查询冠字号:" + dealNo);
			if (dealNo.indexOf("*") > -1) {
				dealNo = dealNo.replace("*", "_");
				moneyDataList = moneyDataService.findMoneyDataListByLike(dealNo);
			} else {
				moneyDataList = moneyDataService.findMoneyDataList(dealNo);
			}
			logger.info("查询冠字号" + dealNo + "共" + moneyDataList.size() + "条结果！");
			if (moneyDataList != null && moneyDataList.size() > 0) {
				return moneyDataList;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
