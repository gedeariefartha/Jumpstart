package com.project.jumpstartbackend.dto;

public class VolunteerRegDto {
	private Long id;
	private String email;
	private String phone;
	private String name;
	private Long campaignid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCampaignid() {
		return campaignid;
	}
	public void setCampaignid(Long campaignid) {
		this.campaignid = campaignid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
