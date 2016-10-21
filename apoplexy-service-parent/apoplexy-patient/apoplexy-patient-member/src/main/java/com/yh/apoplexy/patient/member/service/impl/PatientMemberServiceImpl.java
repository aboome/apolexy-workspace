package com.yh.apoplexy.patient.member.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.member.PatientLoginDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.PropertiesConstants;
import com.yh.apoplexy.common.utils.PropertiesUtil;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.integration.service.intf.ValidateCodeService;
import com.yh.apoplexy.patient.member.result.PatientLoginResult;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 患者会员服务实现类
 * Created by wunder on 16/9/4 15:40.
 */
@Service("patientMemberService")
@ServiceTrace
public class PatientMemberServiceImpl implements PatientMemberService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private ValidateCodeService validateCodeService;

    String failedTimesString = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.LOGIN_FAILED_LOCK_TIMES);
    String lockHoursString = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.LOGIN_FAILED_LOCK_HOURS);

    @Override
    public Result update(PatientMemberDmo patientMemberDmo) {

        int i = commonDao.update(patientMemberDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public PatientMemberDmo findPatientMember(PatientMemberDmo patientMemberDmo) {
        return (PatientMemberDmo)commonDao.selectOne(patientMemberDmo);
    }

    @Override
    @Transactional
    public Result register(PatientMemberDmo patientMemberDmo, PatientLoginDmo patientLoginDmo) {

        Result result = new Result();

        result = checkRegisterParam(patientMemberDmo,patientLoginDmo);

        if (!result.isSuccess()){
            return result;
        }

        patientMemberDmo.setScore(0L);
        patientMemberDmo.setCreateTime(DateUtil.getDate());
        patientMemberDmo.setLastUpdateTime(DateUtil.getDate());
        patientMemberDmo.setStatus(Constants.MemberStatus.NORMAL);

        int i = commonDao.insert(patientMemberDmo);

        result =  SqlAssertUtils.insertAssert(i);

        if (!result.isSuccess()) {
            throw new AppException(result);
        }

        patientLoginDmo.setCreateTime(DateUtil.getDate());
        patientLoginDmo.setLastUpdateTime(DateUtil.getDate());
        patientLoginDmo.setLoginFailCount(0L);
        patientLoginDmo.setStatus(Constants.LoginStatus.NORMAL);

        int j = commonDao.insert(patientLoginDmo);

        result = SqlAssertUtils.insertAssert(j);

        if (!result.isSuccess()) {
            throw new AppException(result);
        }

        return result;
    }

    @Override
    public PatientLoginResult login(PatientLoginDmo patientLoginDmo) {

        PatientLoginResult loginResult = new PatientLoginResult();

        Result result = checkLoginParam(patientLoginDmo);

        if (!result.isSuccess()) {
            loginResult.fail("PL-0000", "登录参数错误。");
            throw new AppException(loginResult);
        }

        String userName = patientLoginDmo.getUserName();

        PatientLoginDmo con = new PatientLoginDmo();

        con.setUserName(userName);

        PatientLoginDmo existLoginDmo = (PatientLoginDmo) commonDao.selectOne(con);

        if (null == existLoginDmo) {
            loginResult.fail("PL-0001", "用户名或者密码错误。");
            throw new AppException(loginResult);
        }

        // 检查登录状态不正确
        if (Constants.LoginStatus.LOCK.equals(existLoginDmo.getStatus())) {
            loginResult.fail("PL-0002", "用户登录状态已被锁定。");
            throw new AppException(loginResult);
        }
        //账号连续登录失败锁定次数
        Long maxFailedTimes = Long.parseLong(failedTimesString);
        //账号锁定时间
        int lockHours = Integer.parseInt(lockHoursString);

        // 检查登录失败次数是否超过5次并且最后一次登录时间距离现在未到达24小时
        if (existLoginDmo.getLoginFailCount() >= maxFailedTimes&&DateUtil.addHours(existLoginDmo.getLastLoginTime(),lockHours).after(DateUtil.getDate())) {
            loginResult.fail("PL-0002", "用户登录状态已被锁定。");
            throw new AppException(loginResult);
        }

        // 检查密码是否正确
        String inputPassword = existLoginDmo.getPassword();

        String userPassword = patientLoginDmo.getPassword();

        if (userPassword.equals(inputPassword)) {
            processLoginSuccess(existLoginDmo);
        }else {
            processLoginFailed(existLoginDmo);
            loginResult.fail("PL-0001", "用户名或者密码错误");
            return loginResult;
        }

        PatientMemberDmo memberCon = new PatientMemberDmo();

        memberCon.setPhone(userName);

        PatientMemberDmo patientMemberDmo = (PatientMemberDmo) commonDao.selectOne(memberCon);

        if (null == patientMemberDmo) {
            loginResult.fail("PL-0003", "用户状态异常。");
            return loginResult;
        }

        loginResult.setUserId(patientMemberDmo.getId());

        return loginResult;

    }

    /**
     * 检查注册参数
     * @param patientMemberDmo
     * @param patientLoginDmo
     * @return
     */
    private Result checkRegisterParam(PatientMemberDmo patientMemberDmo, PatientLoginDmo patientLoginDmo) {

        Result result = new Result();

        if (null == patientMemberDmo || null == patientLoginDmo) {
            result.fail();
            return result;
        }

        if (StringUtils.isBlank(patientMemberDmo.getPhone()) || StringUtils.isBlank(patientMemberDmo.getRealName())) {
            result.fail();
            return result;
        }

        if (StringUtils.isBlank(patientLoginDmo.getPassword()) || StringUtils.isBlank(patientLoginDmo.getUserName())) {
            result.fail();
            return result;
        }

        return result;
    }

    @Override
    public Result checkPhoneCanUse(String phone) {

        Result result = new Result();

        if (StringUtils.isBlank(phone)) {
            result.fail("CPC-0000", "参数非法！");
            return result;
        }

        PatientMemberDmo con = new PatientMemberDmo();

        con.setPhone(phone);
        con.setStatus(Constants.MemberStatus.NORMAL);

        PatientMemberDmo patientMemberDmo = this.findPatientMember(con);

        if (null == patientMemberDmo) {
            result.setSuccess(true);
            return result;
        } else {
            result.fail("CPC-0001", "手机号码已存在！");;
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

        PatientMemberDmo con = new PatientMemberDmo();

        con.setId(userId);
        con.setStatus(Constants.MemberStatus.NORMAL);

        PatientMemberDmo memberDmo = (PatientMemberDmo) commonDao.selectOne(con);

        if (null == memberDmo) {
            result.fail("MPW-0001", "用户信息异常");
            return result;
        }

        String phone = memberDmo.getPhone();

        PatientLoginDmo loginCon = new PatientLoginDmo();

        loginCon.setUserName(phone);
        loginCon.setStatus(Constants.LoginStatus.NORMAL);

        PatientLoginDmo loginDmo = (PatientLoginDmo) commonDao.selectOne(loginCon);

        if (null == loginDmo) {
            result.fail("MPW-0002", "登录信息异常");
            return result;
        }

        String userPassword = loginDmo.getPassword();

        if (!userPassword.equals(oldPassword)) {
            result.fail("MPW-0003", "原密码错误");
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

        PatientLoginDmo con = new PatientLoginDmo();

        con.setUserName(phone);

        PatientLoginDmo loginDmo = (PatientLoginDmo) commonDao.selectOne(con);

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
    @Transactional
    public Result modifyMemberInfo(PatientMemberDmo patientMemberDmo,String validateCode) {

        Result result = new Result();

        if (null == patientMemberDmo||null == patientMemberDmo.getId()){

            result.fail("MMI-0000","修改患者会员信息失败");
            return result;

        }

        PatientMemberDmo memberCon = new PatientMemberDmo();

        memberCon.setId(patientMemberDmo.getId());

        PatientMemberDmo existMemberDmo = findPatientMember(memberCon);

        if (null == existMemberDmo){

            result.fail("MMI-0002","修改患者会员信息失败");
            return result;

        }

        if (!existMemberDmo.getPhone().equals(patientMemberDmo.getPhone())){


            //验证短信验证码
            result = validateCodeService.verifyValidateCode(patientMemberDmo.getPhone(),validateCode, AppConstants.VerifyValidateCodeInvalid.INVALID);

            if (!result.isSuccess()){

                result.fail("MMI-0004","手机验证码不正确");
                return result;
            }

            result = checkPhoneCanUse(patientMemberDmo.getPhone());

            if (!result.isSuccess()){

                result.fail("MMI-0001","修改患者会员信息失败");
                return result;

            }

            PatientLoginDmo loginCon = new PatientLoginDmo();

            loginCon.setUserName(existMemberDmo.getPhone());

            PatientLoginDmo existLoginDmo = (PatientLoginDmo)commonDao.selectOne(loginCon);

            if (null == existLoginDmo){

                result.fail("MMI-0003","修改患者会员信息失败");
                return result;

            }

            existLoginDmo.setUserName(patientMemberDmo.getPhone());

            int i = commonDao.update(existLoginDmo);

            result = SqlAssertUtils.updateAssert(i);

            if (!result.isSuccess()){

                throw new AppException(result);

            }

        }

        patientMemberDmo.setId(existMemberDmo.getId());

        result = update(patientMemberDmo);

        if (!result.isSuccess()){

            throw new AppException(result);

        }

        return result;
    }

    @Override
    public Result updateMemberScore(PatientMemberDmo patientMemberDmo) {

        int i = commonDao.update("PatientMemberMapper.updateMemberScore",patientMemberDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    /**
     * 检查登录参数
     * @param patientLoginDmo
     * @return
     */
    private Result checkLoginParam(PatientLoginDmo patientLoginDmo) {

        Result result = new Result();

        if (null == patientLoginDmo) {
            result.fail();
            return result;
        }

        if (StringUtils.isBlank(patientLoginDmo.getPassword()) || StringUtils.isBlank(patientLoginDmo.getUserName())) {
            result.fail();
            return result;
        }

        return result;
    }

    /**
     * 处理登录成功
     * @param patientLoginDmo
     */
    private void processLoginSuccess(PatientLoginDmo patientLoginDmo){

        if (null == patientLoginDmo||null == patientLoginDmo.getId()){
            return;
        }

        patientLoginDmo.setLoginFailCount(0L);
        patientLoginDmo.setLastLoginTime(DateUtil.getDate());

        commonDao.update(patientLoginDmo);

    }

    /**
     * 处理登录失败
     * @param patientLoginDmo
     */
    private void processLoginFailed(PatientLoginDmo patientLoginDmo){

        if (null == patientLoginDmo||null == patientLoginDmo.getId()){
            return;
        }

        patientLoginDmo.setLastLoginTime(DateUtil.getDate());
        patientLoginDmo.setLoginFailCount(patientLoginDmo.getLoginFailCount()+1);

        commonDao.update(patientLoginDmo);
    }

}
