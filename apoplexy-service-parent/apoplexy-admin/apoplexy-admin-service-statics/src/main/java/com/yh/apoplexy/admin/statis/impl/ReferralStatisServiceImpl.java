package com.yh.apoplexy.admin.statis.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.statis.intf.ReferralStatisService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dto.common.StatisReferralInputDto;
import com.yh.apoplexy.assist.dto.statistics.ReferralStatisticsDto;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.page.Page;
/**
 * 
 * 病例转诊统计管理
 * @author zhangbiao
 *
 */

@Service("referralStatisService ")
@ServiceTrace 

public class ReferralStatisServiceImpl implements ReferralStatisService {
	
	
	@Autowired
	private CommonDao commonDao;

	@Override
	public List<ReferralStatisticsDto> querySendReferralList(
			ReferralCaseDmo referralCaseDmo) {
	               
		return commonDao.selectList("StatisticsMapper.querySendReferralListCount",referralCaseDmo);
	}

	@Override
	public List<ReferralStatisticsDto> queryReceiveReferralListCount(
			ReferralCaseDmo referralCaseDmo) {
		
		return commonDao.selectList("StatisticsMapper.queryReceiveReferralListCount",referralCaseDmo);
	}

	@Override
	public List<ReferralStatisticsDto> queryReceiveReferralList(
			ReferralCaseDmo referralCaseDmo) {
		
		return commonDao.selectList("StatisticsMapper.queryReceiveReferralList", referralCaseDmo);
		
	}

	@Override
	public List<ReferralStatisticsDto> queryReceivePatientList(
			ReferralCaseDmo referralCaseDmo) {
	
		return commonDao.selectList("StatisticsMapper.queryReceivePatientList", referralCaseDmo);
	}

	@Override
	public List<ReferralStatisticsDto> queryReceiveTiemList(
			ReferralCaseDmo referralCaseDmo) {

		return  commonDao.selectList("StatisticsMapper.queryReceiveTiemList", referralCaseDmo);
	}

	@Override
	public List<ReferralStatisticsDto> queryReferralTiemList(
			ReferralCaseDmo referralCaseDmo) {
	
		return commonDao.selectList("StatisticsMapper.queryReferralTiemList", referralCaseDmo);
	}


}
