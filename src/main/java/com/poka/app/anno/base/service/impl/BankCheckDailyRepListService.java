package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BankCheckDailyRepList;
import com.poka.app.anno.enity.BankCheckDailyRepListPK;

@Service
public class BankCheckDailyRepListService extends BaseService<BankCheckDailyRepList, BankCheckDailyRepListPK> {

	
	public List<BankCheckDailyRepList> getBankCheckDailyRepList(String operDate) {
		
		String sql = " select * from BANKCHECKDAILYREP_LIST ";
		if (null == operDate || operDate.equals("")) {
//			sql = sql + "where operDate = date_sub(curdate(),interval 1 day) ";
			sql = sql + "where operDate = to_char(sysdate-1,'yyyy-MM-dd') ";
		} else {
			sql = sql + "where operDate ='" + operDate + "'";
		}
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(sql);
		query.addEntity(BankCheckDailyRepList.class);
		return (List<BankCheckDailyRepList>) query.list();
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
