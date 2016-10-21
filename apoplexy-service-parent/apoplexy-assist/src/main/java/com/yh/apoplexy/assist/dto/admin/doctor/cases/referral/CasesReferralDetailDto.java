package com.yh.apoplexy.assist.dto.admin.doctor.cases.referral;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.entity.Entity;

public class CasesReferralDetailDto extends Entity {

	/**
	 * 查询病例转诊详情DTO
	 */
	private static final long serialVersionUID = 4612694770294744567L;
	//发布医生头像
	private String image;
	
    private String doctorName;
    
    private String hospital;
    
    private Date createTime;
    
    private String mainDesc;
    
    private String title;
    
	private String  nowIllness ; 
	
	private String hisIllness; 
	
	private String assistCheck ; 
	
	private String healthCheck;
	//病例照片/视频
	private String casesPicture;
	
	private Long readCount;
	
	private Long receiveCount;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getMainDesc() {
		return mainDesc;
	}

	public void setMainDesc(String mainDesc) {
		this.mainDesc = mainDesc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNowIllness() {
		return nowIllness;
	}

	public void setNowIllness(String nowIllness) {
		this.nowIllness = nowIllness;
	}

	public String getHisIllness() {
		return hisIllness;
	}

	public void setHisIllness(String hisIllness) {
		this.hisIllness = hisIllness;
	}

	public String getAssistCheck() {
		return assistCheck;
	}

	public void setAssistCheck(String assistCheck) {
		this.assistCheck = assistCheck;
	}

	public String getHealthCheck() {
		return healthCheck;
	}

	public void setHealthCheck(String healthCheck) {
		this.healthCheck = healthCheck;
	}

	public String getCasesPicture() {
		return casesPicture;
	}

	public void setCasesPicture(String casesPicture) {
		this.casesPicture = casesPicture;
	}

	public Long getReadCount() {
		return readCount;
	}

	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}

	public Long getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(Long receiveCount) {
		this.receiveCount = receiveCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
   
}
