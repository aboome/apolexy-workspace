package com.yh.apoplexy.assist.dto.admin.base;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

public class AdminImportDoctorItemDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5128879010668188813L;

	
	private String doctorName;
	
	private String hospital;
	
	private String department;
	
	private String title;
	
	private String job;
	
	private Date effectiveTime;

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	
	
}
