package com.yh.apoplexy.assist.dto.admin.log;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;
/**
 * 
 * 查询系统日志列表得时候，作为输入的dto
 * @author zhangbiao
 *
 */
public class SystemLogInputDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6137684258886132289L;
	
    private String operateUserName;
    
    private String model;
    
    private Date startTime;
    
    private Date endTime;

	public String getOperateUserName() {
		return operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
