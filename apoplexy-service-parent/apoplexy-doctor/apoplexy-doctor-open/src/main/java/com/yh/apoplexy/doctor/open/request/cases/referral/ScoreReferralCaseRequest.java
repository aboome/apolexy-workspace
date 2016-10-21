package com.yh.apoplexy.doctor.open.request.cases.referral;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 我的-我的接诊详情-评分(doc-0032)请求
 * Created by wunder on 16/9/1 20:30.
 */
public class ScoreReferralCaseRequest implements Serializable {

    private static final long serialVersionUID = -7685571753210866242L;

    /**
     * 申请医生ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 接诊记录ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String recordId;

    /**
     * 转诊医生ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String doctorId;

    /**
     * 病例完整度，1~5
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String caseIntegrity;

    /**
     * 病例详细度
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String caseDetail;

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

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getCaseIntegrity() {
        return caseIntegrity;
    }

    public void setCaseIntegrity(String caseIntegrity) {
        this.caseIntegrity = caseIntegrity;
    }

    public String getCaseDetail() {
        return caseDetail;
    }

    public void setCaseDetail(String caseDetail) {
        this.caseDetail = caseDetail;
    }
}
