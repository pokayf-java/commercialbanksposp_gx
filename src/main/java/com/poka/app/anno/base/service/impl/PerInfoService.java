package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.PerInfo;
import com.poka.app.anno.enity.PerInfoPK;

@Service
public class PerInfoService extends BaseService<PerInfo, PerInfoPK> {

	public int getPerinfoCount(){
		String hql = "select count(*)  from PerInfo";
		return ((Long)this.baseDao.findUnique(hql)).intValue();
	}
	
	public List<PerInfo> getPerinfoList(int firstResult,int maxResults){
		String hql = "from PerInfo";
		Query query = createQuery(hql);
		query.setMaxResults(maxResults);
		query.setFirstResult(firstResult);
		return (List<PerInfo>)query.list();
	}
	
	public List<PerInfo> getPerinfoList(){
		String hql = "from PerInfo";
		Query query = createQuery(hql);
		return (List<PerInfo>)query.list();
	}
	
	public PerInfo getPerinfo(PerInfoPK pk){
		String hql = "from PerInfo p where p.percode = :percode and p.bankno = :bankno and p.agencyno = :agencyno";
		Query query = createQuery(hql);
		query.setParameter("percode", pk.getPercode());
		query.setParameter("bankno", pk.getBankno());
		query.setParameter("agencyno", pk.getAgencyno());
		return (PerInfo)query.uniqueResult();
	}
}
