package com.yh.apoplexy.assist.dto.admin.patient.health;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * 健康筛查实体类
 * Created by wunder on 16/9/19 20:00.
 */
public class PatientScreenDto extends Entity {

    private static final long serialVersionUID = -5185951498332497561L;

    private Long id;

    private String userName;

    private Long userId;

    private Long age;

    private String sex;

    private Long incidence;

    private Date createTime;

    private String area;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getIncidence() {
        return incidence;
    }

    public void setIncidence(Long incidence) {
        this.incidence = incidence;
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
