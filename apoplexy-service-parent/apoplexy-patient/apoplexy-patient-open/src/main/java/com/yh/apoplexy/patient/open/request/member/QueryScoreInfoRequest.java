package com.yh.apoplexy.patient.open.request.member;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 个人中心-查询积分信息(pat-0017)请求
 * Created by wunder on 16/9/1 13:58.
 */
public class QueryScoreInfoRequest implements Serializable {

    private static final long serialVersionUID = 658038484776061931L;

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
