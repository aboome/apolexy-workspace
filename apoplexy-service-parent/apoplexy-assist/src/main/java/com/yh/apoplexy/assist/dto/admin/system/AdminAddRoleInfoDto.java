package com.yh.apoplexy.assist.dto.admin.system;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

/**
 * 新增角色
 * @author eyelake
 *
 */
public class AdminAddRoleInfoDto extends Entity{
	   /**
	    * 角色id
	    */
       private Long roleId;
       /**
        * 菜单id
        */
       private Long menuId;
        /**
         * 录入时间
         */
       private Date createTime;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
       
       
}
