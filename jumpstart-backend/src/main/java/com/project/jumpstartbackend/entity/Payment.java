package com.project.jumpstartbackend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment_tb")
public class Payment {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order orderid;
	@ManyToOne
	@JoinColumn(name = "member_id")
	private User memberid;
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private User staffid;
	private Date datetime;
	private boolean status;
	
	public Payment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Order getOrderid() {
		return orderid;
	}

	public void setOrderid(Order orderid) {
		this.orderid = orderid;
	}

	public User getMemberid() {
		return memberid;
	}

	public void setMemberid(User memberid) {
		this.memberid = memberid;
	}

	public User getStaffid() {
		return staffid;
	}

	public void setStaffid(User staffid) {
		this.staffid = staffid;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
