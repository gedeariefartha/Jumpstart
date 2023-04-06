package com.project.jumpstartbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store_tb")
public class Store {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "phone")
	private String phone;
	@Column(name = "localities")
	private String localities;
	
	public Store() {}
	public Store(Long id, String name, String phone, String localities) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.localities = localities;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLocalities() {
		return localities;
	}
	public void setLocalities(String localities) {
		this.localities = localities;
	}
	
	
}

