package com.yh.apoplexy.assist.dto.patient.health;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;


/**
 * 
 * 健康体征数据详情
 * @author zhangbiao
 *
 */
public class HealthDataDto extends Entity {

	private static final long serialVersionUID = -1431673740801322093L;
    
    private Long id; 
    
	private String userName;
	
    private String sex;
    
    private Date createTime;
    
    private Double highPressure;
    
    private Double lowPressure;
    
    private Double bloodSugar;
    
    private Double bloodFat;

    private String area;
  
    
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
