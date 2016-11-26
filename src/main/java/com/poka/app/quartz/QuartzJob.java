package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.AppointmentBusiness;
import com.poka.app.anno.bussiness.PaymentBusiness;
import com.poka.app.anno.bussiness.QryApplyBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 预约交取款、查询申请定时任务类
 * @author Administrator
 *
 */

public class QuartzJob {

	AppointmentBusiness appointmentBussiness;
	PaymentBusiness paymentBussiness;
	QryApplyBusiness qryApplyBussiness;
	

	@Autowired
	public void setPaymentBussiness(PaymentBusiness paymentBussiness) {
		this.paymentBussiness = paymentBussiness;
	}

	@Autowired
	public void setQryApplyBussiness(QryApplyBusiness qryApplyBussiness) {
		this.qryApplyBussiness = qryApplyBussiness;
	}

	@Autowired
	public void setAppointmentBussiness(AppointmentBusiness appointmentBussiness) {
		this.appointmentBussiness = appointmentBussiness;
	}

	public void work() {
		
		if(ConstantUtil.appointmentFlag.trim().equals("Enabled")){
			appointmentBussiness.makeAppointment();
		}
		if(ConstantUtil.paymentFlag.trim().equals("Enabled")){
			paymentBussiness.makePayment();
		}
		if(ConstantUtil.qryApplyFlag.trim().equals("Enabled")){
			qryApplyBussiness.makeQryApply();
		}
		
	}

}
