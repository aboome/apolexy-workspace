package com.yh.apoplexy.assist.dto.doctor.cases.referral;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * 转诊病例实体类
 * Created by wunder on 16/9/8 10:23.
 */
public class ReferralCaseInfoDto extends Entity{

    private static final long serialVersionUID = -274876373308462434L;

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
     * 医生评价星级
     */
    private Long doctorStarLevel;

    /**
     * 病例的类型
     */
    private String caseType;

    /**
     * 转诊标题
     */
    private String title;

    /**
     * 患者年龄
     */
    private Long patientAge;

    /**
     * 患者的性别
     */
    private String patientSex;

    /**
     * 病人的体温
     */
    private Double temperature;

    /**
     * 病人的心率
     */
    private Long heartRate;

    /**
     * 病人的呼吸情况
     */
    private String breath;

    /**
     * 病人的意识情况
     */
    private String consciousness;

    /**
     * 病人的舒张压
     */
    private Double highPressure;

    /**
     * 病人的收缩压
     */
    private Double lowPressure;

    /**
     * 鼻饲胃管时间
     */
    private Date nasogastricTube;

    /**
     * 留置导尿管时间
     */
    private Date indwellingCatheter;

    /**
     * 浅静脉留置管时间
     */
    private Date superficialVein;

    /**
     * 深静脉留置管时间
     */
    private Date deepVein;

    /**
     * PICC留置时间
     */
    private Date picc;

    /**
     * 皮肤伤害类型
     */
    private String skinType;

    /**
     * 皮肤描述
     */
    private String skinDesc;

    /**
     * NIHSS总分
     */
    private Long nihssTotalFee;

    /**
     * 患者补充说明
     */
    private String patientSub;

    /**
     * 主述
     */
    private String mainDesc;

    /**
     * 现病史
     */
    private String nowIllness;

    /**
     * 既往史
     */
    private String hisIllness;

    /**
     * 查体
     */
    private String healthCheck;

    /**
     * 辅助检查
     */
    private String assistCheck;

    /**
     * 范围，是全部还是"1+1+1"
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
     * 转诊的状态，已转诊，未转诊
     */
    private String status;

    /**
     * 已读次数
     */
    private Long readCount;

    /**
     * 被接受的次数
     */
    private Long receiveCount;

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

    /**
     * NHISS序号列表
     */
    private String nihssIndexList;

    /**
     * NHISS结果列表
     */
    private String nihssResultList;

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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Long getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Long heartRate) {
        this.heartRate = heartRate;
    }

    public String getBreath() {
        return breath;
    }

    public void setBreath(String breath) {
        this.breath = breath;
    }

    public String getConsciousness() {
        return consciousness;
    }

    public void setConsciousness(String consciousness) {
        this.consciousness = consciousness;
    }

    public Double getHighPressure() {
        return highPressure;
    }

    public void setHighPressure(Double highPressure) {
        this.highPressure = highPressure;
    }

    public Double getLowPressure() {
        return lowPressure;
    }

    public void setLowPressure(Double lowPressure) {
        this.lowPressure = lowPressure;
    }

    public Date getNasogastricTube() {
        return nasogastricTube;
    }

    public void setNasogastricTube(Date nasogastricTube) {
        this.nasogastricTube = nasogastricTube;
    }

    public Date getIndwellingCatheter() {
        return indwellingCatheter;
    }

    public void setIndwellingCatheter(Date indwellingCatheter) {
        this.indwellingCatheter = indwellingCatheter;
    }

    public Date getSuperficialVein() {
        return superficialVein;
    }

    public void setSuperficialVein(Date superficialVein) {
        this.superficialVein = superficialVein;
    }

    public Date getDeepVein() {
        return deepVein;
    }

    public void setDeepVein(Date deepVein) {
        this.deepVein = deepVein;
    }

    public Date getPicc() {
        return picc;
    }

    public void setPicc(Date picc) {
        this.picc = picc;
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    public String getSkinDesc() {
        return skinDesc;
    }

    public void setSkinDesc(String skinDesc) {
        this.skinDesc = skinDesc;
    }

    public Long getNihssTotalFee() {
        return nihssTotalFee;
    }

    public void setNihssTotalFee(Long nihssTotalFee) {
        this.nihssTotalFee = nihssTotalFee;
    }

    public String getPatientSub() {
        return patientSub;
    }

    public void setPatientSub(String patientSub) {
        this.patientSub = patientSub;
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

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(Long receiveCount) {
        this.receiveCount = receiveCount;
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

    public String getNihssIndexList() {
        return nihssIndexList;
    }

    public void setNihssIndexList(String nihssIndexList) {
        this.nihssIndexList = nihssIndexList;
    }

    public String getNihssResultList() {
        return nihssResultList;
    }

    public void setNihssResultList(String nihssResultList) {
        this.nihssResultList = nihssResultList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDoctorStarLevel() {
        return doctorStarLevel;
    }

    public void setDoctorStarLevel(Long doctorStarLevel) {
        this.doctorStarLevel = doctorStarLevel;
    }
}
