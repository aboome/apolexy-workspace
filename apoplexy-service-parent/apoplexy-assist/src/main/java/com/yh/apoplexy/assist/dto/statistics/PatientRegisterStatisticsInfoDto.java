package com.yh.apoplexy.assist.dto.statistics;

import java.util.Date;

import com.yjh.framework.core.entity.Entity;

public class PatientRegisterStatisticsInfoDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2937642365495668271L;
	
	
    private Long countNum;

    private Date countDate;

	/**
	 * @return the countNum
	 */
	public Long getCountNum() {
		return countNum;
	}

	/**
	 * @param countNum the countNum to set
	 */
	public void setCountNum(Long countNum) {
		this.countNum = countNum;
	}

	/**
	 * @return the countDate
	 */
	public Date getCountDate() {
		return countDate;
	}

	/**
	 * @param countDate the countDate to set
	 */
	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}
    
    
    

}
