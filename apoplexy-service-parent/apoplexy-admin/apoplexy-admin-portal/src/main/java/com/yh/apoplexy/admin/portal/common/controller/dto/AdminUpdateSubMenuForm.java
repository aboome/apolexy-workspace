package com.yh.apoplexy.admin.portal.common.controller.dto;
/**
 * 修改子菜单
 * @author eyelake
 *
 */
public class AdminUpdateSubMenuForm {
	
   private 	String menuName;
   
   private   String menuUrl;
   
   private   String id;
   
   private String  sort;

public String getMenuName() {
	return menuName;
}

public void setMenuName(String menuName) {
	this.menuName = menuName;
}

public String getMenuUrl() {
	return menuUrl;
}

public void setMenuUrl(String menuUrl) {
	this.menuUrl = menuUrl;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getSort() {
	return sort;
}

public void setSort(String sort) {
	this.sort = sort;
}
   
   

}
