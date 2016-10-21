package com.yh.apoplexy.assist.dto.doctor.cases.ast;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;
import java.util.List;

/**
 * AST病例评论详情实体类
 * Created by wunder on 16/9/11 10:24.
 */
public class AstCommentDto extends Entity {

    private static final long serialVersionUID = 6299808706746484507L;

    /**
     * 评论ID
     */
    private Long discussId;

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
    private Date discussTime;

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
    private List<ChildAstCommentDto> childDiscussList;

    public Long getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Long discussId) {
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

    public Date getDiscussTime() {
        return discussTime;
    }

    public void setDiscussTime(Date discussTime) {
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

    public List<ChildAstCommentDto> getChildDiscussList() {
        return childDiscussList;
    }

    public void setChildDiscussList(List<ChildAstCommentDto> childDiscussList) {
        this.childDiscussList = childDiscussList;
    }
}
