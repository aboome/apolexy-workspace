package com.yh.apoplexy.admin.portal.common.controller.dto;

/**
 * Created by wunder on 2016/10/17 00:44.
 */
public class ModifyAdminPasswordForm {

    private String oldPassword;

    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
