package com.yh.apoplexy.doctor.open.dto.cases.referral;

import java.io.Serializable;

/**
 * 接诊医生信息实体项
 * Created by wunder on 16/9/1 19:35.
 */
public class ReceiveDoctorItem implements Serializable {

    private static final long serialVersionUID = 6459207818606530253L;

    /**
     * 接诊医生ID
     */
    private String doctorId;

    /**
     * 接诊医生名称
     */
    private String doctorName;

    /**
     * 接诊医生照片ID
     */
    private String doctorPhoto;

    /**
     * 所属医院
     */
    private String hospital;

    /**
     * 接诊时间
     */
    private String recvTime;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhoto() {
        return doctorPhoto;
    }

    public void setDoctorPhoto(String doctorPhoto) {
        this.doctorPhoto = doctorPhoto;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(String recvTime) {
        this.recvTime = recvTime;
    }
}
