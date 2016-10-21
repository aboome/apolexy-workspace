package com.yh.apoplexy.assist.dmo.doctor.cases.referral;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;

/**
 * 病例接诊评价表
 *
 * @author zhangbiao
 */

@Table(name = "t_doctor_referral_comment")
public class ReferralCommentDmo extends Entity {

    private static final long serialVersionUID = -8418075876807077502L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 病例的ID
     */
    @Column(name = "record_id")
    private Long recordId;

    /**
     * 病例完整度
     */
    @Column(name = "case_integrity")
    private Long caseIntegrity;

    /**
     * 病例详细度
     */
    @Column(name = "case_detail")
    private Long caseDetail;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getCaseIntegrity() {
        return caseIntegrity;
    }

    public void setCaseIntegrity(Long caseIntegrity) {
        this.caseIntegrity = caseIntegrity;
    }

    public Long getCaseDetail() {
        return caseDetail;
    }

    public void setCaseDetail(Long caseDetail) {
        this.caseDetail = caseDetail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
