package com.yh.apoplexy.patient.health.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.health.HealthDataDmo;
import com.yh.apoplexy.assist.dmo.patient.health.HealthDataHisDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientScoreDetailDmo;
import com.yh.apoplexy.assist.dto.patient.health.QueryHealthDataDto;
import com.yh.apoplexy.common.constants.PatientConstants;
import com.yh.apoplexy.common.constants.ScoreConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.patient.health.service.intf.HealthDataService;
import com.yh.apoplexy.patient.health.service.intf.HealthHisDataService;
import com.yh.apoplexy.patient.member.service.intf.PatientScoreService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 患者健康数据服务实现类
 * Created by wunder on 16/9/5 18:32.
 */
@Service("healthDataService")
@ServiceTrace
public class HealthDataServiceImpl implements HealthDataService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private HealthHisDataService healthHisDataService;

    @Autowired
    private PatientScoreService patientScoreService;

    @Override
    public Result insert(HealthDataDmo healthDataDmo) {

        int i = commonDao.insert(healthDataDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(HealthDataDmo healthDataDmo) {

        int i = commonDao.update(healthDataDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(HealthDataDmo healthDataDmo) {

        int i = commonDao.delete(healthDataDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public HealthDataDmo find(HealthDataDmo healthDataDmo) {

        return (HealthDataDmo) commonDao.selectOne(healthDataDmo);

    }

    @Override
    @Transactional
    public Result submitHealthData(HealthDataDmo healthDataDmo) {

        Result result = new Result();

        if (checkFirstSubmitToday(healthDataDmo.getUserId())) {

            result = insert(healthDataDmo);

            if (!result.isSuccess()) {

                throw new AppException(result);
            }

            //增加积分
            PatientScoreDetailDmo patientScoreDetailDmo = new PatientScoreDetailDmo();

            patientScoreDetailDmo.setUserId(healthDataDmo.getUserId());
            patientScoreDetailDmo.setUserName(healthDataDmo.getUserName());
            patientScoreDetailDmo.setEvent(PatientConstants.ScoreEvent.HEALTH_DATA);
            patientScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_PATIENT_HEALTH_DATA));
            patientScoreDetailDmo.setScore(10L);
            patientScoreDetailDmo.setTime(DateUtil.getDate());

            result = patientScoreService.addScoreEvent(patientScoreDetailDmo);

            if (!result.isSuccess()) {

                throw new AppException(result);
            }

            return result;

        }

        HealthDataDmo con = new HealthDataDmo();

        con.setUserId(healthDataDmo.getUserId());
        con.setCreateTime(DateUtil.getDate());

        HealthDataDmo existHealthDataDmo = findTodayHealthData(con);

        if (null == existHealthDataDmo){

            result.fail("SHD-0000","提交体征数据失败");
            return result;
        }

        result = delete(existHealthDataDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        HealthDataHisDmo healthDataHisDmo = new HealthDataHisDmo();

        BeanUtils.copyProperties(existHealthDataDmo, healthDataHisDmo);

        result = healthHisDataService.insert(healthDataHisDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        result = insert(healthDataDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;
    }

    @Override
    public List<HealthDataDmo> selectListByDate(Long userId, Date beginDate, Date endDate) {

        QueryHealthDataDto con = new QueryHealthDataDto();

        con.setUserId(userId);
        con.setBeginDate(beginDate);
        con.setEndDate(endDate);

        return commonDao.selectList("HealthDataMapper.queryHealthDataByDate",con);
    }

    @Override
    public HealthDataDmo findTodayHealthData(HealthDataDmo healthDataDmo) {

        return (HealthDataDmo)commonDao.selectOne("HealthDataMapper.findSubmitToday",healthDataDmo);
    }

    /**
     * 检查是否今日首次提交
     * @param userId
     * @return
     */
    private boolean checkFirstSubmitToday(Long userId){

        HealthDataDmo con = new HealthDataDmo();

        con.setUserId(userId);
        con.setCreateTime(DateUtil.getDate());

        Long count = commonDao.selectCount("HealthDataMapper.countSubmitToday",con);

        return count.equals(0L);

    }


}
