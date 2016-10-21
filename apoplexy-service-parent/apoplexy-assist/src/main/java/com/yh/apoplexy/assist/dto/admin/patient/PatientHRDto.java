package com.yh.apoplexy.assist.dto.admin.patient;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;
/**
 * 
 * 显示高危筛查详情,这里显示的是个人的信息
 * @author zhangbiao
 *
 */
public class PatientHRDto extends Entity {
	
	private static final long serialVersionUID = 6579401772250089473L;
	
    private Long id;
    
	private Long recordId;
	
	private String type;
	
    private String question;
    
    private String yesResult;
    
    private String noResult;
    //问题的答案
    private String result;
    
    private String status;
    
    private Date createTime;
    
    

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getYesResult() {
		return yesResult;
	}

	public void setYes_Result(String yesResult) {
		this.yesResult = yesResult;
	}

	public String getNoResult() {
		return noResult;
	}

	public void setNo_Result(String noResult) {
		this.noResult = noResult;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	 

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 

	 
	public void setYesResult(String yesResult) {
		this.yesResult = yesResult;
	}

	public void setNoResult(String noResult) {
		this.noResult = noResult;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
	
	
    
   
}
