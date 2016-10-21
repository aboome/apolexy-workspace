package com.yh.apoplexy.base.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yh.apoplexy.base.service.intf.DoctorInfoService;
import com.yh.apoplexy.common.constants.Constants;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 医生信息服务实现类
 * Created by wunder on 16/9/6 10:31.
 */
@Service("doctorInfoService")
@ServiceTrace
public class DoctorInfoServiceImpl implements DoctorInfoService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public DoctorInfoDmo find(DoctorInfoDmo doctorInfoDmo) {
        return (DoctorInfoDmo) commonDao.selectOne(doctorInfoDmo);
    }

    @Override
    public DoctorInfoDmo findDoctorByPhone(String phone) {

        DoctorInfoDmo con = new DoctorInfoDmo();

        con.setPhone(phone);
        con.setStatus(Constants.DoctorStatus.NORMAL);

        return find(con);
    }

}
