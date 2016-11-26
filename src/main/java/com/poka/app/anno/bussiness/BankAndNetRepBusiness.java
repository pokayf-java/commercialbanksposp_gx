package com.poka.app.anno.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BankCheckDailyRepListService;
import com.poka.app.anno.base.service.impl.BankCheckDailyRepService;
import com.poka.app.anno.base.service.impl.NetCheckDailyRepListService;
import com.poka.app.anno.base.service.impl.NetCheckDailyRepService;
import com.poka.app.anno.enity.BankCheckDailyRep;
import com.poka.app.anno.enity.BankCheckDailyRepList;
import com.poka.app.anno.enity.NetCheckDailyRep;
import com.poka.app.anno.enity.NetCheckDailyRepList;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;
import com.poka.app.util.PokaDateUtil;

/**
 * 网点、银行日结同步更新到人行
 * 
 * @author lb
 *
 */
@Component
public class BankAndNetRepBusiness {

	Logger logger = Logger.getLogger(BankAndNetRepBusiness.class);
	private BankCheckDailyRepService bankCheckDailyRepService;
	private NetCheckDailyRepService netCheckDailyRepService;

	private BankCheckDailyRepListService bankCheckDailyRepListService;
	private NetCheckDailyRepListService netCheckDailyRepListService;

	private CxfUtil cxfUtil;

	@Autowired
	@Qualifier("bankCheckDailyRepListService")
	public void setBankCheckDailyRepListService(BankCheckDailyRepListService bankCheckDailyRepListService) {
		this.bankCheckDailyRepListService = bankCheckDailyRepListService;
	}

	@Autowired
	@Qualifier("netCheckDailyRepListService")
	public void setNetCheckDailyRepListService(NetCheckDailyRepListService netCheckDailyRepListService) {
		this.netCheckDailyRepListService = netCheckDailyRepListService;
	}

	@Autowired
	@Qualifier("bankCheckDailyRepService")
	public void setBankCheckDailyRepService(BankCheckDailyRepService bankCheckDailyRepService) {
		this.bankCheckDailyRepService = bankCheckDailyRepService;
	}

