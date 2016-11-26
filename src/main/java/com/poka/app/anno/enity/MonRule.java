package com.poka.app.anno.enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.poka.app.util.InitConstant;
/**
 * 
 * @author lb(冠字号编码规则表)
 *
 */
@Entity
@Table(name = "MONRULE")
public class MonRule {
	
	private String monCode;			//自定义编号
	
	private String monHead;			//冠字号格式(比如HO开头)
	
	private String monType;			//版别
	
	private String monStatus;		//状态：0: 正常,1: 停用, 2: 删除
	
	private String monYear;			//启用年份
	
	private String monVal;			//币值
	
	private String sourceType;		//来源类型 ：0:商行,1:人行

	static{
		new InitConstant();		   //初始化常量
	}
	
	@Id
	@Column(name = "MONCODE",length = 22)
	public String getMonCode() {
		return monCode;
	}

	public void setMonCode(String monCode) {
		this.monCode = monCode;
	}

	@Column(name = "MONHEAD",length = 12)
	public String getMonHead() {
		return monHead;
	}

	public void setMonHead(String monHead) {
		this.monHead = monHead;
	}

	@Column(name = "MONTYPE",length = 20)
	public String getMonType() {
		return monType;
	}

	public void setMonType(String monType) {
		this.monType = monType;
	}

	@Column(name = "MONSTATUS",length = 1)
	public String getMonStatus() {
		return monStatus;
	}

	public void setMonStatus(String monStatus) {
		this.monStatus = monStatus;
	}
	
	@Column(name = "MONVAL",length = 5)
	public String getMonVal() {
		return monVal;
	}

	public void setMonVal(String monVal) {
		this.monVal = monVal;
	}

	@Column(name = "MONYEAR",length = 8)
	public String getMonYear() {
		return monYear;
	}

	public void setMonYear(String monYear) {
		this.monYear = monYear;
	}
	
	@Column(name = "SOURCETYPE",length = 1)
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

}
