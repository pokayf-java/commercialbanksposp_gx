package com.poka.app.pb.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.poka.app.anno.enity.BagInfo;
import com.poka.app.anno.enity.BankCheckDailyRep;
import com.poka.app.anno.enity.BankCheckDailyRepList;
import com.poka.app.anno.enity.BranchInfo;
import com.poka.app.anno.enity.NetCheckDailyRep;
import com.poka.app.anno.enity.NetCheckDailyRepList;
import com.poka.app.anno.enity.PerInfo;
import com.poka.app.anno.enity.QryApply;
import com.poka.app.vo.AppointmentVo;
import com.poka.app.vo.PaymentVo;

@WebService
public interface IPBPospSW {
	
	/*获取传输指令*/
	@WebMethod(operationName = "getTransferFlag")
	@WebResult(name = "result")
	public boolean getTransferFlag(@WebParam(name = "transferInfo") String transferInfo);

	/*预约取款*/
	@WebMethod(operationName = "makeAppointmen")
	@WebResult(name = "result")
	public boolean makeAppointmen(@WebParam(name = "appointment") AppointmentVo appointment);
	
	/*预约交款*/
	@WebMethod(operationName = "makePayment")
	@WebResult(name = "result")
	public boolean makePayment(@WebParam(name = "payment") PaymentVo payment);

	/*查询申请*/
	@WebMethod(operationName = "makeQryApply")
	@WebResult(name = "result")
	public boolean makeQryApply(@WebParam(name = "qryApply") QryApply qryApply);

	/*机具同步*/
	@WebMethod(operationName = "getPerInfoData")
	@WebResult(name = "result")
	public boolean getPerInfoData(@WebParam(name = "perInfoList") List<PerInfo> perInfoList);
	
	/*网点同步*/
	@WebMethod(operationName = "getBranchInfoData")
	@WebResult(name = "result")
	public boolean getBranchInfoData(@WebParam(name = "branchInfoList") List<BranchInfo> branchInfoList);

	/*网点日结*/
	@WebMethod(operationName = "sendNetCheckDailyRep")
	@WebResult(name = "result")
	public boolean sendNetCheckDailyRep(
			@WebParam(name = "netCheckDailyRepList") List<NetCheckDailyRep> netCheckDailyRepList);
	
	/*网点流水*/
	@WebMethod(operationName = "sendNetCheckDailyRepList")
	@WebResult(name = "result")
	public boolean sendNetCheckDailyRepList(
			@WebParam(name = "netCheckDailyRepListFlow") List<NetCheckDailyRepList> netCheckDailyRepListFlow);

	/*银行日结*/
	@WebMethod(operationName = "sendBankCheckDailyRep")
	@WebResult(name = "result")
	public boolean sendBankCheckDailyRep(
			@WebParam(name = "bankCheckDailyRepList") List<BankCheckDailyRep> bankCheckDailyRepList);
	
	/*银行流水*/
	@WebMethod(operationName = "sendBankCheckDailyRepList")
	@WebResult(name = "result")
	public boolean sendBankCheckDailyRepList(
			@WebParam(name = "bankCheckDailyRepListFlow") List<BankCheckDailyRepList> bankCheckDailyRepListFlow);

	/*横向调拨*/
	@WebMethod(operationName = "sendBagInfo")
	@WebResult(name = "result")
	public boolean sendBagInfo(@WebParam(name = "bagInfoList") List<BagInfo> bagInfoList);
	
}
