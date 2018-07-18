package com.poka.app.anno.enity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.poka.app.enumtype.OrderType;
import com.poka.app.enumtype.StateType;
/**
 * 订单信息的实体类
 *
 */
@Entity
@Table(name = "T_ORDERINFO")
public class OrderInfo implements Serializable {

	private Integer id;
	private String orderId;
	private OrderType orderType;
	private Date insertDate;
	private StateType state;
	private Timestamp finishDate;

	
	@Override
	public String toString() {
		return "OrderInfo [id=" + id + ", orderId=" + orderId + ", orderType="
				+ orderType + ", insertDate=" + insertDate + ", state=" + state
				+ ", finishDate=" + finishDate + "]";
	}

	@Id
	@Column(name = "FID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "OrderId", length = 50)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "OrderType")
	@Enumerated(EnumType.ORDINAL)
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@Column(name = "InsertDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	@Column(name = "State")
	@Enumerated(EnumType.ORDINAL)
	public StateType getState() {
		return state;
	}

	public void setState(StateType state) {
		this.state = state;
	}

	@Column(name = "FinishDate")
	public Timestamp getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Timestamp finishDate) {
		this.finishDate = finishDate;
	}

}
