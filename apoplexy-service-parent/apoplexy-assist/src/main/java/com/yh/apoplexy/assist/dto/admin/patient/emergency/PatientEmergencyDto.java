package com.yh.apoplexy.assist.dto.admin.patient.emergency;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * Created by wunder on 2016/10/17 08:33.
 */
public class PatientEmergencyDto extends Entity{

    private static final long serialVersionUID = -3381786731809633358L;

    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 年龄
     */
    private Long age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 首次发病时间
     */
    private Date onsetTime;

    /**
     * 脸
     */
    private String face;

    /**
     * 手臂
     */
    private String arm;

    /**
     * 语言
     */
    private String speech;

    /**
     * 创建时间
     */
    private Date createTime;

    private String area;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getOnsetTime() {
        return onsetTime;
    }

    public void setOnsetTime(Date onsetTime) {
        this.onsetTime = onsetTime;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getArm() {
        return arm;
    }

    public void setArm(String arm) {
        this.arm = arm;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
