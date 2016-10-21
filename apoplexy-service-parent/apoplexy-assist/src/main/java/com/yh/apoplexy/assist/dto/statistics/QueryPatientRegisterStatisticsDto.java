package com.yh.apoplexy.assist.dto.statistics;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

public class QueryPatientRegisterStatisticsDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -344640552564809060L;
	
	
    private Date startDate;

    private Date endDate;

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
    
    

}
