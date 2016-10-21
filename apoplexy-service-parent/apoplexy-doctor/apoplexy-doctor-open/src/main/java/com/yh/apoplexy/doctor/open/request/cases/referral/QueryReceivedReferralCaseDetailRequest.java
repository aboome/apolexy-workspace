package com.yh.apoplexy.doctor.open.request.cases.referral;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 我的-已被接诊详情(doc-0029)请求
 * Created by wunder on 16/9/1 20:20.
 */
public class QueryReceivedReferralCaseDetailRequest implements Serializable {

    private static final long serialVersionUID = 4356522752196025977L;

    /**
     * 转诊记录ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String recordId;

    /**
     * 用户id
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
