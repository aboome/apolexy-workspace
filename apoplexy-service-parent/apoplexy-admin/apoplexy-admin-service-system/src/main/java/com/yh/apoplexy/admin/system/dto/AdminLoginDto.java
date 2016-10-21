package com.yh.apoplexy.admin.system.dto;

import com.yjh.framework.lang.Request;

/**
 * admin会员登录请求实体
 * Created by wunder on 2016/10/13 23:27.
 */
public class AdminLoginDto extends Request {

    private static final long serialVersionUID = -584220288221029968L;

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
