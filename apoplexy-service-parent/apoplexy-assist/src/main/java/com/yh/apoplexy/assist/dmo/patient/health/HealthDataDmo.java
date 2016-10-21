package com.yh.apoplexy.assist.dmo.patient.health;

import java.util.Date;

import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Column;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GeneratedValue;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.GenerationType;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Id;
import com.yjh.framework.core.dao.mybatis.torm.sql.annotation.Table;
import com.yjh.framework.core.entity.Entity;

/**
 * 体征数据信息表
 * @author zhangbiao
 */
@Table(name = "t_patient_health_info")
public class HealthDataDmo extends Entity {

    private static final long serialVersionUID = 4521845679662977339L;

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
	 * 舒张压
	 */
	@Column(name = "high_pressure")
	private Double highPressure;
	
	/**
	 * 低压
	 */
	@Column(name = "low_pressure")
	private Double lowPressure;
	
	/**
	 * 血糖
	 */
	@Column(name = "blood_sugar")
	private Double bloodSugar;
	
	
	/**
	 * 血脂
	 */
	@Column(name = "blood_fat")
	private Double bloodFat;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 *最后更新时间
	 */
	@Column(name = "last_update_time")
	private Date lastUpdateTime;

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

    public Double getHighPressure() {
		return highPressure;
	}

	public void setHighPressure(Double highPressure) {
		this.highPressure = highPressure;
	}

	public Double getLowPressure() {
		return lowPressure;
	}

	public void setLowPressure(Double lowPressure) {
		this.lowPressure = lowPressure;
	}

	public Double getBloodSugar() {
		return bloodSugar;
	}

	public void setBloodSugar(Double bloodSugar) {
		this.bloodSugar = bloodSugar;
	}

	public Double getBloodFat() {
		return bloodFat;
	}

	public void setBloodFat(Double bloodFat) {
		this.bloodFat = bloodFat;
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

}