	@Autowired
	@Qualifier("netCheckDailyRepService")
	public void setNetCheckDailyRepService(NetCheckDailyRepService netCheckDailyRepService) {
		this.netCheckDailyRepService = netCheckDailyRepService;
	}

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	/**
	 * 银行日结同步更新到人行
	 */
	public void sendBankCheckRepList() {
		try {
			List<BankCheckDailyRep> bankCheckDailyRepList = bankCheckDailyRepService.getBankCheckDailyRepList();
			if (null != bankCheckDailyRepList && bankCheckDailyRepList.size() > 0) {
				IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
				cxfUtil.recieveTimeOutWrapper(service);
				boolean result = Boolean.FALSE;
				result = service.getTransferFlag("bankAndNetRep");
				if (result) {
					result = service.sendBankCheckDailyRep(bankCheckDailyRepList);
					if (result) {
						logger.info("BankCheckDailyRep 数据同步成功...("
								+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
						logger.info("共计" + bankCheckDailyRepList.size() + "条.");
					} else {
						logger.info("BankCheckDailyRep 数据同步失败...("
								+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
					}
				} else {
					logger.info("人行服务器拒绝同步请求...");
					return;
				}
			} else {
				logger.info("BankCheckDailyRep Size:" + bankCheckDailyRepList.size());
				return;
			}
		} catch (Exception ex) {
			logger.info("连接服务器失败...");
			ex.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 网点日结同步更新到人行
	 */
	public void sendNetCheckRepList() {
		try {

			List<NetCheckDailyRep> netCheckDailyRepList = netCheckDailyRepService.getNetCheckDailyRepList();
			if (null != netCheckDailyRepList && netCheckDailyRepList.size() > 0) {
				IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
				cxfUtil.recieveTimeOutWrapper(service);
				boolean result = Boolean.FALSE;
				result = service.getTransferFlag("bankAndNetRep");
				if (result) {
					result = service.sendNetCheckDailyRep(netCheckDailyRepList);
					if (result) {
						logger.info(" NetCheckDailyRep 数据同步成功... ");
						logger.info("总计： " + netCheckDailyRepList.size() + "条.");
					} else {
						logger.info(" NetCheckDailyRep 数据同步失败... ");
					}
				} else {
					logger.info("人行服务器拒绝同步请求...");
					return;
				}
			} else {
				logger.info("NetCheckDailyRep Size:" + netCheckDailyRepList.size());
				return;
			}

		} catch (Exception ex) {
			logger.info("连接服务器失败!");
			ex.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 网点流水同步更新到人行
	 */
	public void sendNetCheckRepListFlow() {

		List checkDailyRepLogs = netCheckDailyRepListService.getCheckDailyRepLogsList(2, 0, null);
		if (null != checkDailyRepLogs && checkDailyRepLogs.size() > 0) {
			List checkDailyRepLogsSec = netCheckDailyRepListService.getCheckDailyRepLogsList(2, 1, null);
			if (null != checkDailyRepLogsSec && checkDailyRepLogsSec.size() > 0) {
				sendCheckDailyRepListInfo(null, 2);
			} else {
				List<String> moreDateList = PokaDateUtil.getMoreDate(15);
				if (null != moreDateList && moreDateList.size() > 0) {
					for (int i = 0; i < moreDateList.size(); i++) {
						String operDateTmp = moreDateList.get(i);
						List<String> judgeLogsList = netCheckDailyRepListService.getCheckDailyRepLogsList(2, 2,
								operDateTmp);
						if (judgeLogsList.isEmpty()) {
							sendCheckDailyRepListInfo(operDateTmp, 2);
						}
					}
				}
			}
		} else {
			sendCheckDailyRepListInfo(null, 2);
		}

	}

	/**
	 * 银行流水同步更新到人行
	 */
	public void sendBankCheckRepListFlow() {

		List checkDailyRepLogs = netCheckDailyRepListService.getCheckDailyRepLogsList(2, 0, null);
		if (null != checkDailyRepLogs && checkDailyRepLogs.size() > 0) {
			List checkDailyRepLogsSec = netCheckDailyRepListService.getCheckDailyRepLogsList(2, 1, null);
			if (null != checkDailyRepLogsSec && checkDailyRepLogsSec.size() > 0) {
				sendCheckDailyRepListInfo(null, 1);
			} else {
				List<String> moreDateList = PokaDateUtil.getMoreDate(15);
				if (null != moreDateList && moreDateList.size() > 0) {
					for (int i = 0; i < moreDateList.size(); i++) {
						String operDateTmp = moreDateList.get(i);
						List<String> judgeLogsList = netCheckDailyRepListService.getCheckDailyRepLogsList(1, 2,
								operDateTmp);
						if (judgeLogsList.isEmpty()) {
							sendCheckDailyRepListInfo(operDateTmp, 1);
						}
					}
				}
			}
		} else {
			sendCheckDailyRepListInfo(null, 1);
		}

	}

	/**
	 * 执行计算日结的存储过程
	 */
	public void doProduce() {
		bankCheckDailyRepService.doProduce();
	}

	/**
	 * 
	 * @param operDate
	 * @param bankOrNetFlag:
	 *            1,表示银行；2,表示网点
	 */
	public void sendCheckDailyRepListInfo(String operDate, int bankOrNetFlag) {

		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		try {
			result = service.getTransferFlag("bankAndNetRep");
		} catch (Exception ex) {
			logger.info("连接服务器失败...");
			return;
		}
		if (result) {
			List<NetCheckDailyRepList> netCheckDailyRepList = null;
			List<BankCheckDailyRepList> bankCheckDailyRepList = null;
			if (null != operDate) {
				if (bankOrNetFlag == 1) {
					bankCheckDailyRepList = bankCheckDailyRepListService.getBankCheckDailyRepList(operDate);
				}
				if (bankOrNetFlag == 2) {
					netCheckDailyRepList = netCheckDailyRepListService.getNetCheckDailyRepList(operDate);
				}

			} else {
				if (bankOrNetFlag == 1) {
					bankCheckDailyRepList = bankCheckDailyRepListService.getBankCheckDailyRepList(null);
				}
				if (bankOrNetFlag == 2) {
					netCheckDailyRepList = netCheckDailyRepListService.getNetCheckDailyRepList(null);
				}
			}
			if (bankOrNetFlag == 1) {
				if (null != bankCheckDailyRepList && bankCheckDailyRepList.size() > 0) {
					try {
						result = service.sendBankCheckDailyRepList(bankCheckDailyRepList);
					} catch (Exception ex) {
						logger.info("连接服务器失败...");
					}
					if (result) {
						logger.info(" BankCheckDailyRepList 数据同步成功... ");
						logger.info("总计： " + bankCheckDailyRepList.size() + "条.");

						if (null != operDate) {
							netCheckDailyRepListService.doInsertLog(bankOrNetFlag, operDate);
						} else {
							netCheckDailyRepListService.doInsertLog(bankOrNetFlag, null);
						}

					} else {
						logger.info(" BankCheckDailyRepList 数据同步失败... ");
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else if (bankOrNetFlag == 2) {
				if (null != netCheckDailyRepList && netCheckDailyRepList.size() > 0) {
					try {
						result = service.sendNetCheckDailyRepList(netCheckDailyRepList);
					} catch (Exception ex) {
						logger.info("连接服务器失败...");
					}
					if (result) {
						logger.info(" NetCheckDailyRepList 数据同步成功...");
						logger.info("总计： " + netCheckDailyRepList.size() + "条.");
						if (null != operDate) {
							netCheckDailyRepListService.doInsertLog(bankOrNetFlag, operDate);
						} else {
							netCheckDailyRepListService.doInsertLog(bankOrNetFlag, null);
						}

					} else {
						logger.info(" NetCheckDailyRepList 数据同步失败...");
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {

			}
		} else {
			logger.info("人行服务器拒绝同步请求...");
			return;
		}

	}

	public boolean getBankCheckDailyRepList(final List<BankCheckDailyRepList> bankListData) {
		String bankNo = "";
		int sum = 0;
		if (null != bankListData && bankListData.size() > 0) {
			sum = bankListData.size();
			for (BankCheckDailyRepList bankCheckDailyRepListObj : bankListData) {
				bankNo = bankCheckDailyRepListObj.getBankNo();
				bankCheckDailyRepListService.save(bankCheckDailyRepListObj);
			}
		}
		logger.info("银行(" + bankNo + ")日结流水同步成功...(" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
				+ ")");
		logger.info("共计" + sum + "条.");
		return true;
	}

	public boolean getNetCheckDailyRepList(final List<NetCheckDailyRepList> netListData) {
		String netNo = "";
		int sum = 0;
		if (null != netListData && netListData.size() > 0) {
			sum = netListData.size();
			for (NetCheckDailyRepList netCheckDailyRepListObj : netListData) {
				netNo = netCheckDailyRepListObj.getNetNo();
				netCheckDailyRepListService.save(netCheckDailyRepListObj);
			}
		}
		logger.info(
				"网点(" + netNo + ")日结流水同步成功...(" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
		logger.info("共计" + sum + "条.");
		return true;
	}

}
