package com.yh.apoplexy.patient.health.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.health.HealthDataHisDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.patient.health.service.intf.HealthHisDataService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 患者历史健康数据服务实现类
 * Created by wunder on 16/9/5 18:32.
 */
@Service("healthHisDataService")
@ServiceTrace
public class HealthHisDataServiceImpl implements HealthHisDataService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(HealthDataHisDmo healthDataHisDmo) {

        int i = commonDao.insert(healthDataHisDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(HealthDataHisDmo healthDataHisDmo) {

        int i = commonDao.update(healthDataHisDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(HealthDataHisDmo healthDataHisDmo) {

        int i = commonDao.delete(healthDataHisDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public HealthDataHisDmo find(HealthDataHisDmo healthDataHisDmo) {

        return (HealthDataHisDmo)commonDao.selectOne(healthDataHisDmo);

    }
}
