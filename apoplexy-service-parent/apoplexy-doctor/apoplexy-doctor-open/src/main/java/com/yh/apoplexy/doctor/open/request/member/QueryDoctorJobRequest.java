package com.yh.apoplexy.doctor.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 个人中心-查询工作岗位信息(doc-0054)请求
 * Created by wunder on 16/9/2 15:39.
 */
public class QueryDoctorJobRequest implements Serializable {

    private static final long serialVersionUID = 2420069645988647397L;

    /**
     * 用户ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
