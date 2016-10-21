package com.yh.apoplexy.assist.dmo.doctor.cases.discuss;
/**
 * 评论回复实体，病例讨论评论表
 */
import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;
@Table(name = "t_doctor_case_comment")
public class CasesReplyDmo extends Entity {

	private static final long serialVersionUID = 2145388000311170885L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	/**
	 * 回复的发起者ID
	 */
	@Column(name = "from_user_id")
	private Long formUserId;
	
	/**
	 * 回复的接受者ID
	 */
	@Column(name = "to_user_id")
	private Long toUserId;
	
	/**
	 * 病例讨论id
	 */
	@Column(name = "record_id")
	private Long recordId;
     
	/**
	 * 评论的内容和回复的内容
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 * 评论的类型
	 */
	@Column(name = "type")
	private String type;
	
	/**
	 * 父ID
	 */
	@Column(name = "parent_id")
	private Long parentId;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createtime;
	
	/**
	 * 最后更新时间
	 */
	@Column(name = "last_update_time")
	private Date lastUpdateTime;
	
	/**
	 * 评论的状态
	 */
	@Column(name = "status")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFormUserId() {
		return formUserId;
	}

	public void setFormUserId(Long formUserId) {
		this.formUserId = formUserId;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
