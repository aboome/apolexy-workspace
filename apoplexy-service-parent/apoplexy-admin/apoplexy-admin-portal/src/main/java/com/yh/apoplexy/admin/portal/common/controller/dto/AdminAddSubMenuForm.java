package com.yh.apoplexy.admin.portal.common.controller.dto;
/**
 * 新增子菜单
 * @author eyelake
 *
 */
public class AdminAddSubMenuForm {
       
	private String parentId;
	
	private String menuName;
	
	private String menuUrl;
	
	private String sort;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
}
