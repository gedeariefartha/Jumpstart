package com.project.jumpstartbackend.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class CampaignRegDto {
	private Long id;
	private String title;
	private String desc;
	private MultipartFile file;
	private Date timedate;
	private boolean status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Date getTimedate() {
		return timedate;
	}
	public void setTimedate(Date timedate) {
		this.timedate = timedate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}


}
