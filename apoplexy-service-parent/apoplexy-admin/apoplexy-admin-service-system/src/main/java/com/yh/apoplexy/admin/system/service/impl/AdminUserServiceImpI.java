package com.yh.apoplexy.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yh.apoplexy.admin.system.service.intf.AdminUserRoleService;
import com.yh.apoplexy.admin.system.service.intf.AdminUserService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.admin.AdminInfoDmo;
import com.yh.apoplexy.assist.dmo.admin.AdminUserRoleDmo;
import com.yh.apoplexy.assist.dto.admin.system.AdminInfoDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

/**
 * admin端用户管理服务实现类
 * Created by wunder on 16/9/23 14:09.
 */
@Service("adminUserService")
@ServiceTrace
public class AdminUserServiceImpI implements AdminUserService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    @Override
    public List<AdminInfoDto> selectListByPage(AdminInfoDmo adminInfoDmo,
    		Page page) {
    	adminInfoDmo.setStatus(Constants.MemberStatus.NORMAL);

        return commonDao.selectListByPage("AdminUserManagement.UserManagementCount", "AdminUserManagement.UserManagementList", adminInfoDmo, page);
   
    }
    

    @Override
    public AdminInfoDmo selectOne(AdminInfoDmo adminInfoDmo) {

        return (AdminInfoDmo) commonDao.selectOne(adminInfoDmo);
    }

    @Override
    public Result addUser(AdminInfoDmo adminInfoDmo) {

        Result result = new Result();
    	if(null == adminInfoDmo)
    	{
    		result.fail("参数异常");
    		return result;
    	}

    	AdminInfoDmo dmo = new AdminInfoDmo();
    	dmo.setUserName(adminInfoDmo.getUserName());
    	dmo.setStatus(Constants.AdminStatus.NORMAL);

    	AdminInfoDmo exist = selectOne(dmo);
    	if(exist != null)
    	{
    		result.fail("用户名已存在");
    		return result;
    	}
        int i = commonDao.insert(adminInfoDmo);

        return SqlAssertUtils.insertAssert(i);

    }

    @Override
    public Result updateUser(AdminInfoDmo adminInfoDmo) {

        int i = commonDao.update(adminInfoDmo);

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    @Transactional
    public Result deleteUser(AdminInfoDmo adminInfoDmo) {

        Result result = new Result();

        adminInfoDmo.setStatus(Constants.MemberStatus.DELETE);

        result = updateUser(adminInfoDmo);

        if (!result.isSuccess()) {
            result.fail("DLU-0001","删除用户失败");
            throw new AppException(result);
        }

        AdminUserRoleDmo adminUserRoleDmo = new AdminUserRoleDmo();

        adminUserRoleDmo.setUserId(adminInfoDmo.getId());

        result = adminUserRoleService.delete(adminUserRoleDmo);

        if (!result.isSuccess()) {
            result.fail("DLU-0002","删除用户角色关系失败");
            throw new AppException(result);
        }

        return result;
    }

    @Override
    public Result unlockUser(AdminInfoDmo adminInfoDmo) {

        Result result = new Result();

        adminInfoDmo.setStatus(Constants.MemberStatus.NORMAL);

        AdminInfoDmo existAdminInfoDmo = selectOne(adminInfoDmo);

        if (null == existAdminInfoDmo){

            result.fail("ULU-0001","用户信息不存在");
            return result;

        }

        existAdminInfoDmo.setLoginFailCount(0L);

        int i = commonDao.update(existAdminInfoDmo);

        return SqlAssertUtils.updateAssert(i);
    }


}
