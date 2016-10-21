package com.yh.apoplexy.assist.dto.admin;

import com.yjh.framework.core.entity.Entity;

/**
 * 登录请求
 * Created by wunder on 16/9/2 21:28.
 */
public class LoginDto extends Entity {

    private static final long serialVersionUID = 8089257179924691531L;

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
