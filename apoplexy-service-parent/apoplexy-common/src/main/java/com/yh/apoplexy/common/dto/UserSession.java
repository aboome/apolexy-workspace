package com.yh.apoplexy.common.dto;

import java.io.Serializable;

/**
 * 用户信息相关的session
 * 
 */
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1356803237527761369L;

    /** 用户Id */
    private String            userId;

    /** 用户名 */
    private String            name;

    /** 用户角色 */
    private String            roleId;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /** 按钮权限 */
    // private List<String> permissionCode;TODO:等页面需要用到权限编码时再使用该属性

}
