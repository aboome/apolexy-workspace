package com.yh.apoplexy.admin.statis.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.statis.intf.DoctorStatisService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.statistics.DoctorStatisticsDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.page.Page;
/**
 * 
 * 统计医生的数量
 * @author zhangbiao
 *
 */
@Service("doctorStatisService")
@ServiceTrace 

public class DoctorStatisServiceImpl implements DoctorStatisService {
	@Autowired
	private CommonDao commonDao;

	
	
	
	@Override
	public List<DoctorStatisticsDto> queryDoctorCount(
			DoctorMemberDmo doctorMemberDmo) {

		return commonDao.selectList("StatisticsMapper.queryDoctorCount",doctorMemberDmo);
		
	}
    

	 

}
