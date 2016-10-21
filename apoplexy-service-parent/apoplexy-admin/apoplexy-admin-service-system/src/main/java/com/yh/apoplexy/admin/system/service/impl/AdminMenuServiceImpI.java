package com.yh.apoplexy.admin.system.service.impl;

import com.yh.apoplexy.admin.system.dto.FirstMenuDto;
import com.yh.apoplexy.admin.system.dto.SecondMenuDto;
import com.yh.apoplexy.admin.system.service.intf.AdminMenuService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.admin.MenuInfoDmo;
import com.yh.apoplexy.assist.dto.admin.system.AdminMenuDto;
import com.yh.apoplexy.assist.dto.admin.system.QueryMenuListDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("adminMenuService")
@ServiceTrace
public class AdminMenuServiceImpI implements AdminMenuService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(MenuInfoDmo menuInfoDmo) {

        int i = commonDao.insert(menuInfoDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(MenuInfoDmo menuInfoDmo) {

        int i = commonDao.update(menuInfoDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public List<MenuInfoDmo> selectList(MenuInfoDmo con) {
        return commonDao.selectList(con);
    }

    @Override
    public List<AdminMenuDto> selectListByPage(MenuInfoDmo con, Page page) {
        return commonDao.selectListByPage("AdminMenuMapper.queryMenuCount", "AdminMenuMapper.queryMenuList", con, page);
    }

    @Override
    public List<MenuInfoDmo> queryMenuListByUser(QueryMenuListDto con) {
        return commonDao.selectList("AdminMenuMapper.queryMenuListByUser", con);
    }

    @Override
    public List<FirstMenuDto> loadMenuListByUser(QueryMenuListDto con) {

        List<FirstMenuDto> menuDtoList = new ArrayList<>();

        con.setStatus(Constants.MenuStatus.NORMAL);

        //查询一级菜单
        con.setMenuLevel(Constants.MenuLevel.FIRST);

        List<MenuInfoDmo> firstMenuList = queryMenuListByUser(con);

        //查询二级菜单
        FirstMenuDto firstMenuDto = null;

        for (MenuInfoDmo firstMenu : firstMenuList) {

            firstMenuDto = new FirstMenuDto();

            con.setMenuLevel(Constants.MenuLevel.SECOND);
            con.setParentId(firstMenu.getId());

            List<SecondMenuDto> secondMenuDtoList = new ArrayList<>();

            List<MenuInfoDmo> secondMenuList = queryMenuListByUser(con);

            //查询三级菜单

            SecondMenuDto secondMenuDto = null;

            for (MenuInfoDmo secondMenu : secondMenuList) {

                secondMenuDto = new SecondMenuDto();

                con.setMenuLevel(Constants.MenuLevel.THIRD);
                con.setParentId(secondMenu.getId());

                List<MenuInfoDmo> thirdMenuList = queryMenuListByUser(con);

                secondMenuDto.setMenuInfoDmo(secondMenu);
                secondMenuDto.setThirdMenuList(thirdMenuList);

                secondMenuDtoList.add(secondMenuDto);
            }

            firstMenuDto.setMenuInfoDmo(firstMenu);
            firstMenuDto.setSecondMenuList(secondMenuDtoList);

            menuDtoList.add(firstMenuDto);

        }

        return menuDtoList;
    }

    @Override
    public Result addMenu(MenuInfoDmo menuInfoDmo) {

        return insert(menuInfoDmo);

    }

    @Override
    public Result updateMenu(MenuInfoDmo menuInfoDmo) {

        return update(menuInfoDmo);

    }

    @Override
    @Transactional
    public Result deleteMenu(MenuInfoDmo menuInfoDmo) {

        Result result = new Result();

        MenuInfoDmo subMenuDmo = new MenuInfoDmo();

        subMenuDmo.setParentId(menuInfoDmo.getId());
        subMenuDmo.setStatus(Constants.MenuStatus.NORMAL);

        Long i = commonDao.selectCount("AdminMenuMapper.SubMenuCount", subMenuDmo);

        if (i > 0) {
            result.fail("该菜单存在子菜单，请先删除全部子菜单");
            throw new AppException(result);
        }

        int t = commonDao.delete(menuInfoDmo);

        if (t < 0) {
            result.fail("删除菜单失败");
            throw new AppException(result);
        }

        return result;

    }
}
