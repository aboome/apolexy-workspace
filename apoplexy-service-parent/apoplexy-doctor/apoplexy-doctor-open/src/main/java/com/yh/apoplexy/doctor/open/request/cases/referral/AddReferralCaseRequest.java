package com.yh.apoplexy.doctor.open.request.cases.referral;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralCaseItem;

import java.io.Serializable;

/**
 * 新增转诊病例(doc-0020)请求
 * Created by wunder on 16/9/1 19:41.
 */
public class AddReferralCaseRequest extends ReferralCaseItem {

    private static final long serialVersionUID = 898121123657424336L;

    /**
     * 医生ID
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
