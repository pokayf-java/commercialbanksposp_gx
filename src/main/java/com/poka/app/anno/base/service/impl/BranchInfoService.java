package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BranchInfo;
import com.poka.app.anno.enity.BranchInfoPK;

@Service
public class BranchInfoService extends BaseService<BranchInfo, BranchInfoPK> {

	public int getBranchInfoCount() {
		String hql = "select count(*)  from BranchInfo";
		return ((Long) this.baseDao.findUnique(hql)).intValue();
	}

	public List<BranchInfo> getBranchInfoList(int firstResult, int maxResults) {
		String hql = "from BranchInfo ";
		Query query = createQuery(hql);
		 query.setMaxResults(maxResults);
		 query.setFirstResult(firstResult);
		return (List<BranchInfo>) query.list();
	}

	public List<BranchInfo> getBranchInfoList() {
		String hql = "from BranchInfo";
		Query query = createQuery(hql);
		return (List<BranchInfo>) query.list();
	}

	public BranchInfo getBranchInfo(BranchInfoPK pk) {
		String hql = "from BranchInfo b where b.bankno = :bankno and b.agencyno = :agencyno";
		Query query = createQuery(hql);
		query.setParameter("bankno", pk.getBankno());
		query.setParameter("agencyno", pk.getAgencyno());
		return (BranchInfo) query.uniqueResult();
	}
}
