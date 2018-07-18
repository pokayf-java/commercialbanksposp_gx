package com.poka.app.cb.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.poka.app.anno.enity.BagInfo;
import com.poka.app.anno.enity.BankCheckDailyRep;
import com.poka.app.anno.enity.BankCheckDailyRepList;
import com.poka.app.anno.enity.MonRule;
import com.poka.app.anno.enity.MoneyDataInfo;
import com.poka.app.anno.enity.NetCheckDailyRep;
import com.poka.app.anno.enity.NetCheckDailyRepList;
import com.poka.app.vo.AppointmenResult;

@WebService
public interface ICBPospSW {

	@WebMethod(operationName = "handleAppointmen")
	@WebResult(name = "result")
	public boolean handleAppointmen(@WebParam(name = "appointment") AppointmenResult appointment);

	@WebMethod(operationName = "sendMonRuleData")
	@WebResult(name = "result")
	public boolean sendMonRuleData(@WebParam(name = "monRuleList") List<MonRule> monRuleList);

	@WebMethod(operationName = "sendMonRuleDataForCZYH")
	@WebResult(name = "result")
	public boolean sendMonRuleDataForCZYH(@WebParam(name = "monRuleList") List<MonRule> monRuleList);

	@WebMethod(operationName = "getPerInfoData")
	@WebResult(name = "result")
	public boolean getPerInfoData(@WebParam(name = "listData") String listData);

	@WebMethod(operationName = "getBanchInfoData")
	@WebResult(name = "result")
	public boolean getBanchInfoData(@WebParam(name = "listData") String listData);

	@WebMethod(operationName = "sendNetCheckDailyRep")
	@WebResult(name = "result")
	public boolean sendNetCheckDailyRepBak(
			@WebParam(name = "netCheckDailyRepList") List<NetCheckDailyRep> netCheckDailyRepList);

	@WebMethod(operationName = "sendBankCheckDailyRep")
	@WebResult(name = "result")
	public boolean sendBankCheckDailyRepBak(
			@WebParam(name = "bankCheckDailyRepList") List<BankCheckDailyRep> bankCheckDailyRepList);

	/* 网点流水 */
	@WebMethod(operationName = "sendNetCheckDailyRepList")
	@WebResult(name = "result")
	public boolean sendNetCheckDailyRepList(
			@WebParam(name = "netCheckDailyRepListFlow") List<NetCheckDailyRepList> netCheckDailyRepListFlow);

	/* 银行流水 */
	@WebMethod(operationName = "sendBankCheckDailyRepList")
	@WebResult(name = "result")
	public boolean sendBankCheckDailyRepList(
			@WebParam(name = "bankCheckDailyRepListFlow") List<BankCheckDailyRepList> bankCheckDailyRepListFlow);

	@WebMethod(operationName = "selectGZHData")
	@WebResult(name = "result")
	public List<MoneyDataInfo> selectGZHData(@WebParam(name = "mon") String mon);
	
	/*取款信息下发商行*/
	@WebMethod(operationName = "sendBagInfoData")
	@WebResult(name = "result")
	public boolean sendBagInfoData(@WebParam(name = "bagInfoList") List<BagInfo> bagInfoList);

}
