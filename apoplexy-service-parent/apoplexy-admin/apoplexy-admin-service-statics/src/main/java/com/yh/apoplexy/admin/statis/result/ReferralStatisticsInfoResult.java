package com.yh.apoplexy.admin.statis.result;

import java.util.List;

import com.yjh.framework.lang.Result;

public class ReferralStatisticsInfoResult extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6146952733666667404L;

    private List<Long> countNum;

    private List<String> countDate;

	/**
	 * @return the countNum
	 */
	public List<Long> getCountNum() {
		return countNum;
	}

	/**
	 * @param countNum the countNum to set
	 */
	public void setCountNum(List<Long> countNum) {
		this.countNum = countNum;
	}

	/**
	 * @return the countDate
	 */
	public List<String> getCountDate() {
		return countDate;
	}

	/**
	 * @param countDate the countDate to set
	 */
	public void setCountDate(List<String> countDate) {
		this.countDate = countDate;
	}
    
    
    
}
