package com.yh.apoplexy.admin.patient.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yh.apoplexy.admin.patient.service.intf.AdminPatientZFEmerService;

import com.yh.apoplexy.assist.dto.admin.patient.emergency.PatientEmergencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.health.PatientEmergencyDmo;
import com.yh.apoplexy.assist.dto.admin.patient.emergency.PatientEmergencyInputDto;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.page.Page;


@Service("adminPatientZFEmerService")
@ServiceTrace 

public class AdminPatientZFEmerServiceImpl implements AdminPatientZFEmerService {
	
	
	/**
	 * 查询中风急救列表，根据患者名称，区域，时间段,如果参数不填，就是全部
	 */
	@Autowired
	private CommonDao commonDao;

	@Override
	public List<PatientEmergencyDto> queryZFEmerList(PatientEmergencyInputDto con, Page page) {

		return commonDao.selectListByPage("PatientMapper.queryZFEmergencyListCount", "PatientMapper.queryZFEmergencyList", con, page);
	}
	
	
  /**
   * 
   * 查询中风急救详情，单表操作
   */
	@Override
	public  PatientEmergencyDmo queryZFEmerDetail( PatientEmergencyDmo con) {
		 
		if(con == null){
				return null;
		}
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("recordId", con.getId().toString());
		
 		return (PatientEmergencyDmo)commonDao.selectOne(con);
	}

}
