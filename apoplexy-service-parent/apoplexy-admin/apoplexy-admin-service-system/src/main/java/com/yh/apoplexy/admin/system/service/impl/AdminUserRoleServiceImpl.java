package com.yh.apoplexy.admin.system.service.impl;

import com.yh.apoplexy.admin.system.service.intf.AdminUserRoleService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.admin.AdminUserRoleDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * admin端用户角色关系服务实现类
 * Created by wunder on 16/9/23 14:09.
 */
@Service("adminUserRoleService")
@ServiceTrace
public class AdminUserRoleServiceImpl implements AdminUserRoleService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(AdminUserRoleDmo adminUserRoleDmo) {

        int i = commonDao.insert(adminUserRoleDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(AdminUserRoleDmo con) {

        int i = commonDao.update(con);

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public Result delete(AdminUserRoleDmo con) {

        int i = commonDao.delete(con);

        return SqlAssertUtils.deleteAssert(i);

    }

    @Override
    public AdminUserRoleDmo selectOne(AdminUserRoleDmo con) {

        return (AdminUserRoleDmo)commonDao.selectOne(con);

    }

    @Override
    public Result bindRole(AdminUserRoleDmo adminUserRoleDmo) {

        Result result = new Result();

        AdminUserRoleDmo con = new AdminUserRoleDmo();

        con.setUserId(adminUserRoleDmo.getUserId());

        AdminUserRoleDmo existUserRoleDmo = selectOne(con);

        if (null != existUserRoleDmo){

            existUserRoleDmo.setCreateTime(DateUtil.getDate());
            existUserRoleDmo.setRoleId(adminUserRoleDmo.getRoleId());

            return update(existUserRoleDmo);

        }

        adminUserRoleDmo.setCreateTime(DateUtil.getDate());

        return insert(adminUserRoleDmo);
    }

}
