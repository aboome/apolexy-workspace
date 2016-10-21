package com.yh.apoplexy.assist.dto.admin.doctor;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

public class EvalueDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5403470675605702866L;
	
    private String doctorName;
    
    private Date createTime;
    //案例完整度，满分5分
    private Long caseIntegrity;
    //病例详细度，满分5分
    private Long caseDetail;

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCaseIntegrity() {
		return caseIntegrity;
	}

	public void setCaseIntegrity(Long caseIntegrity) {
		this.caseIntegrity = caseIntegrity;
	}

	public Long getCaseDetail() {
		return caseDetail;
	}

	public void setCaseDetail(Long caseDetail) {
		this.caseDetail = caseDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
			
 
   
}
