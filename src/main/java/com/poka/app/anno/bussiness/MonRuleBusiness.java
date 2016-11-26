package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.MonRuleService;
import com.poka.app.anno.enity.MonRule;

@Component
public class MonRuleBusiness {

	Logger logger = Logger.getLogger(MonRuleBusiness.class);

	private MonRuleService monRuleService;

	@Autowired
	public void setMonRuleService(MonRuleService monRuleService) {
		this.monRuleService = monRuleService;
	}

	public boolean getMonRuleData(List<MonRule> monRuleList) {
		if (null != monRuleList && monRuleList.size() > 0) {
			for (MonRule monRule : monRuleList) {
				// 1：表示来至人行的同步可疑数据
				monRule.setSourceType("1");
				monRuleService.save(monRule);
			}
		}
		return true;
	}
}
