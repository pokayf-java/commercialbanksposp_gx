package com.poka.app.anno.enity;

import java.util.Date;

/**
 * 2017年10月18日
 * 查询界面获取冠字号详情实体类
 * @author enma.ai
 *
 */
public class PopupInfo {
	private String operdate; 
	private String opertype; 
	private String operator; 
	private String checker; 
	private String memo;
	
	public String getOperdate() {
		return operdate;
	}
	public void setOperdate(String operdate) {
		this.operdate = operdate;
	}
	public String getOpertype() {
		return opertype;
	}
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "PopupInfo [operdate=" + operdate + ", opertype=" + opertype + ", operator=" + operator + ", checker="
				+ checker + ", memo=" + memo + "]";
	}
	
}
