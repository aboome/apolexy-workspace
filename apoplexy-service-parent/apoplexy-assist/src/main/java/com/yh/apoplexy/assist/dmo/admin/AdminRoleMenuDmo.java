package com.yh.apoplexy.assist.dmo.admin;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;
/**
 * 角色与菜单关联表
 * @author eyelake
 *
 */
@Table(name = "t_role_menu")
public class AdminRoleMenuDmo extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1807036010941834980L;
	
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	/**
	 * 角色id
	 */
	@Column(name = "role_id")
	private Long roleId;
	/**
	 * 菜单id
	 */
	@Column(name = "menu_id")
	private Long  menuId;
	/**
	 * 录入时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
