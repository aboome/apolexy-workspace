/**
 * 
 */
package com.yh.apoplexy.assist.dto.common;

import com.yjh.framework.core.entity.Entity;

/**
 * @author zhangbiao
 *
 */
public class StatisReferralInputDto extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5543792341885509965L;
	/**
	 *医院等级
	 */
	private String level;
	/**
	 * 
	 * 转诊患者类型
	 */
	private String referralType;
	
	/**
	 * 传进来的月份参数
	 */
	private String month;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getReferralType() {
		return referralType;
	}

	public void setReferralType(String referralType) {
		this.referralType = referralType;
	}

	 

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
