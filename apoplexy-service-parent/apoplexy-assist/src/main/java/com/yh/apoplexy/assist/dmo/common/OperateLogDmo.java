/**
 * 
 */
package com.yh.apoplexy.assist.dmo.common;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;

/**
 * 操作日志实体
 * 
 * @author CC
 *
 */
@Table(name = "t_operator_log")
public class OperateLogDmo extends Entity {

	private static final long serialVersionUID = -7943704828407680323L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	/**
	 * 操作模块
	 */
	@Column(name = "model")
	private String model;
	
	/**
	 * 操作模块名称
	 */
	@Column(name = "model_name")
	private String modelName;
	
	/**
	 * 操作时间
	 */
	@Column(name = "operate_time")
	private Date operateTime;
	
	/**
	 * 操作枚举
	 */
	@Column(name = "operate_enum")
	private String operateEnum;
	
	/**
	 * 操作描述
	 */
	@Column(name = "operate_desc")
	private String operateDesc;
	
	/**
	 * 操作用户ID
	 */
	@Column(name = "operate_user_id")
	private Long operateUserId;
	
	/**
	 * 操作用户名
	 */
	@Column(name = "operate_user_name")
	private String operateUserName;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	/**
	 * @return the operateEnum
	 */
	public String getOperateEnum() {
		return operateEnum;
	}

	/**
	 * @param operateEnum the operateEnum to set
	 */
	public void setOperateEnum(String operateEnum) {
		this.operateEnum = operateEnum;
	}

	/**
	 * @return the operateDesc
	 */
	public String getOperateDesc() {
		return operateDesc;
	}

	/**
	 * @param operateDesc the operateDesc to set
	 */
	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}

	/**
	 * @return the operateTime
	 */
	public Date getOperateTime() {
		return operateTime;
	}

	/**
	 * @param operateTime the operateTime to set
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	/**
	 * @return the operateUserId
	 */
	public Long getOperateUserId() {
		return operateUserId;
	}

	/**
	 * @param operateUserId the operateUserId to set
	 */
	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}

	/**
	 * @return the operateUserName
	 */
	public String getOperateUserName() {
		return operateUserName;
	}

	/**
	 * @param operateUserName the operateUserName to set
	 */
	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}
	
	

}
