package com.netmed.payments.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="orders")
public class MyOrder {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long myorder_id;
	
	private String orderId;
	private String amount;
	private String receipt;
	private String status;
	private String userid;
	private String paymentid;
	
	public Long getMyorder_id() {
		return myorder_id;
	}

	public void setMyorder_id(Long myorder_id) {
		this.myorder_id = myorder_id;
	}

	public String getOrderid() {
		return orderId;
	}

	public void setOrderid(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	
}
