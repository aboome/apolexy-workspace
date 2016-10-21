package com.yh.apoplexy.assist.dto.doctor.cases.discuss;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.entity.Entity;

/**
 * 
 * 查询Ast病例详情，带图片
 * @author zhangbiao
 *
 */
public class AstCasesDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2397726774177375347L;
	
 
    //发布医生的姓名
    private String doctorName;
    //所在医院
    private String hospital;
	//发布时间
    private Date createTime;
    
	private Long id;
	

	private Long userId;
  
	 
	private Date arriveTime;
	
	 
	private String patientName;
 
	private String patientSex;
	 
	private Date patientBirthday;
 
	private Long patientAge;
	
	 
	private Date onsetTime;
	
	 
	private String mrs;
	
 
	private Long nihssTotalFee;
	
 
	private Double weight;
	
	 
	private String smock;
	
	 
	private String pregnancy;
	
	 
	private Double highPressure;
	 
	 
	private Double lowPressure;
	 
	private Double bloodSugar;
	
	
	 
	private Double platelet;
 
	private Double inr;
	
	 
	private Double pt;
	
	 
	private Double aptt;
	
 
	private Double ect;
	
	 
	private Double dtt;
	 
	private String emergencyIndex;
	
	 
	private String emergencyDesc;
	 
	private String veinThrombosis;
	 
	private Date arriveHospitalTime; 
	
	 
	private Date callTime;
	
	 
	private Date thrombolysisArriveTime;
	 
	 
	private Date thrombolysisBeginTime;
	
	 
	private Double dnt ;
	 
 
	private String negativeReason;
	 
	private String negativeReasonDesc;
	
	 
	private String  multiImage ;
	
	 
	private String  multiImageNegativeDesc ;
	
 
	private String   macroangiopathy ;
	 
	 
	private String   macroangiopathyDesc ;
	 
	private String   macroangiopathyReason ;
	
	 
	private String  phoneOne ;
	
	 
	private String   phoneTwo ;
	
 
	private String  ambulanceGo ;
	
	 
	private String   followGo;
	
 
	private String inpatientNo ;
	
	 
	private String followGoWhere ;
	
	
	 
	private Long likeCount; ;
	
 
	private Long readCount ;
	
	
 
	private Long collectionCount ;
	 
	
	private Long commentCount ;

	
	//病例图片
    private String imageId;
  
    
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


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


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

	public String getImageId() {
		return imageId;
	}


	public void setImageId(String imageId) {
		this.imageId = imageId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
   
}
