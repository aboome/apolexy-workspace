package com.yh.apoplexy.admin.patient.service.impl;


/**
 * 
 * 高危筛查管理
 */
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.patient.service.intf.AdminPatientHealthDataService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.health.HealthDataDmo;
import com.yh.apoplexy.assist.dto.admin.patient.healthdata.PatientHealthDataInputDto;
import com.yh.apoplexy.assist.dto.patient.health.HealthDataDto;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.page.Page;

@Service("adminPatientHealthDataService")
@ServiceTrace 

public class AdminPatientHealthDataServiceImpl implements
		AdminPatientHealthDataService {

	@Autowired
	private CommonDao commonDao;
	
    /**
     * 
     * 查询体征数据列表,这四个参数可以不填
     */
	@Override
	public List<HealthDataDto> queryHealDataList(PatientHealthDataInputDto con, Page page) {

		return commonDao.selectListByPage("PatientMapper.queryHealthDataListCount", "PatientMapper.queryHealthDataList", con, page);
		 
	}

	/**
	 * 
	 * 查询体征数据详情，根据id来查询
	 */
	@Override
	public  HealthDataDto queryHealDataDetail(HealthDataDmo con) {
		if(con == null){
			return null;
		} 
	    
		if(con.getId() == null){
			return null;
		}
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("id",con.getId().toString());
		//根据id去体征数据信息表里找
	    return  (HealthDataDto)commonDao.selectOne("PatientMapper.queryHealthDataDetail",map);
	}

}
