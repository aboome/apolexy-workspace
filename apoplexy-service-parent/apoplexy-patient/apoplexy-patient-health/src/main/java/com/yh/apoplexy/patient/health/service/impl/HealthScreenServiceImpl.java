package com.yh.apoplexy.patient.health.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientScoreDetailDmo;
import com.yh.apoplexy.assist.dto.patient.health.HealthScreenDetailDto;
import com.yh.apoplexy.assist.dto.patient.health.SubmitHealthScreenDto;
import com.yh.apoplexy.common.constants.PatientConstants;
import com.yh.apoplexy.common.constants.ScoreConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.patient.health.service.intf.HealthScreenDetailService;
import com.yh.apoplexy.patient.health.service.intf.HealthScreenService;
import com.yh.apoplexy.patient.member.service.intf.PatientScoreService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 患者高危筛查服务实现类
 * Created by wunder on 16/9/5 10:12.
 */
@Service("healthScreenService")
@ServiceTrace
public class HealthScreenServiceImpl implements HealthScreenService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private HealthScreenDetailService healthScreenDetailService;

    @Autowired
    private PatientScoreService patientScoreService;

    @Override
    public Result insert(PatientScreenDmo patientScreenDmo) {

        int i = commonDao.insert(patientScreenDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(PatientScreenDmo patientScreenDmo) {

        int i = commonDao.update(patientScreenDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(PatientScreenDmo patientScreenDmo) {

        int i = commonDao.delete(patientScreenDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public PatientScreenDmo selectOne(PatientScreenDmo patientScreenDmo) {

        return (PatientScreenDmo)commonDao.selectOne(patientScreenDmo);

    }

    @Override
    @Transactional
    public Result submitHealthScreenInfo(SubmitHealthScreenDto submitHealthScreenDto) {

        Result result = new Result();
        //插入高危筛选详情
        result = insert(submitHealthScreenDto.getPatientScreenDmo());

        if (!result.isSuccess()){
            throw new AppException(result);
        }

        PatientScreenDmo latestScreenDmo = selectOne(submitHealthScreenDto.getPatientScreenDmo());

        HealthScreenDetailDto historyScreenDetailDto = new HealthScreenDetailDto();

        historyScreenDetailDto.setRecordId(latestScreenDmo.getId());
        historyScreenDetailDto.setList(submitHealthScreenDto.getHistoryList());
        //批量插入既往史详情
        result = healthScreenDetailService.batchInsert(historyScreenDetailDto);

        if (!result.isSuccess()){
            throw new AppException(result);
        }

        HealthScreenDetailDto healthScreenDetailDto = new HealthScreenDetailDto();

        healthScreenDetailDto.setRecordId(latestScreenDmo.getId());
        healthScreenDetailDto.setList(submitHealthScreenDto.getScreenList());
        //批量插入初筛详情
        result = healthScreenDetailService.batchInsert(healthScreenDetailDto);

        if (!result.isSuccess()){
            throw new AppException(result);
        }

        //增加积分
        if(checkFirstSubmit(submitHealthScreenDto.getPatientScreenDmo().getUserId())){

            PatientScoreDetailDmo patientScoreDetailDmo = new PatientScoreDetailDmo();

            patientScoreDetailDmo.setUserId(submitHealthScreenDto.getPatientScreenDmo().getUserId());
            patientScoreDetailDmo.setUserName(submitHealthScreenDto.getPatientScreenDmo().getUserName());
            patientScoreDetailDmo.setEvent(PatientConstants.ScoreEvent.HEALTH_SCREEN);
            patientScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_PATIENT_HEALTH_SCREEN));
            patientScoreDetailDmo.setScore(10L);
            patientScoreDetailDmo.setTime(DateUtil.getDate());

            result = patientScoreService.addScoreEvent(patientScoreDetailDmo);

            if (!result.isSuccess()) {

                throw new AppException(result);
            }

        }

        return result;
    }

    /**
     * 检查是否首次提交
     * @param userId
     * @return
     */
    private boolean checkFirstSubmit(Long userId){

        PatientScreenDmo con = new PatientScreenDmo();

        con.setUserId(userId);

        Long count = commonDao.selectCount("HealthScreenMapper.countSubmit",con);

        return count.equals(1L);

    }
}
