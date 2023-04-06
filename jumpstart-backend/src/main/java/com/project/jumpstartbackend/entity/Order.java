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
@Table(name = "order_tb")
public class Order {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productid;
	@ManyToOne
	@JoinColumn(name = "member_id")
	private User memberid;
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private User staffid;
	private Date datetime;
	private boolean status;
	
	public Order() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Product getProductid() {
		return productid;
	}

	public void setProductid(Product productid) {
		this.productid = productid;
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

