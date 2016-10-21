package com.yh.apoplexy.assist.dto.admin.patient.emergency;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;
/**
 * 
 * 查询高危筛查列表
 * @author zhangbiao
 *
 */
public class PatientEmergencyInputDto extends Entity {

	private static final long serialVersionUID = 8817791949309721006L;
	
	private String patientName;
	
	private String areaName;
	//开始时间
	private Date startTime;
    //结束时间
	private Date endTime;
	
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
