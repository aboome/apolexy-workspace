package com.yh.apoplexy.doctor.open.dto.cases.ast;

import java.io.Serializable;
import java.util.List;

/**
 * AST评论实体类
 * Created by wunder on 16/9/11 10:16.
 */
public class AstCommentItem implements Serializable {

    private static final long serialVersionUID = -2275603370115782180L;

    /**
     * 评论ID
     */
    private String discussId;

    /**
     * 医生ID
     */
    private String doctorId;

    /**
     * 医生名称
     */
    private String doctorName;

    /**
     * 医院
     */
    private String hospital;

    /**
     * 评论时间
     */
    private String discussTime;

    /**
     * 医生照片
     */
    private String doctorPhoto;

    /**
     * 评论内容
     */
    private String discussContent;

    /**
     * 子评论列表
     */
    private List<ChildAstCommentItem> childCommentList;

    public String getDiscussId() {
        return discussId;
    }

    public void setDiscussId(String discussId) {
        this.discussId = discussId;
    }

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

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDiscussTime() {
        return discussTime;
    }

    public void setDiscussTime(String discussTime) {
        this.discussTime = discussTime;
    }

    public String getDoctorPhoto() {
        return doctorPhoto;
    }

    public void setDoctorPhoto(String doctorPhoto) {
        this.doctorPhoto = doctorPhoto;
    }

    public String getDiscussContent() {
        return discussContent;
    }

    public void setDiscussContent(String discussContent) {
        this.discussContent = discussContent;
    }

    public List<ChildAstCommentItem> getChildCommentList() {
        return childCommentList;
    }

    public void setChildCommentList(List<ChildAstCommentItem> childCommentList) {
        this.childCommentList = childCommentList;
    }
}
