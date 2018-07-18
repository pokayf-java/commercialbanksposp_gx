package com.poka.app.cb.ws.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.AppointmentBusiness;
import com.poka.app.anno.bussiness.BagInfoBusiness;
import com.poka.app.anno.bussiness.BankAndNetRepBusiness;
import com.poka.app.anno.bussiness.MonQueryBusiness;
import com.poka.app.anno.bussiness.MonRuleBusiness;
import com.poka.app.anno.bussiness.PerInfoAndBranchInfoBusiness;
import com.poka.app.anno.enity.BagInfo;
import com.poka.app.anno.enity.BankCheckDailyRep;
import com.poka.app.anno.enity.BankCheckDailyRepList;
import com.poka.app.anno.enity.MonRule;
import com.poka.app.anno.enity.MoneyDataInfo;
import com.poka.app.anno.enity.NetCheckDailyRep;
import com.poka.app.anno.enity.NetCheckDailyRepList;
import com.poka.app.cb.ws.ICBPospSW;
import com.poka.app.vo.AppointmenResult;

@WebService(endpointInterface = "com.poka.app.cb.ws.ICBPospSW") 
public class CBPospSW implements ICBPospSW {

	private AppointmentBusiness appointmentBussiness;
	private MonRuleBusiness monRuleBussiness;
	private PerInfoAndBranchInfoBusiness perInfoAndBranchInfoBussiness;
	private BankAndNetRepBusiness bankAndNetRepBusiness;
	private MonQueryBusiness monQueryBusiness;
	private BagInfoBusiness bagInfoBusiness;
	
	@Autowired
	public void setBagInfoBusiness(BagInfoBusiness bagInfoBusiness) {
		this.bagInfoBusiness = bagInfoBusiness;
	}
	
	@Autowired
	public void setMonQueryBusiness(MonQueryBusiness monQueryBusiness) {
		this.monQueryBusiness = monQueryBusiness;
	}
	
	@Autowired
	public void setAppointmentBussiness(AppointmentBusiness appointmentBussiness) {
		this.appointmentBussiness = appointmentBussiness;
	}
	
	@Autowired
	public void setBankAndNetRepBusiness(BankAndNetRepBusiness bankAndNetRepBusiness) {
		this.bankAndNetRepBusiness = bankAndNetRepBusiness;
	}
	
	@Autowired
	public void setMonRuleBussiness(MonRuleBusiness monRuleBussiness) {
		this.monRuleBussiness = monRuleBussiness;
	}
	
	@Autowired
	public void setPerInfoAndBranchInfoBussiness(PerInfoAndBranchInfoBusiness perInfoAndBranchInfoBussiness) {
		this.perInfoAndBranchInfoBussiness = perInfoAndBranchInfoBussiness;
	}
	/*
	 * 预约请求结果处理
	 * @see com.poka.app.cb.ws.ICBPospSW#handleAppointmen(com.poka.app.vo.AppointmenResult)
	 */
	@Override
	public boolean handleAppointmen(AppointmenResult appointment) {
		return appointmentBussiness.handleAppointment(appointment);
	}
	
	/**
	 * 可疑冠字号同步处理
	 */
	@Override
	public boolean sendMonRuleData(List<MonRule> monRuleList) {
		// TODO Auto-generated method stub
		return monRuleBussiness.getMonRuleData(monRuleList);
	}

	/**
	 * 村镇银行机具同步
	 */
	@Override
	public boolean getPerInfoData(String listData) {
		// TODO Auto-generated method stub
		return perInfoAndBranchInfoBussiness.getPerInfoData(listData);
	}

	/**
	 * 村镇银行网点同步
	 */
	@Override
	public boolean getBanchInfoData(String listData) {
		// TODO Auto-generated method stub
		return perInfoAndBranchInfoBussiness.getBranchInfoData(listData);
	}

	/**
	 * 村镇银行网点日结同步（暂时不用）
	 */
	@Override
	public boolean sendNetCheckDailyRepBak(List<NetCheckDailyRep> netCheckDailyRepList) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 村镇银行银行日结同步（暂时不用）
	 */
	@Override
	public boolean sendBankCheckDailyRepBak(List<BankCheckDailyRep> bankCheckDailyRepList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendMonRuleDataForCZYH(List<MonRule> monRuleList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendNetCheckDailyRepList(List<NetCheckDailyRepList> netCheckDailyRepListFlow) {
		// TODO Auto-generated method stub
		return bankAndNetRepBusiness.getNetCheckDailyRepList(netCheckDailyRepListFlow);
	}

	@Override
	public boolean sendBankCheckDailyRepList(List<BankCheckDailyRepList> bankCheckDailyRepListFlow) {
		// TODO Auto-generated method stub
		return bankAndNetRepBusiness.getBankCheckDailyRepList(bankCheckDailyRepListFlow);
	}

	@Override
	public List<MoneyDataInfo> selectGZHData(String mon) {
		// TODO Auto-generated method stub
		return monQueryBusiness.queryGzhList(mon);
	}

	@Override
	public boolean sendBagInfoData(List<BagInfo> bagInfoList) {
		// TODO Auto-generated method stub
		return bagInfoBusiness.insertBagInfo(bagInfoList);
	}
}
