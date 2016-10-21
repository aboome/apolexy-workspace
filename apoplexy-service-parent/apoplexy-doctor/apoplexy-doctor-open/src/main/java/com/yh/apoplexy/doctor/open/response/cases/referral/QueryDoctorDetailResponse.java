package com.yh.apoplexy.doctor.open.response.cases.referral;

import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralCommentItem;

import java.io.Serializable;
import java.util.List;

/**
 * 我的-我的申请详情-查询医生详细信息(doc-0061)响应
 * Created by wunder on 16/9/2 16:30.
 */
public class QueryDoctorDetailResponse implements Serializable {

    private static final long serialVersionUID = -3907795981415129263L;

    /**
     * 医生ID
     */
    private String doctorId;

    /**
     * 医生名字
     */
    private String doctorName;

    /**
     * 个人头像ID
     */
    private String photo;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别(0：男;1：女)
     */
    private String sex;

    /**
     * 所属医院
     */
    private String hospital;

    /**
     * 医院级别(0：国家级医院;1：市级医院;2：县级医院;3：乡镇医院)
     */
    private String hospitalLevel;

    /**
     * 科室
     */
    private String department;

    /**
     * 职称
     */
    private String title;

    /**
     * 工作岗位
     */
    private String job;

    /**
     * 有效期
     */
    private String effectiveTime;

    /**
     * 发布转诊次数
     */
    private String referralCount;

    /**
     * 评价列表
     */
    private List<ReferralCommentItem> referralCommentList;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getReferralCount() {
        return referralCount;
    }

    public void setReferralCount(String referralCount) {
        this.referralCount = referralCount;
    }

    public List<ReferralCommentItem> getReferralCommentList() {
        return referralCommentList;
    }

    public void setReferralCommentList(List<ReferralCommentItem> referralCommentList) {
        this.referralCommentList = referralCommentList;
    }
}
