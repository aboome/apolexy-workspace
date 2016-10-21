package com.yh.apoplexy.doctor.cases.referral.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralResourceDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralResourceService;
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
@Service("referralResourceService")
@ServiceTrace
public class ReferralResourceServiceImpl implements ReferralResourceService{

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(ReferralResourceDmo referralResourceDmo) {

        int i = commonDao.insert(referralResourceDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result batchInsert(ReferralCaseDetailDto referralCaseDetailDto) {

        Result result = new Result();

        if (CollectionUtils.isEmpty(referralCaseDetailDto.getImageList())){

            return result;

        }

        int i = commonDao.batchInsert("ReferralResourceMapper.batchInsertResource",referralCaseDetailDto);

        if (i != referralCaseDetailDto.getImageList().size()){

            result.fail("RBI-0001","批量插入转诊病例资源失败");
            return result;
        }

        return result;
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
