package com.poka.app.anno.enity;

import java.io.Serializable;

public class BagInfoPK implements Serializable{
	
	private String bankId;
	private String bundleCode;
	
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBundleCode() {
		return bundleCode;
	}
	public void setBundleCode(String bundleCode) {
		this.bundleCode = bundleCode;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (this.getBankId()+this.getBundleCode()).hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof BagInfoPK){
			BagInfoPK pk = (BagInfoPK)obj;
			if(pk.getBankId().equals(this.bankId)&&pk.getBundleCode().equals(this.bundleCode)){
				return true;
			}
			
		}
		return false;
	}
	
}
