package com.yh.apoplexy.admin.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.base.service.intf.AdminPatientNewsService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.knowledge.PatientNewsDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

@Service("adminPatientNewsService")
@ServiceTrace
public class AdminPatientNewsImpI implements AdminPatientNewsService {

	
	@Autowired
	private CommonDao commonDao;
	
	@Override
	public List<PatientNewsDmo> selectListByPage(
			PatientNewsDmo adminPatientNewsDmo, Page page) {
		
		return commonDao.selectListByPage("AdminPatientNewsMapper.patientNewsCount", "AdminPatientNewsMapper.patientNewsList", adminPatientNewsDmo, page);
	}

	@Override
	public PatientNewsDmo selectDoctorNewsDetails(
			PatientNewsDmo adminPatientNewsDmo) {

		return (PatientNewsDmo) commonDao.selectOne(adminPatientNewsDmo);
	}

	@Override
	public Result addPatientNews(PatientNewsDmo adminPatientNewsDmo) {
		int i = commonDao.insert(adminPatientNewsDmo);
		return SqlAssertUtils.insertAssert(i);
	}

	@Override
	public Result updatePatientNewsDetails(
			PatientNewsDmo adminPatientNewsDmo) {
		int i =commonDao.update(adminPatientNewsDmo);
		return SqlAssertUtils.insertAssert(i);
	}

	@Override
	public Result deletePatientNewsDetails(
			PatientNewsDmo adminPatientNewsDmo) {
		int i = commonDao.delete(adminPatientNewsDmo);
		return SqlAssertUtils.insertAssert(i);
	}

}
