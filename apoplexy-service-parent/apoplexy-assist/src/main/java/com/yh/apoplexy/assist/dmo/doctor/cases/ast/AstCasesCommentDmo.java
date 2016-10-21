package com.yh.apoplexy.assist.dmo.doctor.cases.ast;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;

/**
 * AST病例评论表
 */
@Table(name = "t_doctor_ast_comment")
public class AstCasesCommentDmo extends Entity {

    private static final long serialVersionUID = 3965838683298722382L;

    /**
     * 主键
     */
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	/**
	 * 发起回复用户ID
	 */
	@Column(name = "from_user_id")
	private Long fromUserId;
	
	/**
	 * 收到回复用户ID
	 */
	@Column(name = "to_user_id")
	private Long toUserId;
	
	/**
	 * ast病例id
	 */
	@Column(name = "record_id")
	private Long recordId;
	
	/**
	 *内容
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 *类型
	 */
	@Column(name = "type")
	private String  type;
	
	/**
	 * 父评论id
	 */
	@Column(name = "parent_id")
	private Long parentId;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 最后更新时间
	 */
	@Column(name = "last_update_time")
	private Date lastUpdateTime;
	
	/**
	 * 回复的状态
	 */
	@Column(name = "status")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
	
	
	
}
