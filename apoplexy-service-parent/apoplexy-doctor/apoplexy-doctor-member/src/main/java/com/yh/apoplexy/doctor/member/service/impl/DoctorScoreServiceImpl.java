package com.yh.apoplexy.doctor.member.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorScoreDetailDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorScoreService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 医生积分服务实现类
 * Created by wunder on 16/9/6 20:51.
 */
@Service("doctorScoreService")
@ServiceTrace
public class DoctorScoreServiceImpl implements DoctorScoreService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    public Result insert(DoctorScoreDetailDmo doctorScoreDetailDmo) {

        int i = commonDao.insert(doctorScoreDetailDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(DoctorScoreDetailDmo doctorScoreDetailDmo) {

        int i = commonDao.update(doctorScoreDetailDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(DoctorScoreDetailDmo doctorScoreDetailDmo) {

        int i = commonDao.delete(doctorScoreDetailDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public DoctorScoreDetailDmo find(DoctorScoreDetailDmo doctorScoreDetailDmo) {

        return (DoctorScoreDetailDmo)commonDao.selectOne(doctorScoreDetailDmo);

    }

    @Override
    public List<DoctorScoreDetailDmo> selectListByPage(DoctorScoreDetailDmo con, Page page) {

        return commonDao.selectListByPage("DoctorScoreMapper.countScoreDetail","DoctorScoreMapper.queryScoreDetail",con,page);

    }

    @Override
    public Result addScoreEvent(DoctorScoreDetailDmo doctorScoreDetailDmo) {

        Result result = new Result();

        result = insert(doctorScoreDetailDmo);

        if (!result.isSuccess()){
            return result;
        }

        DoctorMemberDmo doctorMemberDmo = new DoctorMemberDmo();

        doctorMemberDmo.setId(doctorScoreDetailDmo.getUserId());
        doctorMemberDmo.setScore(doctorScoreDetailDmo.getScore());

        result = doctorMemberService.updateMemberScore(doctorMemberDmo);

        return result;
    }
}
