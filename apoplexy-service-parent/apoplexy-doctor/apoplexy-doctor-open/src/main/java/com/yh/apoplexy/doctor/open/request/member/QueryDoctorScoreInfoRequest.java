package com.yh.apoplexy.doctor.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 个人中心-积分详情(doc-0056)请求
 * Created by wunder on 16/9/2 15:46.
 */
public class QueryDoctorScoreInfoRequest implements Serializable {

    private static final long serialVersionUID = 5185680248023926180L;

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
