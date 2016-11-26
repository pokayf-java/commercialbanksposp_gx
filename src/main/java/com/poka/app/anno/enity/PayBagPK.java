package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PayBagPK implements Serializable {
//	private Integer id;
	private String provId;
	private String unitId;
	private String bagCode;
	private String orderId;
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof PayBagPK){
			PayBagPK pk = (PayBagPK)obj;
//			if(pk.getId() == this.id 
			if(pk.getProvId().equals(this.getProvId())
					&& pk.getOrderId().equals(this.getOrderId())
					&& pk.getUnitId().equals(this.getUnitId())
					&& pk.getBagCode().equals(this.getBagCode())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return this.getOrderId().hashCode();
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
	
	@Column(name = "ProvId",length=2)
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	
	@Column(name = "UnitId",length=4)
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Column(name = "BagCode",length=20)
	public String getBagCode() {
		return bagCode;
	}
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}
	@Column(name = "OrderId",length=20)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
