package com.yh.apoplexy.admin.system.service.intf;

import com.yh.apoplexy.admin.system.dto.FirstMenuDto;
import com.yh.apoplexy.assist.dmo.admin.MenuInfoDmo;
import com.yh.apoplexy.assist.dto.admin.system.AdminMenuDto;
import com.yh.apoplexy.assist.dto.admin.system.QueryMenuListDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

import java.util.List;

public interface AdminMenuService {

    public Result insert(MenuInfoDmo menuInfoDmo);

    public Result update(MenuInfoDmo menuInfoDmo);

    public List<MenuInfoDmo> selectList(MenuInfoDmo con);
    /**
     * 分页查询菜单列表
     * @param con
     * @param page
     * @return
     */
    public List<AdminMenuDto> selectListByPage(MenuInfoDmo con, Page page);

    /**
     * 查询菜单列表
     * @param con
     * @return
     */
    public List<MenuInfoDmo> queryMenuListByUser(QueryMenuListDto con);

    /**
     * 加载菜单列表
     * @param con
     * @return
     */
    public List<FirstMenuDto> loadMenuListByUser(QueryMenuListDto con);

    /**
     * 新增菜单
     * @param menuInfoDmo
     * @return
     */
    public Result addMenu(MenuInfoDmo menuInfoDmo);

    /**
     * 修改菜单
     * @param menuInfoDmo
     * @return
     */
    public Result updateMenu(MenuInfoDmo menuInfoDmo);

    /**
     * 删除菜单
     * @param menuInfoDmo
     * @return
     */
    public Result deleteMenu(MenuInfoDmo menuInfoDmo);

}
