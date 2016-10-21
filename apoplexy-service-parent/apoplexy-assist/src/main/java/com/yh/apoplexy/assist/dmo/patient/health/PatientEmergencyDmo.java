package com.yh.apoplexy.assist.dmo.patient.health;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;

/**
 * 中风急救信息表
 * @author zhangbiao
 */
@Table(name = "t_patient_emergency_info")
public class PatientEmergencyDmo extends Entity {

    private static final long serialVersionUID = 1096613592503299259L;

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
	 * 首次发病时间
	 */
	@Column(name = "onset_time")
	private Date onsetTime;
	
	/**
	 * 脸
	 */
	@Column(name = "face")
	private String face;
	
	/**
	 * 手臂
	 */
	@Column(name = "arm")
	private String arm;
	
	/**
	 * 语言
	 */
	@Column(name = "speech")
	private String speech;
	
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

	public Date getOnsetTime() {
		return onsetTime;
	}

	public void setOnsetTime(Date onsetTime) {
		this.onsetTime = onsetTime;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getArm() {
		return arm;
	}

	public void setArm(String arm) {
		this.arm = arm;
	}

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
