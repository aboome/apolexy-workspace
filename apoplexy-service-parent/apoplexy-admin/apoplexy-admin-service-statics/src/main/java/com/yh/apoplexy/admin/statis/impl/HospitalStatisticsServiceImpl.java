package com.yh.apoplexy.admin.statis.impl;

import java.util.HashMap;
import java.util.List;

import com.yh.apoplexy.admin.statis.intf.HospitalStatisticsService;
import com.yh.apoplexy.assist.dto.statistics.HospitalStatisticsDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yjh.framework.core.trace.ServiceTrace;


@Service("hospitalStatisticsService")
@ServiceTrace 
public class HospitalStatisticsServiceImpl implements HospitalStatisticsService {

    @Autowired
	private CommonDao commonDao;
	
    @Override
    public List<HospitalStatisticsDto> queryHospitalCount(HospitalInfoDmo con) {

        return commonDao.selectList("StatisticsMapper.queryHospitalCount",con);
    }

}
