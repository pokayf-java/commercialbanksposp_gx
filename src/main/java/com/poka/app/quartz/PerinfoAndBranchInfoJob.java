package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.PerInfoAndBranchInfoBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 网点、机具信息同步更新定时任务类
 * @author Administrator
 *
 */
public class PerinfoAndBranchInfoJob {
	
	private PerInfoAndBranchInfoBusiness perInfoAndBranchInfoBussiness;
	
	@Autowired
	public void setPerInfoAndBranchInfoBussiness(PerInfoAndBranchInfoBusiness perInfoAndBranchInfoBussiness) {
		this.perInfoAndBranchInfoBussiness = perInfoAndBranchInfoBussiness;
	}

	public void work() {
		
		if(ConstantUtil.branchInfoFlag.trim().equals("Enabled")){
			perInfoAndBranchInfoBussiness.sendBranchInfo();
		}
		
		if(ConstantUtil.perInfoFlag.trim().equals("Enabled")){
			perInfoAndBranchInfoBussiness.sendPerInfo();
		}
		
	}
	
}
