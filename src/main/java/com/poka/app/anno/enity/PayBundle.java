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
@Table(name = "TMP_BUNDLES")
@IdClass(value = PayBundlePK.class)
public class PayBundle implements Serializable {

//	private Integer id;
	private String provId;
	private String unitId;
	private String bundleCode;
	private String orderId;
	private String bagCode;
	private String currencyId;
	private Integer currencyKind;
	private Double bundleMoney;	
	private String bagId;
	private String checkId;
	private String empId;
	private Date createDate;
	private Integer bundleState;
	private String bagState;
	private Integer bundleKind;	
	private Integer printCount;
	private String msg;
	private String scanId;
	private String machId;	
	private String tmpBundleCode;
	private Integer state;
	private String areaCode;
	private BooleanEnum isOk;
	
	
	@Override
	public String toString() {
		return "PayBundle [provId=" + provId + ", unitId="
				+ unitId + ", bundleCode=" + bundleCode + ", orderId="
				+ orderId + ", bagCode=" + bagCode + ", currencyId="
				+ currencyId + ", currencyKind=" + currencyKind
				+ ", bundleMoney=" + bundleMoney + ", bagId=" + bagId
				+ ", checkId=" + checkId + ", empId=" + empId + ", createDate="
				+ createDate + ", bundleState=" + bundleState + ", bagState="
				+ bagState + ", bundleKind=" + bundleKind + ", printCount="
				+ printCount + ", msg=" + msg + ", scanId=" + scanId
				+ ", machId=" + machId + ", tmpBundleCode=" + tmpBundleCode
				+ ", state=" + state + ", areaCode=" + areaCode + ", isOk="
				+ isOk + "]";
	}
//	@Id
//	@Column(name = "Did")
//	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@Column(name = "BundleCode",length=20)
	public String getBundleCode() {
		return bundleCode;
	}
	
	public void setBundleCode(String bundleCode) {
		this.bundleCode = bundleCode;
	}
	
	
	
	@Id
	@Column(name = "OrderId",length=20)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	@Column(name = "BagCode",length=20)
	public String getBagCode() {
		return bagCode;
	}
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
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
	
	@Column(name = "BundleMoney")
	public Double getBundleMoney() {
		return bundleMoney;
	}
	public void setBundleMoney(Double bundleMoney) {
		this.bundleMoney = bundleMoney;
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
	
	@Column(name = "EmpId",length=8)
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "BundleState")
	public Integer getBundleState() {
		return bundleState;
	}
	public void setBundleState(Integer bundleState) {
		this.bundleState = bundleState;
	}
	
	@Column(name = "BagState")
	public String getBagState() {
		return bagState;
	}
	public void setBagState(String bagState) {
		this.bagState = bagState;
	}
	
	@Column(name = "BundleKind")
	public Integer getBundleKind() {
		return bundleKind;
	}
	public void setBundleKind(Integer bundleKind) {
		this.bundleKind = bundleKind;
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
	
	@Column(name = "TmpBundleCode",length=24)
	public String getTmpBundleCode() {
		return tmpBundleCode;
	}
	public void setTmpBundleCode(String tmpBundleCode) {
		this.tmpBundleCode = tmpBundleCode;
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
	
	
	
	
	@Column(name = "IsOk")
	@Enumerated(EnumType.ORDINAL)
	public BooleanEnum getIsOk() {
		return isOk;
	}
	public void setIsOk(BooleanEnum isOk) {
		this.isOk = isOk;
	}
}
