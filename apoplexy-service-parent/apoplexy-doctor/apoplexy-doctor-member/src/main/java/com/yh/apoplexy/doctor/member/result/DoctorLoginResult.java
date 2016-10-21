package com.yh.apoplexy.doctor.member.result;

import com.yjh.framework.lang.Result;

/**
 * Created by wunder on 16/9/27 10:13.
 */
public class DoctorLoginResult extends Result{

    private static final long serialVersionUID = -2668781582175880120L;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
