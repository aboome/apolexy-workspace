package com.yh.apoplexy.doctor.open.dto.cases.ast;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.NihssInfoItem;

import java.io.Serializable;
import java.util.List;

/**
 * AST病例实体项
 * Created by wunder on 16/9/1 20:38.
 */
public class AstCaseItem implements Serializable {

    private static final long serialVersionUID = 1398564643049353889L;

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
     * AST病例
     */
    private String recordId;

    /**
     * AST病历时间
     */
    private String recordTime;

    /**
     * 患者名称
     */
    @StringValidator(nullable = true, pattern = RegularConstants.REAL_NAME_REGULAR)
    private String patientName;

    /**
     * 病人年龄
     */
    @StringValidator(nullable = true, pattern = RegularConstants.ID_REGULAR)
    private String patientAge;

    /**
     * 出生年月日
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String patientBirthday;

    /**
     * 病人性别(0：男;1：女)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String patientSex;

    /**
     * 病发时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String onsetTime;

    /**
     * 病前mRS
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String mrs;

    /**
     * NIHSS得分
     */
    @StringValidator(nullable = true, pattern = RegularConstants.ID_REGULAR)
    private String nihssFee;

    /**
     * nihss题目列表
     */
    private List<NihssInfoItem> nihssList;

    /**
     * 既往史列表
     */
    private List<HisIllnessItem> hisIllness;

    /**
     * 体重
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String weight;

    /**
     * 抽烟(0：否;1：是)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String smock;

    /**
     * 妊娠(0：否;1：是)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String pregnancy;

    /**
     * 是否有用药历史(0：否;1：是)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String hisMedication;

    /**
     * 既往用药列表
     */
    private List<HisMedicineItem> hisMedicaitionList;

    /**
     * 急诊血压-舒张压
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String highPressure;

    /**
     * 急诊血压-收缩压
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String lowPressure;

    /**
     * 血糖
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String bloodSugar;

    /**
     * 血小板
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String platelet;

    /**
     * INR
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String inr;

    /**
     * PT
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String pt;

    /**
     * APPT
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String aptt;

    /**
     * ECT
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String ect;

    /**
     * DTT
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String dtt;

    /**
     * CT平扫图片列表
     */
    private List<ImageItem> ctList;

    /**
     * 急诊诊断
     * 0：脑梗死
     * 1：短暂性脑出血发作
     * 2：脑出血
     * 3：其他
     * 多个结果，以竖线（|）分隔
     */
    @StringValidator(nullable = true, pattern = RegularConstants.REAL_NAME_REGULAR)
    private String emergencyIndex;

    /**
     * 其他时，对具体急诊诊断描述
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SHORT_TEXT_REGULAR)
    private String emergencyDesc;

    /**
     * 静脉血栓(0：否;1：是)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String veinThrombosis;

    /**
     * 到院时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String arriveHospitalTime;

    /**
     * 呼叫时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String callTime;

    /**
     * 溶栓组到达时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String thrombolysisArriveTime;

    /**
     * 溶栓组开始时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String thrombolysisBeginTime;

    /**
     * DNT
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String dnt;

    /**
     * 否的原因
     * 0：超溶栓治疗窗
     * 1：患者放弃治疗
     * 2：其他
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String negativeReason;

    /**
     * 其他原因描述
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SHORT_TEXT_REGULAR)
    private String negativeReasonDesc;

    /**
     * 多模式影像学
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String multiImage;

    /**
     * CTA影像列表
     */
    private List<ImageItem> ctaList;

    /**
     * CTP影像列表
     */
    private List<ImageItem> ctpList;

    /**
     * 多模式影像学否定描述
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SHORT_TEXT_REGULAR)
    private String multiImageNegaticeDesc;

    /**
     * 大血管病变
     * 0：否
     * 1：是
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String macroangiopathy;

    /**
     * 大血管病变描述
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SHORT_TEXT_REGULAR)
    private String macroangiopathyDesc;

    /**
     * 大血管病变原因
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SHORT_TEXT_REGULAR)
    private String macroangiopathyReason;

    /**
     * 联系电话一
     */
    @StringValidator(nullable = true, pattern = RegularConstants.REAL_NAME_REGULAR)
    private String phoneOne;

