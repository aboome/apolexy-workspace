package com.yh.apoplexy.doctor.member.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorLoginDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.PropertiesConstants;
import com.yh.apoplexy.common.utils.PropertiesUtil;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.member.result.DoctorLoginResult;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 医生会员信息服务实现类
 * Created by wunder on 16/9/4 13:15.
 */
@Service("doctorMemberService")
@ServiceTrace
public class DoctorMemberServiceImpl implements DoctorMemberService {

    @Autowired
    private CommonDao commonDao;

    String failedTimesString = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.LOGIN_FAILED_LOCK_TIMES);
    String lockHoursString = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.LOGIN_FAILED_LOCK_HOURS);


    @Override
    public Result insert(DoctorMemberDmo doctorMemberDmo) {

        int i = commonDao.insert(doctorMemberDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(DoctorMemberDmo doctorMemberDmo) {

        int i = commonDao.update(doctorMemberDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(DoctorMemberDmo doctorMemberDmo) {

        int i = commonDao.delete(doctorMemberDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public DoctorMemberDmo selectOne(DoctorMemberDmo doctorMemberDmo) {

        return (DoctorMemberDmo) commonDao.selectOne(doctorMemberDmo);
    }

    @Override
    public Result register(DoctorMemberDmo doctorMemberDmo, DoctorLoginDmo doctorLoginDmo) {

        Result result = new Result();

        result = checkRegisterParam(doctorMemberDmo, doctorLoginDmo);

        if (!result.isSuccess()) {
            return result;
        }

        doctorMemberDmo.setScore(0L);
        doctorMemberDmo.setStarLevel(0L);
        doctorMemberDmo.setCreateTime(DateUtil.getDate());
        doctorMemberDmo.setLastUpdateTime(DateUtil.getDate());
        doctorMemberDmo.setStatus(Constants.MemberStatus.NORMAL);

        int i = commonDao.insert(doctorMemberDmo);

        result = SqlAssertUtils.insertAssert(i);

        if (!result.isSuccess()) {
            throw new AppException(result);
        }

        doctorLoginDmo.setCreateTime(DateUtil.getDate());
        doctorLoginDmo.setLastUpdateTime(DateUtil.getDate());
        doctorLoginDmo.setLoginFailCount(0L);
        doctorLoginDmo.setStatus(Constants.LoginStatus.NORMAL);

        int j = commonDao.insert(doctorLoginDmo);

        result = SqlAssertUtils.insertAssert(j);

        if (!result.isSuccess()) {
            throw new AppException(result);
        }

        return result;

    }

    @Override
    public DoctorLoginResult login(DoctorLoginDmo doctorLoginDmo) {

        DoctorLoginResult loginResult = new DoctorLoginResult();

        Result result = checkLoginParam(doctorLoginDmo);

        if (!result.isSuccess()) {
            result.fail("PL-0000", "登录参数错误。");
            throw new AppException(result);
        }

        String userName = doctorLoginDmo.getUserName();
        String email = doctorLoginDmo.getEmail();

        DoctorLoginDmo con = new DoctorLoginDmo();

        if (StringUtils.isNotBlank(userName)) {
            con.setUserName(userName);
        }

        if (StringUtils.isNotBlank(email)) {
            con.setEmail(email);
        }

        DoctorLoginDmo existLoginDmo = (DoctorLoginDmo) commonDao.selectOne(con);

        if (null == existLoginDmo) {
            result.fail("PL-0001", "用户名或者密码错误。");
            throw new AppException(result);
        }

        // 检查登录状态不正确
        if (Constants.LoginStatus.LOCK.equals(existLoginDmo.getStatus())) {
            result.fail("PL-0002", "用户登录状态已被锁定。");
            throw new AppException(result);
        }
        //账号连续登录失败锁定次数
        Long maxFailedTimes = Long.parseLong(failedTimesString);
        //账号锁定时间
        int lockHours = Integer.parseInt(lockHoursString);

        // 检查登录失败次数是否超过5次并且最后一次登录时间距离现在未到达24小时
        if (existLoginDmo.getLoginFailCount() >= maxFailedTimes && DateUtil.addHours(existLoginDmo.getLastLoginTime(), lockHours).after(DateUtil.getDate())) {
            result.fail("PL-0002", "用户登录状态已被锁定。");
            throw new AppException(result);
        }

        // 检查密码是否正确
        String inputPassword = existLoginDmo.getPassword();

        String userPassword = doctorLoginDmo.getPassword();

        if (userPassword.equals(inputPassword)) {
            processLoginSuccess(existLoginDmo);
        } else {
            processLoginFailed(existLoginDmo);
            loginResult.fail("PL-0001", "用户名或者密码错误");
            return loginResult;
        }

        return loginResult;

    }

    @Override
    public Result checkPhoneCanUse(String phone) {

        Result result = new Result();

        if (StringUtils.isBlank(phone)) {
            result.fail("CPC-0000", "参数非法！");
            return result;
        }

        DoctorMemberDmo con = new DoctorMemberDmo();

        con.setPhone(phone);
        con.setStatus(Constants.MemberStatus.NORMAL);

        DoctorMemberDmo doctorMemberDmo = this.selectOne(con);

        if (null == doctorMemberDmo) {
            result.setSuccess(true);
            return result;
        } else {
            result.fail("CPC-0001", "手机号码已存在！");
            return result;
        }
    }

    @Override
    public Result modifyPassword(Long userId, String oldPassword, String newPassword) {

        Result result = new Result();

        if (null == userId || StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            result.fail("MPW-0000", "参数异常");
            return result;
        }

        DoctorMemberDmo con = new DoctorMemberDmo();

        con.setId(userId);
        con.setStatus(Constants.MemberStatus.NORMAL);

        DoctorMemberDmo memberDmo = (DoctorMemberDmo) commonDao.selectOne(con);

        if (null == memberDmo) {
            result.fail("MPW-0001", "用户信息异常");
            return result;
        }

        String phone = memberDmo.getPhone();

        DoctorLoginDmo loginCon = new DoctorLoginDmo();

        loginCon.setUserName(phone);
        loginCon.setStatus(Constants.LoginStatus.NORMAL);

        DoctorLoginDmo loginDmo = (DoctorLoginDmo) commonDao.selectOne(loginCon);

        if (null == loginDmo) {
            result.fail("MPW-0002", "登录信息异常");
            return result;
        }

        String userPassword = loginDmo.getPassword();

        if (!userPassword.equals(oldPassword)) {
            result.fail("MPW-0003", "原密码错误。");
            return result;
        }

        // 修改新的密码
        loginDmo.setPassword(newPassword);
        loginDmo.setLastUpdateTime(DateUtil.getDate());

        int i = commonDao.update(loginDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result resetPassword(String phone, String newPassword) {

        Result result = new Result();

        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(newPassword)) {
            result.fail("RSP-0000", "参数异常");
            return result;
        }

        DoctorLoginDmo con = new DoctorLoginDmo();

        con.setUserName(phone);

        DoctorLoginDmo loginDmo = (DoctorLoginDmo) commonDao.selectOne(con);

        if (null == loginDmo) {
            result.fail("RSP-0001", "登录信息异常");
            return result;
        }

        loginDmo.setLastUpdateTime(DateUtil.getDate());
        loginDmo.setLoginFailCount(0L);
        loginDmo.setPassword(newPassword);

        int i = commonDao.update(loginDmo);

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public DoctorMemberDto findDoctorBaseInfo(DoctorMemberDmo doctorMemberDmo) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("userId",doctorMemberDmo.getId().toString());
        conMap.put("status",doctorMemberDmo.getStatus());

        return (DoctorMemberDto) commonDao.selectOne("DoctorMemberMapper.findDoctorBaseInfo", conMap);
    }

    @Override
    public Result updateMemberScore(DoctorMemberDmo doctorMemberDmo) {

        int i = commonDao.update("DoctorMemberMapper.updateMemberScore",doctorMemberDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    /**
     * 检查注册参数
     *
     * @param doctorMemberDmo
     * @param doctorLoginDmo
     * @return
     */
    private Result checkRegisterParam(DoctorMemberDmo doctorMemberDmo, DoctorLoginDmo doctorLoginDmo) {

        Result result = new Result();

        if (null == doctorMemberDmo || null == doctorLoginDmo) {
            result.fail();
            return result;
        }

        if (StringUtils.isBlank(doctorMemberDmo.getPhone()) || StringUtils.isBlank(doctorMemberDmo.getDoctorName())) {
            result.fail();
            return result;
        }

        if (StringUtils.isBlank(doctorLoginDmo.getPassword()) || StringUtils.isBlank(doctorLoginDmo.getUserName())) {
            result.fail();
            return result;
        }

        return result;
    }

    /**
     * 检查登录参数
     *
     * @param doctorLoginDmo
     * @return
     */
    private Result checkLoginParam(DoctorLoginDmo doctorLoginDmo) {

        Result result = new Result();

        if (null == doctorLoginDmo) {
            result.fail();
            return result;
        }

        if (StringUtils.isBlank(doctorLoginDmo.getPassword()) || (StringUtils.isBlank(doctorLoginDmo.getUserName()) && StringUtils.isBlank(doctorLoginDmo.getEmail()))) {
            result.fail();
            return result;
        }

        return result;
    }

    /**
     * 处理登录成功
     *
     * @param doctorLoginDmo
     */
    private void processLoginSuccess(DoctorLoginDmo doctorLoginDmo) {

        if (null == doctorLoginDmo || null == doctorLoginDmo.getId()) {
            return;
        }

        doctorLoginDmo.setLoginFailCount(0L);
        doctorLoginDmo.setLastLoginTime(DateUtil.getDate());

        commonDao.update(doctorLoginDmo);

    }

    /**
     * 处理登录失败
     *
     * @param doctorLoginDmo
     */
    private void processLoginFailed(DoctorLoginDmo doctorLoginDmo) {

        if (null == doctorLoginDmo || null == doctorLoginDmo.getId()) {
            return;
        }

        doctorLoginDmo.setLastLoginTime(DateUtil.getDate());
        doctorLoginDmo.setLoginFailCount(doctorLoginDmo.getLoginFailCount() + 1);

        commonDao.update(doctorLoginDmo);
    }
}
