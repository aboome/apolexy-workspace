package com.yh.apoplexy.admin.doctor.member.service.impl;

import com.yh.apoplexy.admin.doctor.member.service.intf.AdminDoctorMemberService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 医生会员信息服务实现类
 * Created by wunder on 16/9/4 13:15.
 */
@Service("adminDoctorMemberService")
@ServiceTrace
public class AdminDoctorMemberServiceImpl implements AdminDoctorMemberService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(DoctorMemberDmo doctorMemberDmo) {

        int i = commonDao.insert(doctorMemberDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(DoctorMemberDmo doctorMemberDmo) {

        int i = commonDao.update(doctorMemberDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(DoctorMemberDmo doctorMemberDmo) {

        int i = commonDao.delete(doctorMemberDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public DoctorMemberDmo selectOne(DoctorMemberDmo doctorMemberDmo) {

        return (DoctorMemberDmo) commonDao.selectOne(doctorMemberDmo);
    }

    @Override
    public List<DoctorMemberDmo> selectListByPage(DoctorMemberDmo con, Page page) {
        return commonDao.selectListByPage("AdminDoctorMemberMapper.queryDoctorMemberCount","AdminDoctorMemberMapper.queryDoctorMemberList",con,page);
    }

}
