package com.poka.app.anno.enity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 银行清分日结流水
 * 
 * @author lb
 *
 */
@Entity
@IdClass(BankCheckDailyRepListPK.class)
@Table(name = "BANKCHECKDAILYREP_LIST")
public class BankCheckDailyRepList implements Serializable {

	private String bankNo; 			// 银行号
	private String checkDate; 		// 清分日期
	private BigDecimal monVal; 		// 币值
	private BigInteger records; 	// 张数
	private String operDate; 		// 插入时间

	@Id
	@Column(name = "BANKNO", length = 20)
	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	@Id
	@Column(name = "CHECKDATE", length = 20)
	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	@Id
	@Column(name = "MONVAL")
	public BigDecimal getMonVal() {
		return monVal;
	}

	public void setMonVal(BigDecimal monVal) {
		this.monVal = monVal;
	}

	@Id
	@Column(name = "RECORDS")
	public BigInteger getRecords() {
		return records;
	}

	public void setRecords(BigInteger records) {
		this.records = records;
	}

	@Column(name = "OPERDATE", length = 20)
	public String getOperDate() {
		return operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

}
