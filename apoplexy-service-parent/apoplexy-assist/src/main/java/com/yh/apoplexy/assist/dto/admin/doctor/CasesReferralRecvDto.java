package com.yh.apoplexy.assist.dto.admin.doctor;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

public class CasesReferralRecvDto extends Entity {

	/**
	 * 接诊医生的信息，包括医生姓名，医生所在医院，接诊时间
	 */
	private static final long serialVersionUID = -1799986575615836288L;
	private Long id;
	
	private String doctorName;
	
	private String hospitalName;
	
	private Date createTime;
   
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
