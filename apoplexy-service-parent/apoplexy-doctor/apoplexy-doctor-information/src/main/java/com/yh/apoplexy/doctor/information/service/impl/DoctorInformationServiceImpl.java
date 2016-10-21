package com.yh.apoplexy.doctor.information.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.information.DoctorNewsDmo;
import com.yh.apoplexy.doctor.information.service.intf.DoctorInformationService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 医生学术更新服务实现类
 * Created by wunder on 16/9/6 18:30.
 */
@Service("doctorInformationService")
@ServiceTrace
public class DoctorInformationServiceImpl implements DoctorInformationService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public DoctorNewsDmo find(DoctorNewsDmo con) {
        return (DoctorNewsDmo)commonDao.selectOne(con);
    }

    @Override
    public List<DoctorNewsDmo> selectListByPage(DoctorNewsDmo con, Page page) {
        return commonDao.selectListByPage("DoctorNewsMapper.countNews","DoctorNewsMapper.queryNews",con,page);
    }
}
