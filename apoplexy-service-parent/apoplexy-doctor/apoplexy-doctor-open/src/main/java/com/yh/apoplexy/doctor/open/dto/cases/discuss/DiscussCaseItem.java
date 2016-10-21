package com.yh.apoplexy.doctor.open.dto.cases.discuss;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;
import java.util.List;

/**
 * 病例讨论实体项
 * Created by wunder on 16/9/1 16:10.
 */
public class DiscussCaseItem implements Serializable {

    private static final long serialVersionUID = -8642871358206027240L;

    /**
     * 医生ID
     */
    private String doctorId;

    /**
     * 医生头像ID
     */
    private String photo;

    /**
     * 医生名称
     */
    private String doctorName;

    /**
     * 所属医院
     */
    private String hospital;

    /**
     * 病历讨论
     */
    private String recordId;

    /**
     * 病历创建时间
     */
    private String recordTime;

    /**
     * 病历类型(0：全部;1：“1+1+1”)
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String recordType;

    /**
     * 讨论的主题(0：求诊断)
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String discussTopic;

    /**
     * 病人年龄
     */
    @StringValidator(nullable = true, pattern = RegularConstants.ID_REGULAR)
    private String patientAge;

    /**
     * 病人性别
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String patientSex;

    /**
     * 具体描述
     */
    private String description;

    /**
     * 主诉
     */
    private String mainDesc;

    /**
     * 现病史
     */
    private String nowIllness;

    /**
     * 既往史
     */
    private String historyIllness;

    /**
     * 查体
     */
    private String healthCheck;

    /**
     * 辅助检查
     */
    private String assistCheck;

    /**
     * 上传图片列表，最多12张
     */
    private List<ImageItem> imageList;

    /**
     * 视频id
     */
    @StringValidator(nullable = true, pattern = RegularConstants.UUID_REGULAR)
    private String videoId;

    /**
     * 主要问题
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SHORT_TEXT_REGULAR)
    private String question;

    /**
     * 阅读数
     */
    private String readCount;

    /**
     * 评论数
     */
    private String commentCount;

    /**
     * 点赞数
     */
    private String likeCount;

    /**
     * 收藏数
     */
    private String collectionCount;

    /**
     * 是否已点赞
     */
    private String hasLike;

    /**
     * 是否已收藏
     */
    private String hasCollect;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getDiscussTopic() {
        return discussTopic;
    }

    public void setDiscussTopic(String discussTopic) {
        this.discussTopic = discussTopic;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainDesc() {
        return mainDesc;
    }

    public void setMainDesc(String mainDesc) {
        this.mainDesc = mainDesc;
    }

    public String getNowIllness() {
        return nowIllness;
    }

    public void setNowIllness(String nowIllness) {
        this.nowIllness = nowIllness;
    }

    public String getHistoryIllness() {
        return historyIllness;
    }

    public void setHistoryIllness(String historyIllness) {
        this.historyIllness = historyIllness;
    }

    public String getHealthCheck() {
        return healthCheck;
    }

    public void setHealthCheck(String healthCheck) {
        this.healthCheck = healthCheck;
    }

    public String getAssistCheck() {
        return assistCheck;
    }

    public void setAssistCheck(String assistCheck) {
        this.assistCheck = assistCheck;
    }

    public List<ImageItem> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageItem> imageList) {
        this.imageList = imageList;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(String collectionCount) {
        this.collectionCount = collectionCount;
    }

    public String getHasLike() {
        return hasLike;
    }

    public void setHasLike(String hasLike) {
        this.hasLike = hasLike;
    }

    public String getHasCollect() {
        return hasCollect;
    }

    public void setHasCollect(String hasCollect) {
        this.hasCollect = hasCollect;
    }
}
