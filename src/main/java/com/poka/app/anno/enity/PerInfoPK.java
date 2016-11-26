package com.poka.app.anno.enity;

import java.io.Serializable;

public class PerInfoPK implements Serializable {

	private String percode;
	private String bankno;
	private String agencyno;
//	private String useStatus;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PerInfoPK) {
			PerInfoPK pk = (PerInfoPK) obj;
			if (pk.getPercode().equals(this.percode) && pk.getBankno().equals(this.bankno)
					&& pk.getAgencyno().equals(this.agencyno)) {
				return true;
			}

		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.getPercode() + this.getBankno() + this.getAgencyno()).hashCode();
	}

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getAgencyno() {
		return agencyno;
	}

	public void setAgencyno(String agencyno) {
		this.agencyno = agencyno;
	}

	public String getPercode() {
		return percode;
	}

	public void setPercode(String percode) {
		this.percode = percode;
	}
}
