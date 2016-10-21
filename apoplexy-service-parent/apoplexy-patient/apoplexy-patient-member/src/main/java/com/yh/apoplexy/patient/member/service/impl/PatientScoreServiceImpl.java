package com.yh.apoplexy.patient.member.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientScoreDetailDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.member.service.intf.PatientScoreService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 患者积分服务实现类
 * Created by wunder on 16/9/5 23:20.
 */
@Service("patientScoreService")
@ServiceTrace
public class PatientScoreServiceImpl implements PatientScoreService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private PatientMemberService patientMemberService;

    @Override
    public Result insert(PatientScoreDetailDmo patientScoreDetailDmo) {

        int i = commonDao.insert(patientScoreDetailDmo);

        return SqlAssertUtils.insertAssert(i);

    }

    @Override
    public Result update(PatientScoreDetailDmo patientScoreDetailDmo) {

        int i = commonDao.update(patientScoreDetailDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(PatientScoreDetailDmo patientScoreDetailDmo) {

        int i = commonDao.delete(patientScoreDetailDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public PatientScoreDetailDmo find(PatientScoreDetailDmo patientScoreDetailDmo) {
        return (PatientScoreDetailDmo)commonDao.selectOne(patientScoreDetailDmo);
    }

    @Override
    public List<PatientScoreDetailDmo> selectListByPage(PatientScoreDetailDmo con, Page page) {
        return commonDao.selectListByPage("PatientScoreMapper.countScoreDetail","PatientScoreMapper.queryScoreDetail",con,page);
    }

    @Override
    public Result addScoreEvent(PatientScoreDetailDmo patientScoreDetailDmo) {

        Result result = new Result();

        result = insert(patientScoreDetailDmo);

        if (!result.isSuccess()){
            return result;
        }

        PatientMemberDmo patientMemberDmo = new PatientMemberDmo();

        patientMemberDmo.setId(patientScoreDetailDmo.getUserId());
        patientMemberDmo.setScore(patientScoreDetailDmo.getScore());

        result = patientMemberService.updateMemberScore(patientMemberDmo);

        return result;
    }

}
