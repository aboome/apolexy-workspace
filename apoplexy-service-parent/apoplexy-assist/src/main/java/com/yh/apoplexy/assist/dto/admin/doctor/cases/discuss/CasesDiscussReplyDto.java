package com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

public class CasesDiscussReplyDto extends Entity {
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3367740209626646677L;
   
	private Long id;
	
	private String replyNameFrom;
    
    private String hospitalNameFrom;
    
    private Date createTime;
    
    private String content;
    
    private String replyNameTo;
    
    private String hospitalNameTo;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getReplyNameFrom() {
		return replyNameFrom;
	}

	public void setReplyNameFrom(String replyNameFrom) {
		this.replyNameFrom = replyNameFrom;
	}

	public String getHospitalNameFrom() {
		return hospitalNameFrom;
	}

	public void setHospitalNameFrom(String hospitalNameFrom) {
		this.hospitalNameFrom = hospitalNameFrom;
	}

	public String getReplyNameTo() {
		return replyNameTo;
	}

	public void setReplyNameTo(String replyNameTo) {
		this.replyNameTo = replyNameTo;
	}

	public String getHospitalNameTo() {
		return hospitalNameTo;
	}

	public void setHospitalNameTo(String hospitalNameTo) {
		this.hospitalNameTo = hospitalNameTo;
	}

 
    
    
    
}
