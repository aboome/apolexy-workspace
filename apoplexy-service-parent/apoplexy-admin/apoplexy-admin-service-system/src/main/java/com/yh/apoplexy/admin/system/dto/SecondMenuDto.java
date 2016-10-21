package com.yh.apoplexy.admin.system.dto;

import com.yh.apoplexy.assist.dmo.admin.MenuInfoDmo;

import java.util.List;

/**
 * 二级菜单实体
 * Created by wunder on 2016/10/14 13:42.
 */
public class SecondMenuDto {

    private MenuInfoDmo menuInfoDmo;

    private List<MenuInfoDmo> thirdMenuList;

    public MenuInfoDmo getMenuInfoDmo() {
        return menuInfoDmo;
    }

    public void setMenuInfoDmo(MenuInfoDmo menuInfoDmo) {
        this.menuInfoDmo = menuInfoDmo;
    }

    public List<MenuInfoDmo> getThirdMenuList() {
        return thirdMenuList;
    }

    public void setThirdMenuList(List<MenuInfoDmo> thirdMenuList) {
        this.thirdMenuList = thirdMenuList;
    }
}
