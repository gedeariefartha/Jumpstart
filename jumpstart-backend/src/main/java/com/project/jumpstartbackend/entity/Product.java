package com.project.jumpstartbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.jumpstartbackend.dto.ProductRegDto;

@Entity
@Table(name = "product_tb")
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "product_name")
	private String name;
	@Column(name = "product_desc")
	private String desc;

	@Column(name = "image")
	private String image;
	
	@Column(name = "prize")
	private double prize;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "file_size")
	private String fileSize;

	@ManyToOne
	@JoinColumn(name = "staff_id")
	private User staffid;
	private boolean status;
	
	public Product() {}
	

	public Product(ProductRegDto dto, User usr) {
		this.name = dto.getName();
		this.desc = dto.getDesc();
		this.staffid = usr;
		this.status = dto.isStatus();
		this.prize = dto.getPrize();
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


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public double getPrize() {
		return prize;
	}


	public void setPrize(double prize) {
		this.prize = prize;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getFileSize() {
		return fileSize;
	}


	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}


	public User getStaffid() {
		return staffid;
	}


	public void setStaffid(User staffid) {
		this.staffid = staffid;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


}
