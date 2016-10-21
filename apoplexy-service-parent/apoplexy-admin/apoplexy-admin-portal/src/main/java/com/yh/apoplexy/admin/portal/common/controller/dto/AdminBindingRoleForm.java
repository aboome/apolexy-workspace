package com.yh.apoplexy.admin.portal.common.controller.dto;

/**
 * 绑定角色
 *
 * @author eyelake
 */
public class AdminBindingRoleForm {

    private String userId;

    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


}
