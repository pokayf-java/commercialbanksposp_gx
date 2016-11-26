package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BankCheckDailyRep;
import com.poka.app.anno.enity.BankCheckDailyRepPK;

@Service
public class BankCheckDailyRepService extends BaseService<BankCheckDailyRep, BankCheckDailyRepPK> {

	public List<BankCheckDailyRep> getBankCheckDailyRepList() {
//		String hql = "select * from BANKCHECKDAILYREP where checkDate = date_sub(curdate(),interval 1 day)";
		String hql = "select * from BANKCHECKDAILYREP where checkDate = to_char(sysdate-1,'yyyy-MM-dd')";
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(hql);
		query.addEntity(BankCheckDailyRep.class);
		return (List<BankCheckDailyRep>) query.list();
	}

	/**
	 * 执行日结存储过程
	 */
	public void doProduce() {
		String sql = "{call P_CHECKDAYREP('')}";
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

}
