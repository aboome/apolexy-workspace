package com.yh.apoplexy.assist.dto.admin.doctor.cases.ast;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.entity.Entity;

public class AstHistoryDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4742272828544984904L;

	
	 
	private Long id;
	
	 
	private Long userId;
	
	 
	private Long recordId;
	
 
	private Long result;
	
 
	private String descOne;
	
	 
	private String descTwo;
	
 
	private Date createTime;
	
	private  String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

 
	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	public String getDescOne() {
		return descOne;
	}

	public void setDescOne(String descOne) {
		this.descOne = descOne;
	}

	public String getDescTwo() {
		return descTwo;
	}

	public void setDescTwo(String descTwo) {
		this.descTwo = descTwo;
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
	
	
}
