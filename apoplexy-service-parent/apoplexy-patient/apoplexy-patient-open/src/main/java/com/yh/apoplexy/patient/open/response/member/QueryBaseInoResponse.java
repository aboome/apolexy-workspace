package com.yh.apoplexy.patient.open.response.member;

import java.io.Serializable;

/**
 * 个人中心-查询基本资料(pat-0015)响应
 * Created by wunder on 16/9/1 13:49.
 */
public class QueryBaseInoResponse implements Serializable {

    private static final long serialVersionUID = 2336665762684512893L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 性别(0：男;1：女)
     */
    private String sex;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
