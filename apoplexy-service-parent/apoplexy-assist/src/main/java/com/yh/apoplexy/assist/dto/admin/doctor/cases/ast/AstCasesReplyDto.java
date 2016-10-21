package com.yh.apoplexy.assist.dto.admin.doctor.cases.ast;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

public class AstCasesReplyDto extends Entity {

	/**
	 * 查询Ast病例的回复Dto
	 */
	private static final long serialVersionUID = -371169014495647429L;
    
	private Long id;
	
	private String  doctorName;
	
	private String hospital;
	
	private String toDoctorName;
	
	private String toHospital;
	//评论时间
	private Date createTime;
	//评论内容
	private String comment;
   
	
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

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getToDoctorName() {
		return toDoctorName;
	}

	public void setToDoctorName(String toDoctorName) {
		this.toDoctorName = toDoctorName;
	}

	public String getToHospital() {
		return toHospital;
	}

	public void setToHospital(String toHospital) {
		this.toHospital = toHospital;
	}
	
	
}
