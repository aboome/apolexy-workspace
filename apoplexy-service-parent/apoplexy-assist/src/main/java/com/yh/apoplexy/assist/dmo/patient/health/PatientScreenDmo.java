package com.yh.apoplexy.assist.dmo.patient.health;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;
/**
 * 高危筛查信息表
 * @author zhangbiao
 */
@Table(name = "t_patient_screen_info")
public class PatientScreenDmo extends Entity {

    private static final long serialVersionUID = 6238758375931972567L;

    /**
     * 主键
     */
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private Long userId;
	
	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;
	
	/**
	 * 年龄
	 */
	@Column(name = "age")
	private Long age;
	
	/**
	 * 性别
	 */
	@Column(name = "sex")
	private String sex;
	
	/**
	 * 发生概率
	 */
	@Column(name = "incidence")
	private Long incidence;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getIncidence() {
        return incidence;
    }

    public void setIncidence(Long incidence) {
        this.incidence = incidence;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
