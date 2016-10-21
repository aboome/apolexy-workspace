/**
 * 
 */
package com.yh.apoplexy.assist.dmo.common;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;

/**
 * 区域实体
 * 
 * @author CC
 *
 */
@Table(name = "t_area_info")
public class AreaCodeDmo extends Entity {

	private static final long serialVersionUID = 5540014600484827360L;

	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Long id;
	
    /**
     * 区域名称
     */
    @Column(name = "area_name")
	private String areaName;
	
    /**
     * 区域编号
     */
    @Column(name = "area_code")
	private String areaCode;
	
    /**
     * 省份
     */
    @Column(name = "province")
	private String province;
	
    /**
     * 城市
     */
    @Column(name = "city")
	private String city;
	
    /**
     * 区域等级
     */
    @Column(name = "level")
	private Long level;
	
    /**
     * 父区域ID
     */
    @Column(name = "parent_id")
	private Long parentId;

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the level
	 */
	public Long getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Long level) {
		this.level = level;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

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
	
	
}
