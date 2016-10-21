package com.yh.apoplexy.assist.dto.admin.system;

import com.yjh.framework.core.entity.Entity;

/**
 * 根据用户ID、菜单ID查询子菜单实体
 * @author CC
 *
 */
public class AdminSubMenuDto extends Entity {

	private static final long serialVersionUID = 6032693426508630978L;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 菜单等级
	 */
	private String menuLevel;

	/**
	 * 一级菜单ID
	 */
	private Long firstMenuId;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	 * @return the firstMenuId
	 */
	public Long getFirstMenuId() {
		return firstMenuId;
	}

	/**
	 * @param firstMenuId the firstMenuId to set
	 */
	public void setFirstMenuId(Long firstMenuId) {
		this.firstMenuId = firstMenuId;
	}


}
