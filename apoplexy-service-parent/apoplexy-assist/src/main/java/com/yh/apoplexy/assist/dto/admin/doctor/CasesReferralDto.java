package com.yh.apoplexy.assist.dto.admin.doctor;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

public class CasesReferralDto extends Entity {
	  
	
	  
     /**
	 * 
	 */
	private static final long serialVersionUID = 1728557973566771561L;
       
	   private Long id;
	   
	   
	   private String doctorName;
       
       private String hospital;
       
       private Date createTime;
       
       private String title;
       //转诊的严重程度，referralType
       private String type;
       
       private String mainDesc;
       //转诊的状态
       private String status;
       
       private Long readCount;
       
       private Long receiveCount;

       
    
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
