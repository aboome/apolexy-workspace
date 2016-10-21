package com.yh.apoplexy.assist.dto.doctor.cases.referral;

import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * 查询意向转诊医生实体类
 * Created by wunder on 16/9/9 14:13.
 */
public class QueryIntentDoctorDto extends Entity {

    private static final long serialVersionUID = -3089424182126860758L;

    private Long userId;

    /**
     * 记录id
     */
    private Long recordId;

    /**
     * 转诊意向状态
     */
    private List<String> receiveStatus;

    /**
     * 医生状态
     */
    private String doctorStatus;

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

    public List<String> getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(List<String> receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getDoctorStatus() {
        return doctorStatus;
    }

    public void setDoctorStatus(String doctorStatus) {
        this.doctorStatus = doctorStatus;
    }
}
