package com.yh.apoplexy.assist.dto.doctor.member;


import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * 医生会员信息实体类
 * Created by wunder on 16/9/6 19:22.
 */
public class DoctorMemberDto extends Entity {

    private static final long serialVersionUID = 9064752314878117397L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 医生姓名
     */
    private String doctorName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所属医院
     */
    private String hospital;

    /**
     * 科室
     */
    private String department;

    /**
     * 职称
     */
    private String title;

    /**
     * 岗位
     */
    private String job;

    /**
     * 评分
     */
    private Long score;

    /**
     * 星级
     */
    private Long starLevel;

    /**
     * 岗位有效时间
     */
    private Date effectiveTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 最后一次更新时间
     */
    private Date lastUpdateTime;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 医院等级
     */
    private Long hospitalLevel;

    /**
     * 所属医院id
     */
    private Long hospitalId;

    /**
     * 顶级所属医院
     */
    private Long rootHospitalId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Long starLevel) {
        this.starLevel = starLevel;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(Long hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public Long getRootHospitalId() {
        return rootHospitalId;
    }

    public void setRootHospitalId(Long rootHospitalId) {
        this.rootHospitalId = rootHospitalId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }
}
