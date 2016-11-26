package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BagInfo;
import com.poka.app.anno.enity.BagInfoPK;

@Service
public class BagInfoService extends BaseService<BagInfo, BagInfoPK> {

	/**
	 * 查询是否有要同步的横向调拨信息
	 **/
	public List doSelectTRS_Order() {

		String sql = " select ORDERID from TRS_ORDER where ORDERTYPE = 1 and SENDSTATE = 0 ";
		Query query = this.getBaseDao().getSession().createSQLQuery(sql);

		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

	}

	/**
	 * 查询调拨信息
	 */
	public List<BagInfo> doSelectBagInfo(String orderId) {
		String sql = "select B.BAGCODE,A.TOBANKID BANKID,C.BAGDATE,C.BAGID BAGBAGID,"
				+ " C.CHECKID BAGCHECKID,B.BUNDLECODE,B.BUNDLEDATE" + " from TRS_ORDER A INNER JOIN TRS_BUNDLE B"
				+ " on A.ORDERID = B.ORDERID left join TRS_BAG C"
				+ " on B.ORDERID = C.ORDERID and B.BAGCODE = C.BAGCODE" + " where A.ORDERID = '" + orderId + "'";
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(sql);
		query.addEntity(BagInfo.class);
		return (List<BagInfo>) query.list();
		// return
		// query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

	}

	/**
	 * 更新订单状态、操作时间
	 * 
	 * @param orderId
	 */
	public void doUpdateOrderStatus(String orderId) {

//		String sql = "update TRS_ORDER set SENDSTATE=1,SENDDATE=now() where ORDERID = '" + orderId + "'";
		String sql = "update TRS_ORDER set SENDSTATE=1,SENDDATE=sysdate where ORDERID = '" + orderId + "'";
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(sql);
		query.executeUpdate();

	}
}
