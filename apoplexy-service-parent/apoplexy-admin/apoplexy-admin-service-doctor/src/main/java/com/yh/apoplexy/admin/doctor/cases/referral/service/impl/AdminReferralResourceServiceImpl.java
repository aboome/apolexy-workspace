package com.yh.apoplexy.admin.doctor.cases.referral.service.impl;

import com.yh.apoplexy.admin.doctor.cases.referral.service.intf.AdminReferralResourceService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralResourceDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 转诊病例资源服务实现类
 * Created by wunder on 16/9/9 11:12.
 */
@Service("adminReferralResourceService")
@ServiceTrace
public class AdminReferralResourceServiceImpl implements AdminReferralResourceService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(ReferralResourceDmo referralResourceDmo) {

        int i = commonDao.insert(referralResourceDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public ReferralResourceDmo find(ReferralResourceDmo con) {
        return (ReferralResourceDmo)commonDao.selectOne(con);
    }

    @Override
    public List<ReferralResourceDmo> selectList(ReferralResourceDmo con) {
        return commonDao.selectList(con);
    }
}
