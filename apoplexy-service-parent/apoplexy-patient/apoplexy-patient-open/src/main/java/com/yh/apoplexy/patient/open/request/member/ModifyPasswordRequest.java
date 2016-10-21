package com.yh.apoplexy.patient.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 个人中心-修改密码(pat-0019)请求
 * Created by wunder on 16/9/1 14:31.
 */
public class ModifyPasswordRequest implements Serializable {

    private static final long serialVersionUID = 7365391390208713441L;

    /**
     * 用户ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 旧密码，经过MD5加密后发送到后台
     */
    @StringValidator(nullable = false)
    private String oldPassword;

    /**
     * 新密码，经过MD5加密后发送到后台
     */
    @StringValidator(nullable = false)
    private String newPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
