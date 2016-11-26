package com.poka.app.util;

import com.poka.app.util.ArgsConfigUtil;
import com.poka.app.util.ConstantUtil;
/**
 * 初始化常量
 * @author Administrator
 *
 */
public class InitConstant {
	private static ArgsConfigUtil argsConfigUtil = null;
	static {
		if (argsConfigUtil == null) {
			argsConfigUtil = new ArgsConfigUtil();
			ConstantUtil.appointmentFlag = argsConfigUtil.getAppointmentFlag();
			ConstantUtil.paymentFlag = argsConfigUtil.getPaymentFlag();
			ConstantUtil.qryApplyFlag = argsConfigUtil.getQryApplyFlag();
			ConstantUtil.bankAndNetRepFlag = argsConfigUtil.getBankAndNetRepFlag();
			ConstantUtil.bagInfoFlag = argsConfigUtil.getBagInfoFlag();
			ConstantUtil.branchInfoFlag = argsConfigUtil.getBranchInfoFlag();
			ConstantUtil.perInfoFlag = argsConfigUtil.getPerInfoFlag();
		}
	}
}
