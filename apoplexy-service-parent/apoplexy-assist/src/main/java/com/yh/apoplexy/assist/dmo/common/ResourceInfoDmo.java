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
 * 资源实体信息
 * 
 * @author CC
 *
 */
@Table(name = "t_resources")
public class ResourceInfoDmo extends Entity {

	private static final long serialVersionUID = -2217004103734747133L;

	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Long id;
	
    /**
     * 资源类型,0:图片，1：视频，2：PDF
     */
    @Column(name = "resource_type")
	private String resourceType;
	
    /**
     * 资源名称
     */
    @Column(name = "resource_name")
	private String resourceName;
	
    /**
     * 资源唯一标示
     */
    @Column(name = "resource_uuid")
	private String resourceUuid;
	
    /**
     * 创建时间
     */
    @Column(name = "create_time")
	private Date createTime;
	
    /**
     * 状态，00：正常，90：删除
     */
    @Column(name = "status")
	private String status;
	
    /**
     * 归属，0：公用，1：医生，2：患者
     */
    @Column(name = "owner")
	private String owner;

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
	 * @return the resourceType
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * @param resourceType the resourceType to set
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @return the resourceUuid
	 */
	public String getResourceUuid() {
		return resourceUuid;
	}

	/**
	 * @param resourceUuid the resourceUuid to set
	 */
	public void setResourceUuid(String resourceUuid) {
		this.resourceUuid = resourceUuid;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
    
    
}
