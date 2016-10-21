/**
 * 
 */
package com.yh.apoplexy.admin.portal.system.vo;

import java.util.Date;
import java.util.List;

/**
 * 管理后台菜单视图
 * 
 * @author CC
 *
 */
public class MenuListVo {
	
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 菜单URL
	 */
	private String menuUrl;

	/**
	 * 菜单等级
	 */
	private String menuLevel;
	/**
	 * 父级ID
	 */
	private Long parentId;
    /**
     * 开始时间
     */
	private Date createTime;
    /**
     * 最后更新时间
     */
	private Date lastUpdateTime;
   /**
    * 状态
    */
	private String status;
	
	/**
	 * 排序
	 */
	private Long sort;
	
	/**
	 * 二级菜单列表
	 */
	private List<SecondMenuVo> secondMenuList;

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
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}

	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * @return the menuLevel
	 */
	public String getMenuLevel() {
		return menuLevel;
	}

	/**
	 * @param menuLevel the menuLevel to set
	 */
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
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
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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
	 * @return the sort
	 */
	public Long getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}

	/**
	 * @return the secondMenuList
	 */
	public List<SecondMenuVo> getSecondMenuList() {
		return secondMenuList;
	}

	/**
	 * @param secondMenuList the secondMenuList to set
	 */
	public void setSecondMenuList(List<SecondMenuVo> secondMenuList) {
		this.secondMenuList = secondMenuList;
	}

	
}
