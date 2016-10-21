package com.yh.apoplexy.assist.dto.admin.doctor.cases.referral;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;
import java.util.List;

/**
 * Created by wunder on 16/9/21 10:00.
 */
public class AdminQueryReferralCaseDto extends Entity {

    private static final long serialVersionUID = 8883902976790172734L;

    private String doctorName;

    private String hospitalName;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;

    private List<String> caseStatus;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<String> getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(List<String> caseStatus) {
        this.caseStatus = caseStatus;
    }
}
