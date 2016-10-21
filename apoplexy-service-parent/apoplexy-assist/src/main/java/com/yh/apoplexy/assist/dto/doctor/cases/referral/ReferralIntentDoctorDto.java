package com.yh.apoplexy.assist.dto.doctor.cases.referral;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * 转诊意向接诊医生实体类
 * Created by wunder on 16/9/9 14:03.
 */
public class ReferralIntentDoctorDto extends Entity {

    private static final long serialVersionUID = -5962913449141646747L;

    /**
     * 医生Id
     */
    private Long doctorId;

    /**
     * 医生姓名
     */
    private String doctorName;

    /**
     * 医生头像
     */
    private String doctorPhoto;

    /**
     * 医院名称
     */
    private String hospital;

    /**
     * 意向接诊时间
     */
    private Date recvTime;

    /**
     * 客户端id
     */
    private String clientId;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
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

    public Date getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(Date recvTime) {
        this.recvTime = recvTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
