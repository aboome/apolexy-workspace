package com.yh.apoplexy.patient.open.request.health;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 查询健康数据趋势图 (pat-0009)请求
 * Created by wunder on 16/9/1 10:45.
 */
public class QueryHealthDataChartRequest implements Serializable {

    private static final long serialVersionUID = -2972903704432134463L;

    /**
     * 用户ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 开始时间
     */
    @StringValidator(nullable = false, pattern = RegularConstants.DATE_REGULAR)
    private String beginDate;

    /**
     * 结束时间
     */
    @StringValidator(nullable = false, pattern = RegularConstants.DATE_REGULAR)
    private String endDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
