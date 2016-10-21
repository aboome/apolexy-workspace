package com.yh.apoplexy.patient.health.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.health.PatientEmergencyDmo;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientScoreDetailDmo;
import com.yh.apoplexy.common.constants.PatientConstants;
import com.yh.apoplexy.common.constants.ScoreConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.patient.health.service.intf.PatientEmergencyService;
import com.yh.apoplexy.patient.member.service.intf.PatientScoreService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 中风急救服务实现类
 * Created by wunder on 16/9/5 22:14.
 */
@Service("patientEmergencyService")
@ServiceTrace
public class PatientEmergencyServiceImpl implements PatientEmergencyService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private PatientScoreService patientScoreService;

    @Override
    public Result insert(PatientEmergencyDmo patientEmergencyDmo) {

        int i = commonDao.insert(patientEmergencyDmo);

        return SqlAssertUtils.insertAssert(i);

    }

    @Override
    @Transactional
    public Result submitEmergency(PatientEmergencyDmo patientEmergencyDmo) {

        Result result = new Result();

        result = insert(patientEmergencyDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        //增加积分
        if(checkFirstSubmit(patientEmergencyDmo.getUserId())){

            PatientScoreDetailDmo patientScoreDetailDmo = new PatientScoreDetailDmo();

            patientScoreDetailDmo.setUserId(patientEmergencyDmo.getUserId());
            patientScoreDetailDmo.setUserName(patientEmergencyDmo.getUserName());
            patientScoreDetailDmo.setEvent(PatientConstants.ScoreEvent.EMERGENCY);
            patientScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_PATIENT_APOPLEXY_EMERGENCY));
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

        Long count = commonDao.selectCount("PatientEmergencyMapper.countSubmit",con);

        return count.equals(1L);

    }

}
