package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.BagInfoBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 执行横向调拨 定时任务类
 * @author lb
 * 
 */
public class DoBagInfoJob {
	
	private BagInfoBusiness bagInfoBusiness;
	
	@Autowired
	public void setBagInfoBusiness(BagInfoBusiness bagInfoBusiness) {
		this.bagInfoBusiness = bagInfoBusiness;
	}

	public void work() {
		
		if(ConstantUtil.bagInfoFlag.equals("Enabled")){
			bagInfoBusiness.sendBagInfo();
		}
	}
	
}
