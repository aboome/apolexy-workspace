package com.yh.apoplexy.assist.dto.admin.system;

import com.yjh.framework.core.entity.Entity;

/**
 * 修改角色
 * 
 * @author eyelake
 * 
 */
public class AdminUpdateRoleInfoDto extends Entity {
	/**
	 * 角色id
	 */
	private String userId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色描述
	 */
	private String desc;
	/**
	 * 菜单id
	 */
	private String menuId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
