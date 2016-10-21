package com.yh.apoplexy.assist.dto.admin.doctor.cases.ast;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * Created by wunder on 16/9/27 14:13.
 */
public class AdminChildAstCommentDto extends Entity {

    private static final long serialVersionUID = -7704416567641384729L;

    /**
     * 评论ID
     */
    private String discussId;

    /**
     * 发起回复医生
     */
    private String fromDoctor;

    /**
     * 发起回复医生ID
     */
    private String fromDoctorId;

    /**
     * 收到回复医生
     */
    private String toDoctor;

    /**
     * 收到回复医生ID
     */
    private String toDoctorId;

    /**
     * 回复内容
     */
    private String discussContent;

    /**
     * 评论时间
     */
    private Date discussTime;

    public String getDiscussId() {
        return discussId;
    }

    public void setDiscussId(String discussId) {
        this.discussId = discussId;
    }

    public String getFromDoctor() {
        return fromDoctor;
    }

    public void setFromDoctor(String fromDoctor) {
        this.fromDoctor = fromDoctor;
    }

    public String getFromDoctorId() {
        return fromDoctorId;
    }

    public void setFromDoctorId(String fromDoctorId) {
        this.fromDoctorId = fromDoctorId;
    }

    public String getToDoctor() {
        return toDoctor;
    }

    public void setToDoctor(String toDoctor) {
        this.toDoctor = toDoctor;
    }

    public String getToDoctorId() {
        return toDoctorId;
    }

    public void setToDoctorId(String toDoctorId) {
        this.toDoctorId = toDoctorId;
    }

    public String getDiscussContent() {
        return discussContent;
    }

    public void setDiscussContent(String discussContent) {
        this.discussContent = discussContent;
    }

    public Date getDiscussTime() {
        return discussTime;
    }

    public void setDiscussTime(Date discussTime) {
        this.discussTime = discussTime;
    }
}
