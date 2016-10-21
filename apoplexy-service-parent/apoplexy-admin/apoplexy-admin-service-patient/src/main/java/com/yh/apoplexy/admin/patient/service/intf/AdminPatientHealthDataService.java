package com.yh.apoplexy.admin.patient.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.patient.health.HealthDataDmo;
import com.yh.apoplexy.assist.dmo.patient.health.HealthDataHisDmo;
import com.yh.apoplexy.assist.dto.admin.patient.healthdata.PatientHealthDataInputDto;
import com.yh.apoplexy.assist.dto.patient.health.HealthDataDto;
import com.yjh.framework.page.Page;

/**
 * 
 * 体征数据管理
 * @author zhangbiao
 *
 */
public interface AdminPatientHealthDataService {
     /**
      * 
      * 查询体征数据列表
      * @param con
      * @param page
      * @return
      */
	 public List<HealthDataDto> queryHealDataList(PatientHealthDataInputDto con,Page page);
	 
	 
	 /**
	  * 
	  * 查询体征数据详情
	  * @param con
	  * @return
	  */
	 public HealthDataDto  queryHealDataDetail(HealthDataDmo con);
	 
	 
}
