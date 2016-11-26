package com.poka.app.anno.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.poka.app.anno.base.service.impl.BranchInfoService;
import com.poka.app.anno.base.service.impl.PerInfoService;
import com.poka.app.anno.enity.BranchInfo;
import com.poka.app.anno.enity.BranchInfoPK;
import com.poka.app.anno.enity.PerInfo;
import com.poka.app.anno.enity.PerInfoPK;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;

/**
 * 机具，网点信息同步业务类
 * @author Administrator
 *
 */
@Component
public class PerInfoAndBranchInfoBusiness {

	Logger logger = Logger.getLogger(PerInfoAndBranchInfoBusiness.class);

	private PerInfoService perInfoService;

	private BranchInfoService branchInfoService;

	private CxfUtil cxfUtil;

	@Autowired
	@Qualifier("perInfoService")
	public void setPerInfoService(PerInfoService perInfoService) {
		this.perInfoService = perInfoService;
	}

	@Autowired
	@Qualifier("branchInfoService")
	public void setBranchInfoService(BranchInfoService branchInfoService) {
		this.branchInfoService = branchInfoService;
	}

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	/**
	 * 获取所有机具信息，然后发送至人行Webservice
	 */
	public void sendPerInfo() {
		List<PerInfo> perInfoList = perInfoService.getPerinfoList();
		if (null != perInfoList && perInfoList.size() > 0) {
			handlePerInfoData(perInfoList);
		} else {
			logger.info("perinfo size is zero");
			return;
		}
	}

	public void handlePerInfoData(List<PerInfo> list) {
		try {
			logger.info("perinfo list size is " + list.size());
			IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
			cxfUtil.recieveTimeOutWrapper(service);
			boolean result = Boolean.FALSE;
			result = service.getTransferFlag("perInfo");
			if (result) {
				result = service.getPerInfoData(list);
				if (result) {
					logger.info("perinfodata 信息同步成功...("
							+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
					logger.info("共计" + list.size() + "条.");
				} else {
					logger.info("处理perinfodata失败...(" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
							+ ")");
				}
			} else {
				logger.info("人行服务器拒绝机具信息同步...");
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

	public void sendBranchInfo() {

		List<BranchInfo> branchInfoList = branchInfoService.getBranchInfoList();
		if (null != branchInfoList && branchInfoList.size() > 0) {
			handleBranchInfoData(branchInfoList);
		} else {
			logger.info("branchInfo size is zero");
			return;
		}

		// int maxCount = 5;
		// int first = 0;
		// int count = this.branchInfoService.getBranchInfoCount();
		// if (count <= 0) {
		// logger.info("branchInfo size is zero");
		// return;
		// } else {
		// while (true) {
		// List<BranchInfo> list = branchInfoService.getBranchInfoList(first,
		// maxCount);
		// handleBranchInfoData(list);
		// first += maxCount;
		// if (list.size() < maxCount || first >= count) {
		// break;
		// }
		// }
		// }
		logger.info("finished handle perinfo");
	}

	public void handleBranchInfoData(List<BranchInfo> list) {
		try {
			logger.info("BranchInfo list size is " + list.size());
			IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
			cxfUtil.recieveTimeOutWrapper(service);
			boolean result = Boolean.FALSE;
			result = service.getTransferFlag("branchInfo");
			if (result) {
				result = service.getBranchInfoData(list);
				if (result) {
					logger.info(
							"BranchInfo 信息同步成功...(" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
					logger.info("共计" + list.size() + "条.");
				} else {
					logger.info(
							"BranchInfo 信息同步失败...(" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
				}
			} else {
				logger.info("人行服务器拒绝网点信息同步...");
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
	 * 保存村镇银行传来的网点信息
	 * 
	 * @param biList
	 * @return
	 */
	public boolean getBranchInfoData(String listData) {
		Gson gson = new Gson();
		List<BranchInfo> list = gson.fromJson(listData, new TypeToken<List<BranchInfo>>() {
		}.getType());
		if (list.size() <= 0) {
			return false;
		}
		for (BranchInfo branch : list) {
			BranchInfoPK pk = new BranchInfoPK();
			pk.setAgencyno(branch.getAgencyno());
			pk.setBankno(branch.getBankno());
			BranchInfo tem = branchInfoService.getBranchInfo(pk);
			if (tem == null) {
				branchInfoService.save(branch);
				continue;
			}
			tem.setAddress(branch.getAddress());
			tem.setBranchname(branch.getBranchname());
			tem.setRemark(branch.getRemark());
			tem.setTelphone(branch.getTelphone());
			branchInfoService.save(tem);
		}
		branchInfoService.flush();
		return true;
	}

	/**
	 * 保存村镇银行传来的机具信息
	 * 
	 * @param perInfoList
	 * @return
	 */
	public boolean getPerInfoData(String listData) {

		Gson gson = new Gson();
		List<PerInfo> list = gson.fromJson(listData, new TypeToken<List<PerInfo>>() {
		}.getType());

		if (list.size() <= 0) {
			return false;
		}
		for (PerInfo per : list) {

			PerInfoPK perInfoPK = new PerInfoPK();

			perInfoPK.setPercode(per.getPercode());
			perInfoPK.setBankno(per.getBankno());
			perInfoPK.setAgencyno(per.getAgencyno());
			// perInfoPK.setUseStatus(per.getUseStatus());

			PerInfo tem = perInfoService.getPerinfo(perInfoPK);

			if (tem == null) {
				perInfoService.save(per);
				continue;
			}
			tem.setPercode(per.getPercode());
			tem.setAcctaddr(per.getAcctaddr());
			tem.setAgencyno(per.getAgencyno());
			tem.setBankno(per.getBankno());
			tem.setBrand(per.getBrand());
			tem.setModel(per.getModel());
			tem.setPertype(per.getPertype());
			tem.setQuality(per.getQuality());
			tem.setStatus(per.getStatus());
			tem.setUseStatus(per.getUseStatus());
			perInfoService.save(tem);
		}
		perInfoService.flush();
		logger.info("机具表数据同步成功！(" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
		return true;
	}

}
