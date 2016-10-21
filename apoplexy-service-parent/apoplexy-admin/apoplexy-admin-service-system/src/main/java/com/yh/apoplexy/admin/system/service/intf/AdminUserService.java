package com.yh.apoplexy.admin.system.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.admin.AdminInfoDmo;
import com.yh.apoplexy.assist.dto.admin.system.AdminInfoDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

/**
 * admin端用户管理服务接口类
 * Created by wunder on 16/9/23 14:09.
 */
public interface AdminUserService {

    /**
     * 分页查询用户列表
     *
     * @param adminInfoDmo
     * @param page
     * @return
     */
    public List<AdminInfoDto> selectListByPage(AdminInfoDmo adminInfoDmo, Page page);

    /**
     * 查询用户详情
     *
     * @param adminInfoDmo
     * @return
     */

    public AdminInfoDmo selectOne(AdminInfoDmo adminInfoDmo);

    /**
     * 新增用户
     *
     * @param adminInfoDmo
     * @return
     */
    public Result addUser(AdminInfoDmo adminInfoDmo);

    /**
     * 修改用户
     *
     * @param adminInfoDmo
     * @return
     */
    public Result updateUser(AdminInfoDmo adminInfoDmo);

    /**
     * 删除用户
     *
     * @param adminInfoDmo
     * @return
     */
    public Result deleteUser(AdminInfoDmo adminInfoDmo);

    /**
     * 解锁用户
     *
     * @param adminInfoDmo
     * @return
     */
    public Result unlockUser(AdminInfoDmo adminInfoDmo);

}
