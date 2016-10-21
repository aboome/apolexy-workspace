package com.yh.apoplexy.doctor.cases.referral.result;

import com.yjh.framework.lang.Result;

/**
 * 转诊病例访问权限结果
 * Created by wunder on 16/9/8 10:29.
 */
public class ReferralCasePermissionResult extends Result {

    private static final long serialVersionUID = -5307451325678788878L;

    /**
     * 病例发布医生id
     */
    private Long postDoctorId;

    /**
     * 访问医生姓名
     */
    private String doctorName;

    /**
     * 转诊状态
     */
    private String referralStatus;

    /**
     * 转诊标题
     */
    private String title;

    public Long getPostDoctorId() {
        return postDoctorId;
    }

    public void setPostDoctorId(Long postDoctorId) {
        this.postDoctorId = postDoctorId;
    }

    public String getReferralStatus() {
        return referralStatus;
    }

    public void setReferralStatus(String referralStatus) {
        this.referralStatus = referralStatus;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
