package com.yh.apoplexy.doctor.member.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.member.DoctorLoginDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.doctor.member.result.DoctorLoginResult;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 医生会员信息服务接口类
 * Created by wunder on 16/9/4 13:10.
 */
public interface DoctorMemberService {

    /**
     * 新增医生会员信息
     * @param doctorMemberDmo
     * @return
     */
    public Result insert(DoctorMemberDmo doctorMemberDmo);

    /**
     * 更新医生会员信息
     * @param doctorMemberDmo
     * @return
     */
    public Result update(DoctorMemberDmo doctorMemberDmo);

    /**
     * 删除医生会员信息
     * @param doctorMemberDmo
     * @return
     */
    public Result delete(DoctorMemberDmo doctorMemberDmo);

    /**
     * 查询医生会员信息
     * @param doctorMemberDmo
     * @return
     */
    public DoctorMemberDmo selectOne(DoctorMemberDmo doctorMemberDmo);

    /**
     * 医生会员注册
     *
     * @param doctorMemberDmo
     * @param doctorLoginDmo
     * @return
     */
    public Result register(DoctorMemberDmo doctorMemberDmo, DoctorLoginDmo doctorLoginDmo);

    /**
     * 医生会员登录
     *
     * @param doctorLoginDmo
     * @return
     */
    public DoctorLoginResult login(DoctorLoginDmo doctorLoginDmo);

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
     * 查询医生基础信息
     * @param doctorMemberDmo
     * @return
     */
    public DoctorMemberDto findDoctorBaseInfo(DoctorMemberDmo doctorMemberDmo);

    /**
     * 更新医生会员积分信息
     * @param doctorMemberDmo
     * @return
     */
    public Result updateMemberScore(DoctorMemberDmo doctorMemberDmo);

}