    /**
     * 联系电话二
     */
    @StringValidator(nullable = true, pattern = RegularConstants.REAL_NAME_REGULAR)
    private String phoneTwo;

    /**
     * 救护车来院(0：否;1：是)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String ambulanceGo;

    /**
     * 后续去向(0：未入院;1：入院)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String followGo;

    /**
     * 住院号
     */
    @StringValidator(nullable = true, pattern = RegularConstants.REAL_NAME_REGULAR)
    private String inpatientNo;

    /**
     * 后续去向说明
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SHORT_TEXT_REGULAR)
    private String followGoWhere;

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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(String patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getOnsetTime() {
        return onsetTime;
    }

    public void setOnsetTime(String onsetTime) {
        this.onsetTime = onsetTime;
    }

    public String getMrs() {
        return mrs;
    }

    public void setMrs(String mrs) {
        this.mrs = mrs;
    }

    public String getNihssFee() {
        return nihssFee;
    }

    public void setNihssFee(String nihssFee) {
        this.nihssFee = nihssFee;
    }

    public List<NihssInfoItem> getNihssList() {
        return nihssList;
    }

    public void setNihssList(List<NihssInfoItem> nihssList) {
        this.nihssList = nihssList;
    }

    public List<HisIllnessItem> getHisIllness() {
        return hisIllness;
    }

    public void setHisIllness(List<HisIllnessItem> hisIllness) {
        this.hisIllness = hisIllness;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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

    public String getHisMedication() {
        return hisMedication;
    }

    public void setHisMedication(String hisMedication) {
        this.hisMedication = hisMedication;
    }

    public List<HisMedicineItem> getHisMedicaitionList() {
        return hisMedicaitionList;
    }

    public void setHisMedicaitionList(List<HisMedicineItem> hisMedicaitionList) {
        this.hisMedicaitionList = hisMedicaitionList;
    }

    public String getHighPressure() {
        return highPressure;
    }

    public void setHighPressure(String highPressure) {
        this.highPressure = highPressure;
    }

    public String getLowPressure() {
        return lowPressure;
    }

    public void setLowPressure(String lowPressure) {
        this.lowPressure = lowPressure;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getPlatelet() {
        return platelet;
    }

    public void setPlatelet(String platelet) {
        this.platelet = platelet;
    }

    public String getInr() {
        return inr;
    }

    public void setInr(String inr) {
        this.inr = inr;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getAptt() {
        return aptt;
    }

    public void setAptt(String aptt) {
        this.aptt = aptt;
    }

    public String getEct() {
        return ect;
    }

    public void setEct(String ect) {
        this.ect = ect;
    }

    public String getDtt() {
        return dtt;
    }

    public void setDtt(String dtt) {
        this.dtt = dtt;
    }

    public List<ImageItem> getCtList() {
        return ctList;
    }

    public void setCtList(List<ImageItem> ctList) {
        this.ctList = ctList;
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

    public String getArriveHospitalTime() {
        return arriveHospitalTime;
    }

    public void setArriveHospitalTime(String arriveHospitalTime) {
        this.arriveHospitalTime = arriveHospitalTime;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getThrombolysisArriveTime() {
        return thrombolysisArriveTime;
    }

    public void setThrombolysisArriveTime(String thrombolysisArriveTime) {
        this.thrombolysisArriveTime = thrombolysisArriveTime;
    }

    public String getThrombolysisBeginTime() {
        return thrombolysisBeginTime;
    }

    public void setThrombolysisBeginTime(String thrombolysisBeginTime) {
        this.thrombolysisBeginTime = thrombolysisBeginTime;
    }

    public String getDnt() {
        return dnt;
    }

    public void setDnt(String dnt) {
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

    public List<ImageItem> getCtaList() {
        return ctaList;
    }

    public void setCtaList(List<ImageItem> ctaList) {
        this.ctaList = ctaList;
    }

    public List<ImageItem> getCtpList() {
        return ctpList;
    }

    public void setCtpList(List<ImageItem> ctpList) {
        this.ctpList = ctpList;
    }

    public String getMultiImageNegaticeDesc() {
        return multiImageNegaticeDesc;
    }

    public void setMultiImageNegaticeDesc(String multiImageNegaticeDesc) {
        this.multiImageNegaticeDesc = multiImageNegaticeDesc;
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
