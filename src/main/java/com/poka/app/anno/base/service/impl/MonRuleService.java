package com.poka.app.anno.base.service.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.MonRule;

@Service
public class MonRuleService extends BaseService<MonRule, String> {
	
	public List<MonRule> getMonRuleList(){
		String hql = "from MonRule";
		Query query = createQuery(hql);
		return (List<MonRule>)query.list();
	}
	
}
