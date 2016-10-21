package com.yh.apoplexy.patient.member.result;

import com.yjh.framework.lang.Result;

/**
 * 患者登录结果
 * Created by wunder on 16/9/4 15:38.
 */
public class PatientLoginResult extends Result {

    private static final long serialVersionUID = 8683744051232762962L;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
