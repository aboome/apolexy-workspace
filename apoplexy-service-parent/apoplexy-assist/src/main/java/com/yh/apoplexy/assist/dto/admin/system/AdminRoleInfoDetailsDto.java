package com.yh.apoplexy.assist.dto.admin.system;

import com.yjh.framework.core.entity.Entity;

/**
 * 查询角色详情
 * @author eyelake
 *
 */
public class AdminRoleInfoDetailsDto extends Entity {

    private static final long serialVersionUID = -5742842375926076777L;
    /**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单描述
	 */
	private String menuUrl;
	/**
	 * 菜单等级
	 */
	private String menuLevel;
	/**
	 * 父级id
	 */
	private String parentId;
	/**
	 * 状态
	 */
	private String status;


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
