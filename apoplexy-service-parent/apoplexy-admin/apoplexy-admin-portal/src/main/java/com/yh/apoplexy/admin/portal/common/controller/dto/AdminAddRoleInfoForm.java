package com.yh.apoplexy.admin.portal.common.controller.dto;

import java.util.List;

import com.yh.apoplexy.assist.dto.admin.system.AdminAddRoleInfDto;

/**
 * 新增角色表单
 *
 * @author eyelake
 */
public class AdminAddRoleInfoForm {

    private String roleName;

    private String description;

    private List<AdminAddRoleInfDto> addRoleInfList;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<AdminAddRoleInfDto> getAddRoleInfList() {
        return addRoleInfList;
    }

    public void setAddRoleInfList(List<AdminAddRoleInfDto> addRoleInfList) {
        this.addRoleInfList = addRoleInfList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
