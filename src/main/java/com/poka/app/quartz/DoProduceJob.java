package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.BankAndNetRepBusiness;

/**
 * 执行日结 定时任务类
 * @author lb
 * 
 */
public class DoProduceJob {
	
	private BankAndNetRepBusiness bankAndNetRepBussiness;
	
	@Autowired
	public void setBankAndNetRepBussiness(BankAndNetRepBusiness bankAndNetRepBussiness) {
		this.bankAndNetRepBussiness = bankAndNetRepBussiness;
	}

	public void work() {
		bankAndNetRepBussiness.doProduce();
	}
	
}
