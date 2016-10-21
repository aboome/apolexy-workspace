package com.yh.apoplexy.admin.portal.common.controller.dto;
/**
 * 查询菜单表单
 * @author eyelake
 *
 */
public class AdminMenuForm {
   
	private String menuLevel;
	
	private String pageSize;
	
	private String pageNum;

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	
	
	
}
