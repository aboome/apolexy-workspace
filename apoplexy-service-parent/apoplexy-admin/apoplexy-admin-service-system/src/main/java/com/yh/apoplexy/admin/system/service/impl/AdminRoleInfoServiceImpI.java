package com.yh.apoplexy.admin.system.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yh.apoplexy.assist.dmo.admin.AdminRoleInfoDmo;
import com.yh.apoplexy.assist.dto.system.MenuTreeNodeDto;
import com.yh.apoplexy.assist.dto.system.QueryMenuTreeNodeDto;
import com.yh.apoplexy.assist.dto.system.RoleMenuInfoDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yh.apoplexy.admin.system.service.intf.AdminRoleInfoService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.admin.AdminRoleMenuDmo;
import com.yh.apoplexy.assist.dto.admin.system.AdminRoleInfoDetailsDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

@Service("adminRoleInfoService")
@ServiceTrace
public class AdminRoleInfoServiceImpI implements AdminRoleInfoService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(AdminRoleInfoDmo con) {

        int i = commonDao.insert(con);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(AdminRoleInfoDmo con) {

        int i = commonDao.update(con);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(AdminRoleInfoDmo con) {

        int i = commonDao.delete(con);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public List<AdminRoleInfoDmo> selectList(AdminRoleInfoDmo con) {

        return commonDao.selectList(con);
    }

    @Override
    public List<AdminRoleInfoDmo> selectListByPage(AdminRoleInfoDmo adminRoleInfo, Page page) {

        return commonDao.selectListByPage("AdminRoleInfoMapper.RoleInfoCount", "AdminRoleInfoMapper.RoleInfoList", adminRoleInfo, page);
    }

    @Override
    public List<AdminRoleInfoDetailsDto> selectOne(AdminRoleInfoDmo adminRoleInfo, Page page) {
        return null;
    }

    @Override
    public Result addRoleInfo(AdminRoleInfoDmo adminRoleInfo) {

        int i = commonDao.insert(adminRoleInfo);

        return SqlAssertUtils.insertAssert(i);

    }

    @Override
    public Result updateRoleInfo(AdminRoleInfoDmo adminRoleInfo) {

        int i = commonDao.update(adminRoleInfo);

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    @Transactional
    public Result DeleteRoleInfo(AdminRoleInfoDmo adminRoleInfo) {

        AdminRoleInfoDmo roleInfo = new AdminRoleInfoDmo();
        roleInfo.setId(adminRoleInfo.getId());
        Result result = new Result();
        int i = commonDao.delete(roleInfo);
        if (i < 0) {
            result.fail("删除角色失败");
            throw new AppException(result);
        }

        AdminRoleMenuDmo adminRoleMenuDmo = new AdminRoleMenuDmo();
        adminRoleMenuDmo.setRoleId(adminRoleInfo.getId());
        int t = commonDao.delete(adminRoleMenuDmo);
        if (t < 0) {
            result.fail("删除角色失败");
            throw new AppException(result);
        }
        return result;
    }

    @Override
    public List<MenuTreeNodeDto> queryMenuTreeByRole(QueryMenuTreeNodeDto con) {

        HashMap<String ,String> conMap = new HashMap<>();

        conMap.put("roleId",con.getRoleId());
        conMap.put("subMenuLevel",con.getSubMenuLevel());

        return commonDao.selectList("AdminRoleInfoMapper.queryMenuListByRole",conMap);
    }

    @Override
    @Transactional
    public Result updateRoleMenuInfo(RoleMenuInfoDto roleMenuInfoDto) {

        Result result = new Result();

        if (null == roleMenuInfoDto.getRoleId()){

            result.fail("UPM-0001","更新角色菜单信息失败");
            return result;

        }

        AdminRoleMenuDmo con = new AdminRoleMenuDmo();

        con.setRoleId(roleMenuInfoDto.getRoleId());

        int i = commonDao.delete(con);

        if (i<0){

            result.fail("UPM-0002","更新角色菜单信息失败");
            throw new AppException(result);

        }

        if (CollectionUtils.isEmpty(roleMenuInfoDto.getMenuIdList())){

            return result;

        }

        int j = commonDao.batchInsert("AdminRoleInfoMapper.updateRoleMenuInfo",roleMenuInfoDto);

        if (j != roleMenuInfoDto.getMenuIdList().size()){

            result.fail("UPM-0003","更新角色菜单信息失败");
            throw new AppException(result);

        }

        return result;
    }

    @Override
    public AdminRoleInfoDmo findRoleInfoByUserId(String userId) {

        if(StringUtils.isBlank(userId)){

            return null;
        }

        HashMap<String,String > conMap = new HashMap<>();

        conMap.put("userId",userId.toString());

        return (AdminRoleInfoDmo)commonDao.selectOne("AdminRoleInfoMapper.findRoleInfoByUserId",conMap);
    }

}
