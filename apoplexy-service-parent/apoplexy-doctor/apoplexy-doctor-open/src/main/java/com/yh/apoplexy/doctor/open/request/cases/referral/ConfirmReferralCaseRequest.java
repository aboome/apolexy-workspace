package com.yh.apoplexy.doctor.open.request.cases.referral;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 我的-我的申请详情-确认转诊(doc-0026)请求
 * Created by wunder on 16/9/1 20:08.
 */
public class ConfirmReferralCaseRequest implements Serializable {

    private static final long serialVersionUID = 9195760630474474620L;

    /**
     * 申请医生ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 申请记录ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String recordId;

    /**
     * 转诊到医生ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String toDoctorId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getToDoctorId() {
        return toDoctorId;
    }

    public void setToDoctorId(String toDoctorId) {
        this.toDoctorId = toDoctorId;
    }
}
