package com.yh.apoplexy.assist.dto.doctor.cases.discuss;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.entity.Entity;
/**
 * 
 * Ast既往用药史
 * @author zhangbiao
 *
 */
public class AstMedicationDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8050638339434723874L;
	
    private Long id;
	
	 
	private Long userId;
	
	 
	private Long recordId;
	
	 
 
	
	 
	private Long result;
	
	 
	private String description;
	
	 
	private Date createTime;
	
	private String content;

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

	 

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
