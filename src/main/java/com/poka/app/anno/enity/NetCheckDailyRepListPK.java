package com.poka.app.anno.enity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@SuppressWarnings("serial")
public class NetCheckDailyRepListPK implements Serializable {

	private String bankNo;
	private String checkDate;
	private String netNo;
	private BigDecimal monVal;
	private String operDate;
	private String perCode;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NetCheckDailyRepListPK) {
			NetCheckDailyRepListPK pk = (NetCheckDailyRepListPK) obj;
			if (pk.getBankNo().equals(this.bankNo) && pk.getCheckDate().equals(this.checkDate)
					&& pk.getNetNo().equals(this.netNo) && pk.getMonVal().equals(this.monVal)
					&& pk.getOperDate().equals(this.operDate) && pk.getPerCode().equals(this.perCode)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.getBankNo() + this.getCheckDate() + this.getNetNo() + this.getMonVal() + this.getOperDate()
				+ this.getPerCode()).hashCode();
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getNetNo() {
		return netNo;
	}

	public void setNetNo(String netNo) {
		this.netNo = netNo;
	}

	public BigDecimal getMonVal() {
		return monVal;
	}

	public void setMonVal(BigDecimal monVal) {
		this.monVal = monVal;
	}

	public String getOperDate() {
		return operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	public String getPerCode() {
		return perCode;
	}

	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}

}
