package com.yh.apoplexy.assist.dto.admin.system;

import java.util.List;

import com.yjh.framework.core.entity.Entity;

public class AdminAddRoleInfoListDto extends Entity {

    private static final long serialVersionUID = 390988199031378644L;

    private List<AdminAddRoleInfoDto> addRoleInfoList;

	public List<AdminAddRoleInfoDto> getAddRoleInfoList() {
		return addRoleInfoList;
	}

	public void setAddRoleInfoList(List<AdminAddRoleInfoDto> addRoleInfoList) {
		this.addRoleInfoList = addRoleInfoList;
	}
	
	
}
