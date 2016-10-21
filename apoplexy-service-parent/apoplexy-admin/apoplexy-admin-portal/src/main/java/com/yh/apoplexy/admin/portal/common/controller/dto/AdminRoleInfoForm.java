package com.yh.apoplexy.admin.portal.common.controller.dto;

/**
 * 角色信息表单
 * @author eyelake
 *
 */
public class AdminRoleInfoForm {
     private String roleName;
     
     private String pageNum;
     
     private String pageSize;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
     
     
}
