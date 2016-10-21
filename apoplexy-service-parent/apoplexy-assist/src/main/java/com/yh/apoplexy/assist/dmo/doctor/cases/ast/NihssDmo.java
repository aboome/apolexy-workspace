package com.yh.apoplexy.assist.dmo.doctor.cases.ast;
import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
/**
 * 
 * 基线nihss信息表
 */
import com.yjh.framework.core.entity.Entity;


@Table(name = "t_nihss_info")
public class NihssDmo extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3899081094499208771L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "type")
	private String type;
	
	
	@Column(name = "user_id")
	private Long userId;
	
	
	@Column(name = "record_id")
	private Long recordId;
	
	@Column(name = "index")
	private Long index;
	
	@Column(name = "result")
	private Long result;
	
	@Column(name = "create_time")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
   
}
