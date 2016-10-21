package com.yh.apoplexy.admin.patient.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yh.apoplexy.admin.patient.service.intf.AdminPatientHRService;
import com.yh.apoplexy.assist.dto.admin.patient.health.PatientHRInputDto;
import com.yh.apoplexy.assist.dto.admin.patient.health.PatientScreenDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDmo;
import com.yh.apoplexy.assist.dto.admin.patient.PatientHRDto;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.page.Page;


@Service("adminPatientHRService")
@ServiceTrace 

public class AdminPatientHRServiceImpl implements AdminPatientHRService {
	
	
	@Autowired
	private CommonDao commonDao;  
	
    /**
     * 
     * 查询高危筛查列表
     */
	@Override
	public List<PatientScreenDto> queryHRList(PatientHRInputDto con, Page page) {

		return commonDao.selectListByPage("PatientMapper.queryHRListCount", "PatientMapper.queryHRList", con, page);
	}

	/**
	 * 查询高危筛查详情
	 */
	@Override
	public PatientHRDto queryHRDetail(PatientScreenDmo con) {
	    if(con == null){
	    	return null;
	    }
	    
	    HashMap<String,String> map = new HashMap<String,String>();
	    
	    map.put("recordId",con.getId().toString());
	    
		return (PatientHRDto)commonDao.selectOne("PatientMapper.queryHRDetail",map);
	}

}
