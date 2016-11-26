package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;

public class PayBundlePK implements Serializable {
//	private Integer id;
	private String provId;
	private String unitId;
	private String bundleCode;
	private String orderId;
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof PayBundlePK){
			PayBundlePK pk = (PayBundlePK)obj;
//			if(pk.getId() == this.id 
			if(pk.getProvId().equals(this.getProvId())
					&& pk.getOrderId().equals(this.getOrderId())
					&& pk.getUnitId().equals(this.getUnitId())
					&& pk.getBundleCode().equals(this.getBundleCode())){
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
	
	@Column(name = "BundleCode",length=20)
	public String getBundleCode() {
		return bundleCode;
	}
	public void setBundleCode(String bagCode) {
		this.bundleCode = bagCode;
	}
	
	
	@Column(name = "OrderId",length=20)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
