package com.yh.apoplexy.assist.dmo.doctor.cases.referral;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;

/**
 * 病例转诊实体,病例转诊信息表
 */

@Table(name = "t_doctor_referral")
public class ReferralCaseDmo extends Entity {

    private static final long serialVersionUID = -1108201407955080179L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * 用户的ID,发起转诊的那个人
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 转诊的类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 转诊的主题
     */
    @Column(name = "title")
    private String title;

    /**
     * 病人的年龄
     */
    @Column(name = "patient_age")
    private Long patientAge;

    /**
     * 病人的性别
     */
    @Column(name = "patient_sex")
    private String patientSex;

    /**
     * 病人的体温
     */
    @Column(name = "temperature")
    private Double temperature;

    /**
     * 病人的心率
     */
    @Column(name = "heart_rate")
    private Long heartRate;

    /**
     * 病人的呼吸情况
     */
    @Column(name = "breath")
    private Long breath;

    /**
     * 病人的意识情况
     */
    @Column(name = "consciousness")
    private String consciousness;

    /**
     * 病人的舒张压
     */
    @Column(name = "high_pressure")
    private Double highPressure;

    /**
     * 病人的收缩压
     */
    @Column(name = "low_pressure")
    private Double lowPressure;

    /**
     * 鼻饲胃管时间
     */
    @Column(name = "nasogastric_tube")
    private Date nasogastricTube;

    /**
     * 留置导尿管时间
     */
    @Column(name = "indwelling_catheter")
    private Date indwellingCatheter;

    /**
     * 浅静脉留置管时间
     */
    @Column(name = "superficial_vein")
    private Date superficialVein;

    /**
     * 深静脉留置管时间
     */
    @Column(name = "deep_vein")
    private Date deepVein;

    /**
     * PICC留置时间
     */
    @Column(name = "picc")
    private Date picc;

    /**
     * 皮肤伤害类型
     */
    @Column(name = "skin_type")
    private String skinType;

    /**
     * 皮肤描述
     */
    @Column(name = "skin_desc")
    private String skinDesc;

    /**
     * NIHSS总分
     */
    @Column(name = "nihss_total_fee")
    private Long nihssTotalFee;

    /**
     * 患者补充说明
     */
    @Column(name = "patient_sub")
    private String patientSub;

    /**
     * 主述
     */
    @Column(name = "main_desc")
    private String mainDesc;

    /**
     * 现在的病情
     */
    @Column(name = "now_illness")
    private String nowIllness;

    /**
     * 历史病情
     */
    @Column(name = "his_illness")
    private String hisIllness;

    /**
     * 查体
     */
    @Column(name = "health_check")
    private String healthCheck;

    /**
     * 辅助检查
     */
    @Column(name = "assist_check")
    private String assistCheck;

    /**
     * 范围，是全部还是"1+1+1"
     */
    @Column(name = "case_range")
    private String caseRange;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 转诊的状态，已转诊，未转诊
     */
    @Column(name = "status")
    private String status;

    /**
     * 已读次数
     */
    @Column(name = "read_count")
    private Long readCount;

    /**
     * 被接受的次数
     */
    @Column(name = "receive_count")
    private Long receiveCount;

    /**
     * 顶级医院Id
     */
    @Column(name = "root_hospital_id")
    private Long rootHospitalId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getBreath() {
        return breath;
    }

    public void setBreath(Long breath) {
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
}
