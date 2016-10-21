package com.yh.apoplexy.admin.statis.impl;

import com.yh.apoplexy.admin.statis.intf.DownloadStatisticsService;
import com.yh.apoplexy.assist.dmo.common.AppDownloadDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.assist.dto.statistics.DownloadStatisticInfoDto;
import com.yh.apoplexy.assist.dto.statistics.PatientRegisterStatisticsInfoDto;
import com.yh.apoplexy.assist.dto.statistics.QueryDoctorRegisterStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.QueryDownloadStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.QueryPatientRegisterStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.ReferralStatisticsInfoDto;
import com.yh.apoplexy.common.constants.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yjh.framework.core.trace.ServiceTrace;

import java.util.List;

@Service("downloadStatisticsService")
@ServiceTrace 
public class DownloadStatisticsServiceImpl implements DownloadStatisticsService {

    @Autowired
	private CommonDao commonDao;
	


	@Override
	public List<DownloadStatisticInfoDto> queryDownloadTestCount(QueryDownloadStatisticsDto downloadStatisticsDto) {
		
		return commonDao.selectList("StatisticsMapper.queryDoctorDownladCountList", downloadStatisticsDto);
	}



	@Override
	public List<ReferralStatisticsInfoDto> queryDoctorRegisterCount(
			QueryDoctorRegisterStatisticsDto doctorRegisterStatisticsDto) {
		
		return commonDao.selectList("StatisticsMapper.queryDoctorRegisterCount", doctorRegisterStatisticsDto);
	}



	@Override
	public List<PatientRegisterStatisticsInfoDto> queryPatientRegisterCount(
			QueryPatientRegisterStatisticsDto patientRegisterStatisticsDto) {

		return commonDao.selectList("StatisticsMapper.queryPatientRegisterCount", patientRegisterStatisticsDto);
	}





	
	
	
 
}
