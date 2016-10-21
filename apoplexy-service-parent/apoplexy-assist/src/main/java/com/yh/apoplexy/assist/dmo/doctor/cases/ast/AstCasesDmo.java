package com.yh.apoplexy.assist.dmo.doctor.cases.ast;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;


/**
 * AST病例信息表
 */
@Table(name = "t_doctor_ast_info")
public class AstCasesDmo extends Entity {

    private static final long serialVersionUID = -1114299195637930676L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * 医生的ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 到达时间
     */
    @Column(name = "arrive_time")
    private Date arriveTime;

    /**
     * 病人的姓名
     */
    @Column(name = "patient_name")
    private String patientName;

    /**
     * 病人的性别
     */
    @Column(name = "patient_sex")
    private String patientSex;

    /**
     * 病人的出生年月
     */
    @Column(name = "patient_birthday")
    private Date patientBirthday;

    /**
     * 病人的年纪
     */
    @Column(name = "patient_age")
    private Long patientAge;

    /**
     * 病发时间
     */
    @Column(name = "onset_time")
    private Date onsetTime;

    /**
     * 病前mrs
     */
    @Column(name = "mrs")
    private String mrs;

    /**
     * nihss总得分
     */
    @Column(name = "nihss_total_fee")
    private Long nihssTotalFee;

    /**
     * 体重
     */
    @Column(name = "weight")
    private Double weight;

    /**
     * 是否抽烟
     */
    @Column(name = "smock")
    private String smock;

    /**
     * 是否怀孕
     */
    @Column(name = "pregnancy")
    private String pregnancy;

    /**
     * 舒张压
     */
    @Column(name = "high_pressure")
    private Double highPressure;

    /**
     * 收缩压
     */
    @Column(name = "low_pressure")
    private Double lowPressure;

    /**
     * 血糖
     */
    @Column(name = "blood_sugar")
    private Double bloodSugar;


    /**
     * 血小板
     */
    @Column(name = "platelet")
    private Double platelet;

    /**
     * INr
     */
    @Column(name = "inr")
    private Double inr;

    /**
     * pt
     */
    @Column(name = "pt")
    private Double pt;

    /**
     * aptt
     */
    @Column(name = "aptt")
    private Double aptt;

    /**
     * ect
     */
    @Column(name = "ect")
    private Double ect;

    /**
     * dtt
     */
    @Column(name = "dtt")
    private Double dtt;

    /**
     * 急诊诊断
     */
    @Column(name = "emergency_index")
    private String emergencyIndex;

    /**
     * 急诊描述
     */
    @Column(name = "emergency_desc")
    private String emergencyDesc;

    /**
     * 静脉血栓
     */
    @Column(name = "vein_thrombosis")
    private String veinThrombosis;

    /**
     * 到医院的时间
     */
    @Column(name = "arrive_hospital_time")
    private Date arriveHospitalTime;

    /**
     * 呼叫时间
     */
    @Column(name = "call_time")
    private Date callTime;

    /**
     * 溶栓组到达时间
     */
    @Column(name = "thrombolysis_arrive_time")
    private Date thrombolysisArriveTime;

    /**
     * 溶栓组开始时间
     */
    @Column(name = "thrombolysis_begin_time")
    private Date thrombolysisBeginTime;

    /**
     * dnt
     */
    @Column(name = "dnt")
    private Double dnt;

    /**
     * 否的原因
     */
    @Column(name = "negative_reason")
    private String negativeReason;

    /**
     * 否的原因描述
     */
    @Column(name = "negative_reason_desc")
    private String negativeReasonDesc;

    /**
     * 多模式影像学
     */
    @Column(name = "multi_image")
    private String multiImage;

    /**
     * 多模式影像学否定描述
     */
    @Column(name = "multi_image_negative_desc")
    private String multiImageNegativeDesc;

    /**
     * 大血管病变
     */
    @Column(name = "macroangiopathy")
    private String macroangiopathy;

    /**
     * 大血管病变描述
     */
    @Column(name = "macroangiopathy_desc")
    private String macroangiopathyDesc;

    /**
     * 大血管病变原因
     */
    @Column(name = "macroangiopathy_reason")
    private String macroangiopathyReason;

    /**
     * 手机号码
     */
    @Column(name = "phone_one")
    private String phoneOne;

    /**
     * 备用手机号码
     */
    @Column(name = "phone_two")
    private String phoneTwo;

    /**
     * 救护车来院
     */
    @Column(name = "ambulance_go")
    private String ambulanceGo;

    /**
     * 后续去向
     */
    @Column(name = "follow_go")
    private String followGo;

    /**
     * 住院号
     */
    @Column(name = "inpatient_no")
    private String inpatientNo;

    /**
     * 后续去向说明
     */
    @Column(name = "follow_go_where")
    private String followGoWhere;

    /**
     * 点赞数
     */
    @Column(name = "like_count")
    private Long likeCount;
    ;

    /**
     * 阅读量
     */
    @Column(name = "read_count")
    private Long readCount;

    /**
     * 收藏量
     */
    @Column(name = "collection_count")
    private Long collectionCount;

    /**
     * 评论数
     */
    @Column(name = "comment_count")
    private Long commentCount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;

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

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public Date getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(Date patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    public Long getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Long patientAge) {
        this.patientAge = patientAge;
    }

