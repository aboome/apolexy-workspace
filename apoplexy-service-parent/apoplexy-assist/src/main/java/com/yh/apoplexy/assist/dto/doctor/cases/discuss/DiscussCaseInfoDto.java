package com.yh.apoplexy.assist.dto.doctor.cases.discuss;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * 讨论病例实体类
 * Created by wunder on 16/9/7 09:52.
 */
public class DiscussCaseInfoDto extends Entity {

    private static final long serialVersionUID = 440776069036198768L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 医生ID
     */
    private Long userId;

    /**
     * 医生姓名
     */
    private String doctorName;

    /**
     * 医生头像
     */
    private String avatar;

    /**
     * 所属医院
     */
    private String hospital;

    /**
     * 病例的类型，比如是求诊断还是别的什么
     */
    private String caseType;

    /**
     * 患者的年纪
     */
    private Long patientAge;

    /**
     * 患者的性别
     */
    private String patientSex;

    /**
     * 描述
     */
    private String caseDesc;

    /**
     * 主述
     */
    private String mainDesc;

    /**
     * 现病史
     */
    private String  nowIllness;

    /**
     * 既往史
     */
    private String hisIllness ;

    /**
     * 病人的体检情况
     */
    private String healthCheck;

    /**
     * 辅助检查
     */
    private String assistCheck;

    /**
     * 主要的问题
     */
    private String mainQuestion;

    /**
     * 病例发布的范围,是全部还是1+1+1
     */
    private String caseRange;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 阅读数
     */
    private Long readCount;

    /**
     * 收藏数
     */
    private Long collectionCount;

    /**
     * 评论数
     */
    private Long commentCount;

    /**
     * 顶级所属医院
     */
    private Long rootHospitalId;

    /**
     * 视频资源id
     */
    private String videoId;

    /**
     * 图片列表
     */
    private String imageList;

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

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public Long getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Long patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
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

    public String getHisIllness() {
        return hisIllness;
    }

    public void setHisIllness(String hisIllness) {
        this.hisIllness = hisIllness;
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

    public String getMainQuestion() {
        return mainQuestion;
    }

    public void setMainQuestion(String mainQuestion) {
        this.mainQuestion = mainQuestion;
    }

    public String getCaseRange() {
        return caseRange;
    }

    public void setCaseRange(String caseRange) {
        this.caseRange = caseRange;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Long collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getRootHospitalId() {
        return rootHospitalId;
    }

    public void setRootHospitalId(Long rootHospitalId) {
        this.rootHospitalId = rootHospitalId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
