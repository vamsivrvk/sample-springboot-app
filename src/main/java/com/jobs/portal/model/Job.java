package com.jobs.portal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jobs.portal.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Job {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;
	private String role;
	private String skills;
	private String minExperience;
	private String maxExperience;
	private String companyName;
	private String contactEmail;
	private String phoneNo;
	private String summary;
	private Date postedDate;
	//private String postedBy;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;	//Each job posted should be tagged to a User
	
	
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getMinExperience() {
		return minExperience;
	}
	public void setMinExperience(String minExperience) {
		this.minExperience = minExperience;
	}
	public String getMaxExperience() {
		return maxExperience;
	}
	public void setMaxExperience(String maxExperience) {
		this.maxExperience = maxExperience;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", role=" + role + ", skills=" + skills + ", minExperience=" + minExperience
				+ ", maxExperience=" + maxExperience + ", companyName=" + companyName + ", contactEmail=" + contactEmail
				+ ", phoneNo=" + phoneNo + ", summary=" + summary + ", postedDate=" + postedDate + ", user=" + user
				+ "]";
	}
	
	
	
	
	

}
