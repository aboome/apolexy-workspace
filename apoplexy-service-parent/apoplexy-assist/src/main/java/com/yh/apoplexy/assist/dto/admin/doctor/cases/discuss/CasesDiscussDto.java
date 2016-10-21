package com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

public class CasesDiscussDto extends Entity {
	
	private static final long serialVersionUID = -2008601254619065350L;

	private Long id;
	
	private String doctorName;
    
    private String hospital;
    
    private Date createTime;
    
    private String type;
    
    private String mainDesc;
    
    private Long readCount;
    
    private Long replyCount;
    
    private Long collectionCount;
    
    private Long likeCount;
 
    
    
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
