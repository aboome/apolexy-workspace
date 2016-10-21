package com.yh.apoplexy.patient.health.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yh.apoplexy.assist.dto.patient.health.HealthScreenDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.patient.health.service.intf.HealthScreenDetailService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 高危筛选详情服务实现类
 * Created by wunder on 16/9/5 11:26.
 */
@Service("healthScreenDetailService")
@ServiceTrace
public class HealthScreenDetailServiceImpl implements HealthScreenDetailService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(PatientScreenDetailDmo patientScreenDetailDmo) {

        int i = commonDao.insert(patientScreenDetailDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(PatientScreenDetailDmo patientScreenDetailDmo) {

        int i = commonDao.update(patientScreenDetailDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(PatientScreenDetailDmo patientScreenDetailDmo) {

        int i = commonDao.delete(patientScreenDetailDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public PatientScreenDetailDmo selectOne(PatientScreenDetailDmo patientScreenDetailDmo) {

        return (PatientScreenDetailDmo)commonDao.selectOne(patientScreenDetailDmo);

    }

    @Override
    public Result batchInsert(HealthScreenDetailDto healthScreenDetailDto) {

        Result result = new Result();

        int i = commonDao.batchInsert("HealthScreenMapper.batchInsertDetail", healthScreenDetailDto);

        if (i != healthScreenDetailDto.getList().size()){
            result.fail();
            return result;
        }

        return result;
    }
}
