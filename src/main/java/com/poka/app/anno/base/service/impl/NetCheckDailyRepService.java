package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.NetCheckDailyRep;
import com.poka.app.anno.enity.NetCheckDailyRepPK;

@Service
public class NetCheckDailyRepService extends BaseService<NetCheckDailyRep, NetCheckDailyRepPK> {
	
	public List<NetCheckDailyRep> getNetCheckDailyRepList(){
//		String hql = "select * from NETCHECKDAILYREP where checkDate = date_sub(curdate(),interval 1 day)";
		String hql = "select * from NETCHECKDAILYREP where checkDate = to_char(sysdate,'yyyy-MM-dd')";
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(hql);
		query.addEntity(NetCheckDailyRep.class);
		return (List<NetCheckDailyRep>)query.list();
	}
}
