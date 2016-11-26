package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.NetCheckDailyRepList;
import com.poka.app.anno.enity.NetCheckDailyRepListPK;

@Service
public class NetCheckDailyRepListService extends BaseService<NetCheckDailyRepList, NetCheckDailyRepListPK> {

	/**
	 * 查询网点流水信息（前一天或指定日期）
	 * 
	 * @return
	 */
	public List<NetCheckDailyRepList> getNetCheckDailyRepList(String operDate) {
		String sql = " select * from NETCHECKDAILYREP_LIST ";
		if (null == operDate || operDate.equals("")) {
//			sql = sql + "where operDate = date_sub(curdate(),interval 1 day) ";
			sql = sql + "where operDate = to_char(sysdate-1,'yyyy-MM-dd') ";
		} else {
			sql = sql + "where operDate ='" + operDate + "'";
		}
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(sql);
		query.addEntity(NetCheckDailyRepList.class);
		return (List<NetCheckDailyRepList>) query.list();
	}

	/**
	 * firstStart:true 表示首次同步 firstStart:fale 表示不是首次，然后查询前两天的日志记录
	 * 
	 * @param firstStart
	 * @return
	 */
	public List<String> getCheckDailyRepLogsList(int type, int firstFlag, String operDate) {
		String sql = " select * from CHECKDAILYREPLOGS where type = "+type;
		if (firstFlag == 1) {
//			sql = sql + " and operDate = date_sub(curdate(),interval 2 day) ";
			sql = sql + " and operDate = to_char(sysdate-1,'yyyy-MM-dd') ";
		} else if (firstFlag == 2) {
			sql = sql + " and operDate = '" + operDate + "'";
		} else {

		}
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(sql);
		return query.list();
	}

	/**
	 * 当数据传输完毕后，插入同步日志表日期。
	 * 
	 * @param type
	 */
	public void doInsertLog(int type, String operDate) {
		String querySql = " select * from CHECKDAILYREPLOGS where type=" + type;

		if (null == operDate || operDate.equals("")) {
//			querySql = querySql + " and operDate = date_sub(curdate(),interval 1 day) ";
			querySql = querySql + " and operDate = to_char(sysdate-1,'yyyy-MM-dd') ";
		} else {
			querySql = querySql + " and operDate = '" + operDate + "'";
		}
		List selectList = this.getBaseDao().getSession().createSQLQuery(querySql).list();
		if (null == selectList || selectList.size() == 0) {
			String sql = " insert into CHECKDAILYREPLOGS (operdate,type) ";
			if (null == operDate || operDate.equals("")) {
//				sql = sql + " values (date_sub(curdate(),interval 1 day)," + type + ")";
				sql = sql + " values (to_char(sysdate-1,'yyyy-MM-dd')," + type + ")";
			} else {
				sql = sql + " values ('" + operDate + "'," + type + ")";
			}
			this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
		}

	}
}
