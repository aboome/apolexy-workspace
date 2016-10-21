package com.yh.apoplexy.admin.system.service.intf;

import com.yh.apoplexy.assist.dmo.admin.AdminUserRoleDmo;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * admin端用户角色关系服务接口类
 * Created by wunder on 16/9/23 14:05.
 */
public interface AdminUserRoleService {

    /**
     * 新增用户角色关系信息
     * @param adminUserRoleDmo
     * @return
     */
    public Result insert(AdminUserRoleDmo adminUserRoleDmo);

    /**
     * 更新用户角色关系信息
     * @param con
     * @return
     */
    public Result update(AdminUserRoleDmo con);

    /**
     * 删除用户角色关系信息
     * @param con
     * @return
     */
    public Result delete(AdminUserRoleDmo con);

    /**
     * 查找用户角色关系信息
     * @param con
     * @return
     */
    public AdminUserRoleDmo selectOne(AdminUserRoleDmo con);

    /**
     * 用户绑定角色
     * @param adminUserRoleDmo
     * @return
     */
    public Result bindRole(AdminUserRoleDmo adminUserRoleDmo);

}
