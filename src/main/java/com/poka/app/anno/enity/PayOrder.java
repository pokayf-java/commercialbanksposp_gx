package com.poka.app.anno.enity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.poka.app.enumtype.BooleanEnum;
import com.poka.app.enumtype.StateType;

@Entity
@Table(name = "TMP_ORDERS")
@IdClass(value=PayOrderPK.class)
public class PayOrder implements Serializable {
	
//	private Integer id;
	private String provId;
	private String unitId;
	private String orderId;
	private String sourceId;
	private String fromProvId;
	private String fromUnitId;
	private Date orderDate;
	private Date bussinessDate;
	private Double orderMoney;
	private Integer orderKind;
	private StateType orderState;
	private String orderEmpId;
	private String bussinessEmpId;
	private BooleanEnum isDownload;
	private String downloadUserId;
	private String msg;
	private String provUnitName;
	
	
@Override
	public String toString() {
		return "PayOrder [provId=" + provId + ", unitId="
				+ unitId + ", orderId=" + orderId + ", sourceId=" + sourceId
				+ ", fromProvId=" + fromProvId + ", fromUnitId=" + fromUnitId
				+ ", orderDate=" + orderDate + ", bussinessDate="
				+ bussinessDate + ", orderMoney=" + orderMoney + ", orderKind="
				+ orderKind + ", orderState=" + orderState + ", orderEmpId="
				+ orderEmpId + ", bussinessEmpId=" + bussinessEmpId
				+ ", isDownload=" + isDownload + ", downloadUserId="
				+ downloadUserId + ", msg=" + msg + ", provUnitName="
				+ provUnitName + "]";
	}
	//	private List<PayOrderDetail> detail = new ArrayList<>();
//	private List<PayBag> bags = new ArrayList<>();
//	private List<PayBundles> bundles = new ArrayList<>();
//	
//	@OneToMany(mappedBy="orderId",fetch=FetchType.LAZY)
//	public List<PayOrderDetail> getDetail() {
//		return detail;
//	}
//	public void setDetail(List<PayOrderDetail> detail) {
//		this.detail = detail;
//	}
//	
//	@OneToMany(mappedBy="orderId",fetch=FetchType.LAZY)
//	public List<PayBag> getBags() {
//		return bags;
//	}
//	public void setBags(List<PayBag> bags) {
//		this.bags = bags;
//	}
//	
//	@OneToMany(mappedBy="orderId",fetch=FetchType.LAZY)
//	public List<PayBundles> getBundles() {
//		return bundles;
//	}
//	public void setBundles(List<PayBundles> bundles) {
//		this.bundles = bundles;
//	}
//	
//	
//	@Id
//	@Column(name = "Did")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
	@Id
	@Column(name = "ProvId",length=2)
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	
	@Id
	@Column(name = "UnitId",length=4)
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Id
	@Column(name = "OrderId",length=20)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "SourceId",length=20)
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@Column(name = "FromProvId",length=2)
	public String getFromProvId() {
		return fromProvId;
	}
	public void setFromProvId(String fromProvId) {
		this.fromProvId = fromProvId;
	}
	
	@Column(name = "FromUnitId",length=4)
	public String getFromUnitId() {
		return fromUnitId;
	}
	public void setFromUnitId(String fromUnitId) {
		this.fromUnitId = fromUnitId;
	}
	
	@Column(name = "OrderDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Column(name = "BussinessDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBussinessDate() {
		return bussinessDate;
	}
	public void setBussinessDate(Date bussinessDate) {
		this.bussinessDate = bussinessDate;
	}
	
	@Column(name = "OrderMoney")
	public Double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
	
	@Column(name = "OrderKind")
	//@Enumerated(EnumType.ORDINAL)
	public Integer getOrderKind() {
		return orderKind;
	}
	public void setOrderKind(Integer orderKind) {
		this.orderKind = orderKind;
	}
	
	@Column(name = "OrderState")
	@Enumerated(EnumType.ORDINAL)
	public StateType getOrderState() {
		return orderState;
	}
	public void setOrderState(StateType orderState) {
		this.orderState = orderState;
	}
	
	@Column(name = "OrderEmpId",length=8)
	public String getOrderEmpId() {
		return orderEmpId;
	}
	public void setOrderEmpId(String orderEmpId) {
		this.orderEmpId = orderEmpId;
	}
	
	@Column(name = "BussinessEmpId",length=8)
	public String getBussinessEmpId() {
		return bussinessEmpId;
	}
	public void setBussinessEmpId(String bussinessEmpId) {
		this.bussinessEmpId = bussinessEmpId;
	}
	
	@Column(name = "IsDownload")
	@Enumerated(EnumType.ORDINAL)
	public BooleanEnum getIsDownload() {
		return isDownload;
	}
	public void setIsDownload(BooleanEnum isDownload) {
		this.isDownload = isDownload;
	}
	
	@Column(name = "DownloadUserId",length=8)
	public String getDownloadUserId() {
		return downloadUserId;
	}
	public void setDownloadUserId(String downloadUserId) {
		this.downloadUserId = downloadUserId;
	}
	
	@Column(name = "Msg",length=200)
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Column(name = "ProvUnitName",length=150)
	public String getProvUnitName() {
		return provUnitName;
	}
	public void setProvUnitName(String provUnitName) {
		this.provUnitName = provUnitName;
	}

}
