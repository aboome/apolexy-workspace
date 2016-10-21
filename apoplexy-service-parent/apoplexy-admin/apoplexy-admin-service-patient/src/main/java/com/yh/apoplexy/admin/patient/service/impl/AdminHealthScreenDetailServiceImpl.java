package com.yh.apoplexy.admin.patient.service.impl;

import com.yh.apoplexy.admin.patient.service.intf.AdminHealthScreenDetailService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yjh.framework.core.trace.ServiceTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Admin端高危筛查详情服务实现类
 * Created by wunder on 16/9/19 16:23.
 */
@Service("adminHealthScreenDetailService")
@ServiceTrace
public class AdminHealthScreenDetailServiceImpl implements AdminHealthScreenDetailService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public List<PatientScreenDetailDmo> selectList(PatientScreenDetailDmo con) {

        return commonDao.selectList(con);

    }
}
