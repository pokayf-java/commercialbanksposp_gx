package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RESERVEDETAIL")
public class ReserveDetail implements Serializable {

	private Long fid;
	private String rsvNo;
	private String moneyId;
	private String moneyName;
	private Double rsvSum;
	private String moneyBad;
	
	@Override
	public String toString() {
		return "ReserveDetail [fid=" + fid + ", rsvNo=" + rsvNo + ", moneyId="
				+ moneyId + ", moneyName=" + moneyName + ", rsvSum=" + rsvSum
				+ ", moneyBad=" + moneyBad + "]";
	}
	@Id
	@Column(name = "fid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	
	@Column(name = "RsvNo", length = 20)
	public String getRsvNo() {
		return rsvNo;
	}
	public void setRsvNo(String rsvNo) {
		this.rsvNo = rsvNo;
	}
	
	@Column(name = "MoneyId", length = 2)
	public String getMoneyId() {
		return moneyId;
	}
	public void setMoneyId(String moneyId) {
		this.moneyId = moneyId;
	}
	
	@Column(name = "MoneyName", length = 20)
	public String getMoneyName() {
		return moneyName;
	}
	public void setMoneyName(String moneyName) {
		this.moneyName = moneyName;
	}
	
	@Column(name = "RsvSum")
	public Double getRsvSum() {
		return rsvSum;
	}
	public void setRsvSum(Double rsvSum) {
		this.rsvSum = rsvSum;
	}
	
	@Column(name = "MoneyBad",length=1)
	public String getMoneyBad() {
		return moneyBad;
	}
	public void setMoneyBad(String moneyBad) {
		this.moneyBad = moneyBad;
	}
	
	

}
