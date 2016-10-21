package com.yh.apoplexy.doctor.open.response.member;

import java.io.Serializable;

/**
 * 个人中心-查询个人基础资料(doc-0053)响应
 * Created by wunder on 16/9/2 15:36.
 */
public class QueryDoctorBaseInfoResponse implements Serializable {

    private static final long serialVersionUID = 8872325157398188591L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 个人头像ID
     */
    private String photo;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别(0：男;1：女)
     */
    private String sex;

    /**
     * 所属医院
     */
    private String hospital;

    /**
     * 医院级别(0：国家级医院;1：市级医院;2：县级医院;3：乡镇医院)
     */
    private String hospitalLevel;

    /**
     * 科室
     */
    private String department;

    /**
     * 职称
     */
    private String title;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
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
}
