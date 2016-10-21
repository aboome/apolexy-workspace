package com.yh.apoplexy.doctor.open.dto.cases.referral;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;

import java.io.Serializable;
import java.util.List;

/**
 * 转诊病例实体项
 * Created by wunder on 16/9/1 18:59.
 */
public class ReferralCaseItem implements Serializable {

    private static final long serialVersionUID = -5329315963424218402L;

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
     * 医生评价星级
     */
    private String doctorStarLevel;

    /**
     * 转诊状态(0：未转诊;1：已转诊)
     */
    private String status;

    /**
     * 转诊ID
     */
    private String recordId;

    /**
     * 转诊创建时间
     */
    private String recordTime;

    /**
     * 转诊类型(0：全部;1：“1+1+1”)
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String recordType;

    /**
     * 转诊的类型(0：急性;1：高危)
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String referralType;

    /**
     * 转诊标题
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SHORT_TEXT_REGULAR)
    private String referralTitle;

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
     * 体温
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String temperature;

    /**
     * 心率
     */
    @StringValidator(nullable = true, pattern = RegularConstants.ID_REGULAR)
    private String heartRate;

    /**
     * 呼吸
     */
    @StringValidator(nullable = true, pattern = RegularConstants.ID_REGULAR)
    private String breath;

    /**
     * 意识(0：清楚;1：不清楚)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String consciousness;

    /**
     * 舒张压
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String highPressure;

    /**
     * 收缩压
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DOUBLE_REGULAR)
    private String lowPressure;

    /**
     * 鼻饲胃管时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String nasogastricTube;

    /**
     * 留置导尿管时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String indwellingCatheter;

    /**
     * 浅静脉留置管时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String superficialVein;

    /**
     * 深静脉留置管时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String deepVein;

    /**
     * PICC留置时间
     */
    @StringValidator(nullable = true, pattern = RegularConstants.DATE_REGULAR)
    private String picc;

    /**
     * 皮肤伤害类型(0：破损)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String skinType;

    /**
     * 对应皮肤位置
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SHORT_TEXT_REGULAR)
    private String skinDesc;

    /**
     * Nihss列表
     */
    private List<NihssInfoItem> nihssList;

    /**
     * Nihss总得分
     */
    @StringValidator(nullable = true, pattern = RegularConstants.ID_REGULAR)
    private String nihssTotalFee;

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
     * 阅读数
     */
    private String readCount;

    /**
     * 意向接诊医生数
     */
    private String receiveCount;

    /**
     * 视频资源id
     */
    @StringValidator(nullable = true, pattern = RegularConstants.UUID_REGULAR)
    private String videoId;

    /**
     * 转诊筛选详情列表
     */
    private List<ReferralScreenItem> screenList;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getReferralType() {
        return referralType;
    }

    public void setReferralType(String referralType) {
        this.referralType = referralType;
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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
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

    public String getNasogastricTube() {
        return nasogastricTube;
    }

    public void setNasogastricTube(String nasogastricTube) {
        this.nasogastricTube = nasogastricTube;
    }

    public String getIndwellingCatheter() {
        return indwellingCatheter;
    }

    public void setIndwellingCatheter(String indwellingCatheter) {
        this.indwellingCatheter = indwellingCatheter;
    }

    public String getSuperficialVein() {
        return superficialVein;
    }

    public void setSuperficialVein(String superficialVein) {
        this.superficialVein = superficialVein;
    }

    public String getDeepVein() {
        return deepVein;
    }

    public void setDeepVein(String deepVein) {
        this.deepVein = deepVein;
    }

    public String getPicc() {
        return picc;
    }

    public void setPicc(String picc) {
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

    public List<NihssInfoItem> getNihssList() {
        return nihssList;
    }

    public void setNihssList(List<NihssInfoItem> nihssList) {
        this.nihssList = nihssList;
    }

    public String getNihssTotalFee() {
        return nihssTotalFee;
    }

    public void setNihssTotalFee(String nihssTotalFee) {
        this.nihssTotalFee = nihssTotalFee;
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

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(String receiveCount) {
        this.receiveCount = receiveCount;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getReferralTitle() {
        return referralTitle;
    }

    public void setReferralTitle(String referralTitle) {
        this.referralTitle = referralTitle;
    }

    public List<ReferralScreenItem> getScreenList() {
        return screenList;
    }

    public void setScreenList(List<ReferralScreenItem> screenList) {
        this.screenList = screenList;
    }

    public String getDoctorStarLevel() {
        return doctorStarLevel;
    }

    public void setDoctorStarLevel(String doctorStarLevel) {
        this.doctorStarLevel = doctorStarLevel;
    }
}
