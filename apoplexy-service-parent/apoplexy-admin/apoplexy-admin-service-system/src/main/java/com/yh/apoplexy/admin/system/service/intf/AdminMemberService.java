package com.yh.apoplexy.admin.system.service.intf;

import com.yh.apoplexy.admin.system.dto.AdminLoginDto;
import com.yh.apoplexy.admin.system.result.AdminLoginResult;
import com.yh.apoplexy.assist.dmo.admin.AdminInfoDmo;
import com.yjh.framework.lang.Result;

/**
 * admin端会员服务接口类
 * Created by wunder on 16/9/2 21:25.
 */
public interface AdminMemberService {

    public AdminInfoDmo selectOne(AdminInfoDmo con);

    public Result update(AdminInfoDmo con);

    /**
     * 管理员登录
     *
     * @param loginDto
     * @return
     */
    public AdminLoginResult login(AdminLoginDto loginDto);

}








