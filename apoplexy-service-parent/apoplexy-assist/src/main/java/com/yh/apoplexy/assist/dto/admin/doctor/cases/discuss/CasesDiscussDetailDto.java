package com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

public class CasesDiscussDetailDto extends Entity {

	private static final long serialVersionUID = 7340755214967675311L;

	private String doctorName;

	private String hospital;

	private Date createTime;
	// 医生的头像
	private String logo;

	private String type;


	private String mainDesc;

	private String nowIllness;

	private String hisIllness;

	private Long healthCheck;

	private Long assistCheck;

	private String casesPicture;

	private Long readCount;

	private Long replyCount;

	private Long collectionCount;

	private Long likeCount;

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	 

	public String getMainDesc() {
		return mainDesc;
	}

	public void setMainDesc(String mainDesc) {
		this.mainDesc = mainDesc;
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

	public Long getHealthCheck() {
		return healthCheck;
	}

	public void setHealthCheck(Long healthCheck) {
		this.healthCheck = healthCheck;
	}

	public Long getAssistCheck() {
		return assistCheck;
	}

	public void setAssistCheck(Long assistCheck) {
		this.assistCheck = assistCheck;
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

	public Long getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Long replyCount) {
		this.replyCount = replyCount;
	}

	public Long getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Long collectionCount) {
		this.collectionCount = collectionCount;
	}

	public Long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
