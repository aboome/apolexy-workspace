package com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss;

import java.util.Date;
import java.util.List;

import com.yjh.framework.core.entity.Entity;

public class AdminQueryDiscussCaseDto extends Entity {

    private static final long serialVersionUID = 2029253274804095759L;

    private String doctorName;

    private String hospitalName;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;

    private String caseStatus;

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

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }
}
