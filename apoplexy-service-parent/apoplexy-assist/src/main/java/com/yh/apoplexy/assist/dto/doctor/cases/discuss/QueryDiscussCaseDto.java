package com.yh.apoplexy.assist.dto.doctor.cases.discuss;

import com.yjh.framework.core.entity.Entity;

/**
 * 分页查询讨论病例请求实体类
 * Created by wunder on 16/9/7 10:12.
 */
public class QueryDiscussCaseDto extends Entity {

    private static final long serialVersionUID = 1994134954591537199L;

    private Long userId;

    private String caseRange;

    private String caseStatus;

    private String doctorStatus;

    private String videoType;

    private String collectionStatus;

    private String commentStatus;

    private String imageType;

    private String doctorRange;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCaseRange() {
        return caseRange;
    }

    public void setCaseRange(String caseRange) {
        this.caseRange = caseRange;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getDoctorStatus() {
        return doctorStatus;
    }

    public void setDoctorStatus(String doctorStatus) {
        this.doctorStatus = doctorStatus;
    }

    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getDoctorRange() {
        return doctorRange;
    }

    public void setDoctorRange(String doctorRange) {
        this.doctorRange = doctorRange;
    }
}
