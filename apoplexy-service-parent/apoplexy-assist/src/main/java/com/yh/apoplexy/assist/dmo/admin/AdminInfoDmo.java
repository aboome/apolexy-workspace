package com.yh.apoplexy.assist.dmo.admin;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;
/**
 * 管理员信息表
 * @author cc
 *
 */
@Table(name = "t_admin_info")
public class AdminInfoDmo extends Entity{

	private static final long serialVersionUID = -7449719432242280928L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;
	
	/**
	 * 密码
	 */
	@Column(name = "password")
	private String passWord;
	
	/**
	 * 电话号码
	 */
	@Column(name = "phone")
	private String phone;
	
	/**
	 * 电子邮箱
	 */
	@Column(name = "email")
	private String email;
	
	/**
	 * 职位
	 */
	@Column(name = "job")
	private String job;
	
	/**
	 * 描述
	 */
	@Column(name = "user_desc")
	private String userDesc;
	
	/**
	 * 真实姓名
	 */
	@Column(name = "real_name")
	private String realName;
	
	/**
	 * 开始时间
	 */
	@Column(name = "create_time")
	private Date  createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name = "last_update_time")
	private Date  lastUpdateTime;
	
	/**
	 * 登录失败次数
	 */
	@Column(name = "login_fail_count")
	private Long loginFailCount;
	
	/**
	 * 状态
	 */
	@Column(name = "status")
    private   String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}


	/**
	 * @return the userDesc
	 */
	public String getUserDesc() {
		return userDesc;
	}

	/**
	 * @param userDesc the userDesc to set
	 */
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getLoginFailCount() {
		return loginFailCount;
	}

	public void setLoginFailCount(Long loginFailCount) {
		this.loginFailCount = loginFailCount;
	}
	
	
}
