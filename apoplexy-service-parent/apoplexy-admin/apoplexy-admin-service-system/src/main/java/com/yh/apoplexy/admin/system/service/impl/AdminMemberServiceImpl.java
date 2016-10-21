package com.yh.apoplexy.admin.system.service.impl;

import com.yh.apoplexy.admin.system.dto.AdminLoginDto;
import com.yh.apoplexy.admin.system.result.AdminLoginResult;
import com.yh.apoplexy.admin.system.service.intf.AdminMemberService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.admin.AdminInfoDmo;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.PropertiesConstants;
import com.yh.apoplexy.common.utils.PropertiesUtil;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * admin端会员服务实现类
 * Created by wunder on 16/9/2 21:26.
 */
@Service("adminMemberService")
@ServiceTrace
public class AdminMemberServiceImpl implements AdminMemberService {

    String failedTimesString = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.LOGIN_FAILED_LOCK_TIMES);

    String lockHoursString = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.LOGIN_FAILED_LOCK_HOURS);

    @Autowired
    private CommonDao commonDao;

    @Override
    public AdminInfoDmo selectOne(AdminInfoDmo con) {
        return (AdminInfoDmo) commonDao.selectOne(con);
    }

    @Override
    public Result update(AdminInfoDmo con) {

        int i = commonDao.update(con);
        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public AdminLoginResult login(AdminLoginDto loginDto) {

        AdminLoginResult result = new AdminLoginResult();

        String userName = loginDto.getUserName();

        String password = loginDto.getPassword();

        AdminInfoDmo con = new AdminInfoDmo();

        con.setUserName(userName);
        con.setStatus(Constants.AdminStatus.NORMAL);

        AdminInfoDmo adminInfoDmo = selectOne(con);

        if (null == adminInfoDmo) {
            result.fail("AML-0001", "用户名或者密码错误");
            return result;
        }

        // 检查登录状态不正确
        if (Constants.LoginStatus.LOCK.equals(adminInfoDmo.getStatus())) {
            result.fail("AML-0002", "用户已被锁定。");
            return result;
        }

        // 检查登录状态不正确
        if (Constants.AdminStatus.DELETE.equals(adminInfoDmo.getStatus())) {
            result.fail("AML-0002", "用户名或者密码错误。");
            return result;
        }

        //账号连续登录失败锁定次数
        Long maxFailedTimes = Long.parseLong(failedTimesString);
        //账号锁定时间
        int lockHours = Integer.parseInt(lockHoursString);

        // 检查登录失败次数是否超过5次并且最后一次登录时间距离现在未到达24小时
        if (adminInfoDmo.getLoginFailCount() >= maxFailedTimes && DateUtil.addHours(adminInfoDmo.getLastUpdateTime(), lockHours).after(DateUtil.getDate())) {
            result.fail("AML-0003", "用户连续登录失败，已被锁定。");
            return result;
        }

        // 检查密码是否正确
        if (password.equalsIgnoreCase(adminInfoDmo.getPassWord())) {
            result.setAdminInfoDmo(adminInfoDmo);
            processLoginSuccess(adminInfoDmo);

        } else {
            processLoginFailed(adminInfoDmo);
            result.fail("AML-0004", "用户名或者密码不正确");
            return result;
        }

        return result;

    }

    /**
     * 处理登录成功
     *
     * @param adminInfoDmo
     */
    private void processLoginSuccess(AdminInfoDmo adminInfoDmo) {

        if (null == adminInfoDmo || null == adminInfoDmo.getId()) {
            return;
        }

        adminInfoDmo.setLoginFailCount(0L);
        adminInfoDmo.setLastUpdateTime(DateUtil.getDate());

        update(adminInfoDmo);

    }

    /**
     * 处理登录失败
     *
     * @param adminInfoDmo
     */
    private void processLoginFailed(AdminInfoDmo adminInfoDmo) {

        if (null == adminInfoDmo || null == adminInfoDmo.getId()) {
            return;
        }

        adminInfoDmo.setLastUpdateTime(DateUtil.getDate());
        adminInfoDmo.setLoginFailCount(adminInfoDmo.getLoginFailCount() + 1);

        update(adminInfoDmo);
    }
}
