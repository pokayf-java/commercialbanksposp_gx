package com.poka.app.anno.enity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.poka.app.enumtype.BooleanEnum;

@Entity
@Table(name = "TMP_BAGS")
@IdClass(value = PayBagPK.class)
public class PayBag implements Serializable {
//	private Integer id;
	private String provId;
	private String unitId;
	private String bagCode;
	private String orderId;
	private String detailId;
	private String currencyId;
	private Integer currencyKind;
	private Double bagMoney;
	private String casingId;
	private String countId;
	private String bagId;
	private String checkId;
	private Date createDate;
	private Integer bagState;
	private Integer printCount;
	private String msg;
	private String scanId;
	private String machId;
	private Integer scanNo;
	private Integer state;
	private String areaCode;
	private Integer writeCount;
	private String dealScanID;
	private BooleanEnum isOk;
	
	
	@Override
	public String toString() {
		return "PayBag [provId=" + provId + ", unitId=" + unitId
				+ ", bagCode=" + bagCode + ", orderId=" + orderId
				+ ", detailId=" + detailId + ", currencyId=" + currencyId
				+ ", currencyKind=" + currencyKind + ", bagMoney=" + bagMoney
				+ ", casingId=" + casingId + ", countId=" + countId
				+ ", bagId=" + bagId + ", checkId=" + checkId + ", createDate="
				+ createDate + ", bagState=" + bagState + ", printCount="
				+ printCount + ", msg=" + msg + ", scanId=" + scanId
				+ ", machId=" + machId + ", scanNo=" + scanNo + ", state="
				+ state + ", areaCode=" + areaCode + ", writeCount="
				+ writeCount + ", dealScanID=" + dealScanID + ", isOk=" + isOk
				+ "]";
	}
//	@Id
//	@Column(name = "Did")
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
	@Id
	@Column(name = "ProvId",length=2)
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	
	@Id
	@Column(name = "UnitId",length=4)
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Id
	@Column(name = "BagCode",length=20)
	public String getBagCode() {
		return bagCode;
	}
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}
	
	@Id
	@Column(name = "OrderId",length=20)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "DetailId",length=20)
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	
	@Column(name = "CurrencyId",length=2)
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	
	@Column(name = "CurrencyKind")
	public Integer getCurrencyKind() {
		return currencyKind;
	}
	public void setCurrencyKind(Integer currencyKind) {
		this.currencyKind = currencyKind;
	}
	
	@Column(name = "BagMoney")
	public Double getBagMoney() {
		return bagMoney;
	}
	public void setBagMoney(Double bagMoney) {
		this.bagMoney = bagMoney;
	}
	
	@Column(name = "CasingId",length=2)
	public String getCasingId() {
		return casingId;
	}
	public void setCasingId(String casingId) {
		this.casingId = casingId;
	}
	
	@Column(name = "CountId",length=8)
	public String getCountId() {
		return countId;
	}
	public void setCountId(String countId) {
		this.countId = countId;
	}
	
	@Column(name = "BagId",length=8)
	public String getBagId() {
		return bagId;
	}
	public void setBagId(String bagId) {
		this.bagId = bagId;
	}
	
	@Column(name = "CheckId",length=8)
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	
	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "BagState")
	public Integer getBagState() {
		return bagState;
	}
	public void setBagState(Integer bagState) {
		this.bagState = bagState;
	}
	
	@Column(name = "PrintCount")
	public Integer getPrintCount() {
		return printCount;
	}
	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}
	
	@Column(name = "Msg",length=50)
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Column(name = "ScanId",length=2)
	public String getScanId() {
		return scanId;
	}
	public void setScanId(String scanId) {
		this.scanId = scanId;
	}
	
	@Column(name = "MachId",length=12)
	public String getMachId() {
		return machId;
	}
	public void setMachId(String machId) {
		this.machId = machId;
	}
	
	@Column(name = "ScanNo")
	public Integer getScanNo() {
		return scanNo;
	}
	public void setScanNo(Integer scanNo) {
		this.scanNo = scanNo;
	}
	
	@Column(name = "State")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	@Column(name = "AreaCode",length=10)
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Column(name = "WriteCount")
	public Integer getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(Integer writeCount) {
		this.writeCount = writeCount;
	}
	
	@Column(name = "DealScanID",length=50)
	public String getDealScanID() {
		return dealScanID;
	}
	public void setDealScanID(String dealScanID) {
		this.dealScanID = dealScanID;
	}
	
	@Column(name = "IsOk")
	@Enumerated(EnumType.ORDINAL)
	public BooleanEnum getIsOk() {
		return isOk;
	}
	public void setIsOk(BooleanEnum isOk) {
		this.isOk = isOk;
	}
	
}