    public Date getOnsetTime() {
        return onsetTime;
    }

    public void setOnsetTime(Date onsetTime) {
        this.onsetTime = onsetTime;
    }

    public String getMrs() {
        return mrs;
    }

    public void setMrs(String mrs) {
        this.mrs = mrs;
    }

    public Long getNihssTotalFee() {
        return nihssTotalFee;
    }

    public void setNihssTotalFee(Long nihssTotalFee) {
        this.nihssTotalFee = nihssTotalFee;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSmock() {
        return smock;
    }

    public void setSmock(String smock) {
        this.smock = smock;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        this.pregnancy = pregnancy;
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

    public Double getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(Double bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public Double getPlatelet() {
        return platelet;
    }

    public void setPlatelet(Double platelet) {
        this.platelet = platelet;
    }

    public Double getInr() {
        return inr;
    }

    public void setInr(Double inr) {
        this.inr = inr;
    }

    public Double getPt() {
        return pt;
    }

    public void setPt(Double pt) {
        this.pt = pt;
    }

    public Double getAptt() {
        return aptt;
    }

    public void setAptt(Double aptt) {
        this.aptt = aptt;
    }

    public Double getEct() {
        return ect;
    }

    public void setEct(Double ect) {
        this.ect = ect;
    }

    public Double getDtt() {
        return dtt;
    }

    public void setDtt(Double dtt) {
        this.dtt = dtt;
    }

    public String getEmergencyIndex() {
        return emergencyIndex;
    }

    public void setEmergencyIndex(String emergencyIndex) {
        this.emergencyIndex = emergencyIndex;
    }

    public String getEmergencyDesc() {
        return emergencyDesc;
    }

    public void setEmergencyDesc(String emergencyDesc) {
        this.emergencyDesc = emergencyDesc;
    }

    public String getVeinThrombosis() {
        return veinThrombosis;
    }

    public void setVeinThrombosis(String veinThrombosis) {
        this.veinThrombosis = veinThrombosis;
    }

    public Date getArriveHospitalTime() {
        return arriveHospitalTime;
    }

    public void setArriveHospitalTime(Date arriveHospitalTime) {
        this.arriveHospitalTime = arriveHospitalTime;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    public Date getThrombolysisArriveTime() {
        return thrombolysisArriveTime;
    }

    public void setThrombolysisArriveTime(Date thrombolysisArriveTime) {
        this.thrombolysisArriveTime = thrombolysisArriveTime;
    }

    public Date getThrombolysisBeginTime() {
        return thrombolysisBeginTime;
    }

    public void setThrombolysisBeginTime(Date thrombolysisBeginTime) {
        this.thrombolysisBeginTime = thrombolysisBeginTime;
    }

    public Double getDnt() {
        return dnt;
    }

    public void setDnt(Double dnt) {
        this.dnt = dnt;
    }

    public String getNegativeReason() {
        return negativeReason;
    }

    public void setNegativeReason(String negativeReason) {
        this.negativeReason = negativeReason;
    }

    public String getNegativeReasonDesc() {
        return negativeReasonDesc;
    }

    public void setNegativeReasonDesc(String negativeReasonDesc) {
        this.negativeReasonDesc = negativeReasonDesc;
    }

    public String getMultiImage() {
        return multiImage;
    }

    public void setMultiImage(String multiImage) {
        this.multiImage = multiImage;
    }

    public String getMultiImageNegativeDesc() {
        return multiImageNegativeDesc;
    }

    public void setMultiImageNegativeDesc(String multiImageNegativeDesc) {
        this.multiImageNegativeDesc = multiImageNegativeDesc;
    }

    public String getMacroangiopathy() {
        return macroangiopathy;
    }

    public void setMacroangiopathy(String macroangiopathy) {
        this.macroangiopathy = macroangiopathy;
    }

    public String getMacroangiopathyDesc() {
        return macroangiopathyDesc;
    }

    public void setMacroangiopathyDesc(String macroangiopathyDesc) {
        this.macroangiopathyDesc = macroangiopathyDesc;
    }

    public String getMacroangiopathyReason() {
        return macroangiopathyReason;
    }

    public void setMacroangiopathyReason(String macroangiopathyReason) {
        this.macroangiopathyReason = macroangiopathyReason;
    }

    public String getPhoneOne() {
        return phoneOne;
    }

    public void setPhoneOne(String phoneOne) {
        this.phoneOne = phoneOne;
    }

    public String getPhoneTwo() {
        return phoneTwo;
    }

    public void setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
    }

    public String getAmbulanceGo() {
        return ambulanceGo;
    }

    public void setAmbulanceGo(String ambulanceGo) {
        this.ambulanceGo = ambulanceGo;
    }

    public String getFollowGo() {
        return followGo;
    }

    public void setFollowGo(String followGo) {
        this.followGo = followGo;
    }

    public String getInpatientNo() {
        return inpatientNo;
    }

    public void setInpatientNo(String inpatientNo) {
        this.inpatientNo = inpatientNo;
    }

    public String getFollowGoWhere() {
        return followGoWhere;
    }

    public void setFollowGoWhere(String followGoWhere) {
        this.followGoWhere = followGoWhere;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
