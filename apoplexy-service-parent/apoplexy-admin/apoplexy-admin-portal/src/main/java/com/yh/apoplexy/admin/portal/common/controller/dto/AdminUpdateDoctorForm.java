package com.yh.apoplexy.admin.portal.common.controller.dto;

import java.util.Date;

/**
 * 修改医生信息表单
 *
 * @author eyelake
 */
public class AdminUpdateDoctorForm {

    private String id;

    private String doctorName;

    private String hospital;

    private String department;

    private String title;

    private String job;

    private String effectiveTime;

    private String phone;

    private String email;

    private String sex;

    private String avatar;
    
    private String modifyHospitalId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

	/**
	 * @return the modifyHospitalId
	 */
	public String getModifyHospitalId() {
		return modifyHospitalId;
	}

	/**
	 * @param modifyHospitalId the modifyHospitalId to set
	 */
	public void setModifyHospitalId(String modifyHospitalId) {
		this.modifyHospitalId = modifyHospitalId;
	}
    
    
}
