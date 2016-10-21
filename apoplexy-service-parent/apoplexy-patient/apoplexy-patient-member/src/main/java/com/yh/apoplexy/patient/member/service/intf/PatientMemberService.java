package com.yh.apoplexy.patient.member.service.intf;

import com.yh.apoplexy.assist.dmo.patient.member.PatientLoginDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.patient.member.result.PatientLoginResult;
import com.yjh.framework.lang.Result;

/**
 * 患者会员服务接口类
 * Created by wunder on 16/9/4 15:13.
 */
public interface PatientMemberService {

    /**
     * 更新患者会员信息
     * @param patientMemberDmo
     * @return
     */
    public Result update(PatientMemberDmo patientMemberDmo);

    /**
     * 查询患者用户信息
     *
     * @param patientMemberDmo
     * @return
     */
    public PatientMemberDmo findPatientMember(PatientMemberDmo patientMemberDmo);

    /**
     * 患者会员注册
     *
     * @param patientMemberDmo
     * @param patientLoginDmo
     * @return
     */
    public Result register(PatientMemberDmo patientMemberDmo, PatientLoginDmo patientLoginDmo);

    /**
     * 患者会员登录
     *
     * @param patientLoginDmo
     * @return
     */
    public PatientLoginResult login(PatientLoginDmo patientLoginDmo);

    /**
     * 检查手机号码是否可用
     *
     * @param phone
     * @return
     */
    public Result checkPhoneCanUse(String phone);

    /**
     * 修改密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public Result modifyPassword(Long userId, String oldPassword, String newPassword);

    /**
     * 重置密码
     * @param phone
     * @param newPassword
     * @return
     */
    public Result resetPassword(String phone, String newPassword);

    /**
     * 修改用户信息
     * @param patientMemberDmo
     * @return
     */
    public Result modifyMemberInfo(PatientMemberDmo patientMemberDmo,String validateCode);

    /**
     * 更新患者会员积分信息
     * @param patientMemberDmo
     * @return
     */
    public Result updateMemberScore(PatientMemberDmo patientMemberDmo);

}
