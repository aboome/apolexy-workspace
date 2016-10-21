package com.yh.apoplexy.admin.system.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.admin.AdminRoleInfoDmo;
import com.yh.apoplexy.assist.dto.admin.system.AdminAddRoleInfDto;
import com.yh.apoplexy.assist.dto.admin.system.AdminAddRoleInfoDto;
import com.yh.apoplexy.assist.dto.admin.system.AdminRoleInfoDetailsDto;
import com.yh.apoplexy.assist.dto.admin.system.AdminUpdateRoleInfoDto;
import com.yh.apoplexy.assist.dto.system.MenuTreeNodeDto;
import com.yh.apoplexy.assist.dto.system.QueryMenuTreeNodeDto;
import com.yh.apoplexy.assist.dto.system.RoleMenuInfoDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

public interface AdminRoleInfoService {

    /**
     * 插入角色信息
     * @param con
     * @return
     */
    public Result insert(AdminRoleInfoDmo con);

    /**
     * 更新角色信息
     * @param con
     * @return
     */
    public Result update(AdminRoleInfoDmo con);

    /**
     * 删除角色信息
     * @param con
     * @return
     */
    public Result delete(AdminRoleInfoDmo con);

    /**
     * 查询角色信息列表
     * @param con
     * @return
     */
    public List<AdminRoleInfoDmo> selectList(AdminRoleInfoDmo con);

	/**
	 * 查询角色列表
	 * @param adminRoleInfo
	 * @param page
	 * @return
	 */
	public List<AdminRoleInfoDmo> selectListByPage(AdminRoleInfoDmo adminRoleInfo, Page page);
	
	/**
	 * 查询角色详情
	 * @param adminRoleInfo
	 * @return
	 */
	public List<AdminRoleInfoDetailsDto> selectOne(AdminRoleInfoDmo adminRoleInfo,Page page);

	/**
	 * 新增角色
	 * @param adminRoleInfo
	 * @return
	 */
	public Result addRoleInfo(AdminRoleInfoDmo adminRoleInfo);
	/**
	 * 修改角色
	 * @param adminRoleInfo
	 * @return
	 */
	public Result updateRoleInfo(AdminRoleInfoDmo adminRoleInfo);
	/**
	 * 删除角色
	 * @param adminRoleInfo
	 * @return
	 */
	public Result DeleteRoleInfo(AdminRoleInfoDmo adminRoleInfo);

    /**
     * 根据角色信息查询菜单树
     * @param con
     * @return
     */
    public List<MenuTreeNodeDto> queryMenuTreeByRole(QueryMenuTreeNodeDto con);

    /**
     * 更新角色菜单信息
     * @param roleMenuInfoDto
     * @return
     */
    public Result updateRoleMenuInfo(RoleMenuInfoDto roleMenuInfoDto);

    /**
     * 根据用户Id查询用户角色信息
     * @param userId
     * @return
     */
    public AdminRoleInfoDmo findRoleInfoByUserId(String userId);
}
