package com.yh.apoplexy.assist.dmo.doctor.cases.discuss;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;

/**
 * 医生发出来的病例讨论实体,病例讨论信息表
 * @author zb
 *
 */  

@Table(name = "t_doctor_case_discuss")
public class DiscussCaseDmo extends Entity {

	private static final long serialVersionUID = 8257652217277305371L;

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
	 * 病例的类型，比如是求诊断还是别的什么
	 */
	@Column(name = "type")
	private String type;
	
	
	/**
	 * 患者的年纪
	 */
	@Column(name = "patient_age")
	private Long patientAge;
	
	
	/**
	 * 患者的性别
	 */
	@Column(name = "patient_sex")
	private String patientSex;
	
	/**
	 * 描述
	 */
	@Column(name = "description")
	private String description;
	
	/**
	 * 主述
	 */
	@Column(name = "main_desc")
	private String mainDesc;
	
	/**
	 * 现病史
	 */
	@Column(name = "now_illness")
	private String  nowIllness;
	
	/**
	 * 既往史
	 */
	@Column(name = "his_illness")
	private String hisIllness ;
	
	/**
	 * 病人的体检情况
	 */
	@Column(name = "health_check")
	private String healthCheck;
	
	/**
	 * 辅助检查
	 */
	@Column(name = "assist_check")
	private String assistCheck;
     
	/**
	 * 主要的问题
	 */
	@Column(name = "main_question")
	private String mainQuestion;
	
	/**
	 * 病例发布的范围,是全部还是1+1+1
	 */
	@Column(name = "case_range")
	private String  caseRange;
	
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
	 * 状态
	 */
	@Column(name = "status")
	private String status;
	
	/**
	 * 点赞数
	 */
	@Column(name = "like_count")
	private Long likeCount;
	
	/**
	 * 阅读数
	 */
	@Column(name = "read_count")
	private Long readCount;
	
	
	/**
	 * 收藏数
	 */
	@Column(name = "collection_count")
	private Long collectionCount;
	
	/**
	 * 评论数
	 */
	@Column(name = "comment_count")
	private Long commentCount;
	
	/**
	 * 顶级所属医院
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
}
