package com.yh.apoplexy.doctor.cases.referral.dto;

import com.yjh.framework.lang.Request;

/**
 * 确认接诊实体类
 * Created by wunder on 16/9/10 12:49.
 */
public class ConfirmReferralDto extends Request {

    private static final long serialVersionUID = -3953290580654497062L;

    /**
     * 发布转诊申请医生id
     */
    private Long userId;

    /**
     * 申请记录id
     */
    private Long recordId;

    /**
     * 意向接诊医生id
     */
    private Long toDoctorId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getToDoctorId() {
        return toDoctorId;
    }

    public void setToDoctorId(Long toDoctorId) {
        this.toDoctorId = toDoctorId;
    }
}
