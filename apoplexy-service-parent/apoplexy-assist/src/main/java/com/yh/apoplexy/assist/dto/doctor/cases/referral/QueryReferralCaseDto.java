package com.yh.apoplexy.assist.dto.doctor.cases.referral;

import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * 分页查询转诊病例请求实体类
 * Created by wunder on 16/9/8 10:24.
 */
public class QueryReferralCaseDto extends Entity {

    private static final long serialVersionUID = -9058764619812457790L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 转诊病例发布范围
     */
    private String caseRange;

    private String allRange;

    /**
     * 转诊病例状态
     */
    private List<String> caseStatus;

    /**
     * 医生状态
     */
    private String doctorStatus;

    /**
     * 视频类型
     */
    private String videoType;

    /**
     * 图片类型
     */
    private String imageType;

    /**
     * NIHSS类型
     */
    private String nihssType;

    /**
     * 意向接诊信息状态
     */
    private String receiveStatus;

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

    public List<String> getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(List<String> caseStatus) {
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

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getNihssType() {
        return nihssType;
    }

    public void setNihssType(String nihssType) {
        this.nihssType = nihssType;
    }

    public String getAllRange() {
        return allRange;
    }

    public void setAllRange(String allRange) {
        this.allRange = allRange;
    }

    public String getDoctorRange() {
        return doctorRange;
    }

    public void setDoctorRange(String doctorRange) {
        this.doctorRange = doctorRange;
    }
}
