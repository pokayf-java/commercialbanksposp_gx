package com.poka.app.anno.enity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.poka.app.enumtype.BooleanEnum;

@Entity
@Table(name="RESERVEMAIN")
public class ReserveMain implements Serializable {

	private String rsvNo;
	private String provId;
	private String unitId;
	private String unitName;
	private BooleanEnum flag;
	private Date rsvDate;
	private String bankId;
	private String bankName;
	private String netId;
	private String netName;
	private String userId;
	private String userName;
	private Date createDate;
	private String rsvDesc;
	private Integer state;
	private String checkRemark;
	private String orderId;
	
	@Override
	public String toString() {
		return "ReserveMain [rsvNo=" + rsvNo + ", provId=" + provId
				+ ", unitId=" + unitId + ", unitName=" + unitName + ", flag="
				+ flag + ", rsvDate=" + rsvDate + ", bankId=" + bankId
				+ ", bankName=" + bankName + ", netId=" + netId + ", netName="
				+ netName + ", userId=" + userId + ", userName=" + userName
				+ ", createDate=" + createDate + ", rsvDesc=" + rsvDesc
				+ ", state=" + state + ", checkRemark=" + checkRemark
				+ ", orderId=" + orderId + "]";
	}

	@Id
	@Column(name = "RsvNo", length = 20, unique = true)
	public String getRsvNo() {
		return rsvNo;
	}

	public void setRsvNo(String rsvNo) {
		this.rsvNo = rsvNo;
	}

	@Column(name = "ProvId", length = 2)
	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	@Column(name = "UnitId", length = 4)
	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "UnitName", length = 64)
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(name = "Flag")
	@Enumerated(EnumType.ORDINAL)
	public BooleanEnum getFlag() {
		return flag;
	}

	public void setFlag(BooleanEnum flag) {
		this.flag = flag;
	}

	@Column(name = "RsvDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRsvDate() {
		return rsvDate;
	}

	public void setRsvDate(Date rsvDate) {
		this.rsvDate = rsvDate;
	}

	@Column(name = "BankId", length = 4)
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	@Column(name = "BankName", length = 64)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "NetId", length = 4)
	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	@Column(name = "NetName", length = 100)
	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	@Column(name = "UserId", length = 8)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "UserName", length = 32)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "RsvDesc", length = 64)
	public String getRsvDesc() {
		return rsvDesc;
	}

	public void setRsvDesc(String rsvDesc) {
		this.rsvDesc = rsvDesc;
	}

	@Column(name = "State")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "CheckRemark", length = 64)
	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	@Column(name = "OrderId", length = 64)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	

	

}
