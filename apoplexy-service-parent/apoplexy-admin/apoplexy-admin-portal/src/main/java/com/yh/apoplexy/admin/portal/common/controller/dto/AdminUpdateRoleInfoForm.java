package com.yh.apoplexy.admin.portal.common.controller.dto;

import java.util.List;

import com.yh.apoplexy.assist.dto.admin.system.AdminAddRoleInfDto;

/**
 * 修改角色
 *
 * @author eyelake
 */
public class AdminUpdateRoleInfoForm {

    private String id;

    private String roleName;

    private String description;

    private List<AdminAddRoleInfDto> addRoleInfList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
