package com.yh.apoplexy.assist.dto.common;

import com.yjh.framework.core.entity.Entity;

public class StatisPatientInputDto extends Entity {
	
	
     /**
	 * 
	 */
	private static final long serialVersionUID = 6357642249238583942L;
	
	
    /**
     * 第一张fast量表是否填写
     */
	private String fast1;
     /**
      * 
      * 第二张fast量表是否填写
      */
    private String fast2;
     
    /**
     * 患者所在的上海行政区
     */
    private String area;

	public String getFast1() {
		return fast1;
	}

	public void setFast1(String fast1) {
		this.fast1 = fast1;
	}

	public String getFast2() {
		return fast2;
	}

	public void setFast2(String fast2) {
		this.fast2 = fast2;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
    
}
