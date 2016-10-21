package com.yh.apoplexy.assist.dto.doctor.cases.ast;

import com.yjh.framework.core.entity.Entity;

/**
 * 分页查询病例请求实体类
 * Created by wunder on 16/9/10 15:17.
 */
public class QueryAstCaseDto extends Entity {

    private static final long serialVersionUID = -5588258923345842113L;

    private Long userId;

    private String caseStatus;

    private String doctorStatus;

    private String videoType;

    private String collectionStatus;

    private String commentStatus;

    private String ctType;

    private String ctaType;

    private String ctpType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
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

    public String getCtType() {
        return ctType;
    }

    public void setCtType(String ctType) {
        this.ctType = ctType;
    }

    public String getCtaType() {
        return ctaType;
    }

    public void setCtaType(String ctaType) {
        this.ctaType = ctaType;
    }

    public String getCtpType() {
        return ctpType;
    }

    public void setCtpType(String ctpType) {
        this.ctpType = ctpType;
    }
}
