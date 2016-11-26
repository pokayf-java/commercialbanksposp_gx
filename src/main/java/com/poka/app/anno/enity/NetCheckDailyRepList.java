package com.poka.app.anno.enity;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 	网点清分日结流水
 * @author lb
 *
 */
@Entity
@IdClass(NetCheckDailyRepListPK.class)
@Table(name="NETCHECKDAILYREP_LIST")
public class NetCheckDailyRepList {
	
	private String bankNo;			//银行号
	private String netNo;			//网点号
	private String checkDate;		//清分日期
	private String operDate;		//插入时间
	private BigDecimal monVal;		//币值
	private BigInteger records;		//张数
	private String perCode;			//机具号
	
	@Id
	@Column(name="BANKNO",length=20)
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	@Id
	@Column(name="NETNO",length=20)
	public String getNetNo() {
		return netNo;
	}
	public void setNetNo(String netNo) {
		this.netNo = netNo;
	}
	
	@Id
	@Column(name="CHECKDATE",length=20)
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	
	@Column(name="MONVAL")
	public BigDecimal getMonVal() {
		return monVal;
	}
	public void setMonVal(BigDecimal monVal) {
		this.monVal = monVal;
	}
	
	@Column(name="RECORDS")
	public BigInteger getRecords() {
		return records;
	}
	public void setRecords(BigInteger records) {
		this.records = records;
	}
	
	@Column(name="OPERDATE",length=20)
	public String getOperDate() {
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	@Column(name="PERCODE",length=20)
	public String getPerCode() {
		return perCode;
	}
	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}
	
	
}
