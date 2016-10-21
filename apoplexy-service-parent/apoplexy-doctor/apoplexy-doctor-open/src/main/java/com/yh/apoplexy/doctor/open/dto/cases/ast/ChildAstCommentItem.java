package com.yh.apoplexy.doctor.open.dto.cases.ast;

import java.io.Serializable;

/**
 * AST子评论实体类
 * Created by wunder on 16/9/11 10:15.
 */
public class ChildAstCommentItem implements Serializable {

    private static final long serialVersionUID = 2553154005472392449L;

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
}
