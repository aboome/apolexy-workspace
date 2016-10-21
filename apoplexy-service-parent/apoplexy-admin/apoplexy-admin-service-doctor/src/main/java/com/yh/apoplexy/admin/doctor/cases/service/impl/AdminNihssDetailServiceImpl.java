package com.yh.apoplexy.admin.doctor.cases.service.impl;

import com.yh.apoplexy.admin.doctor.cases.service.intf.AdminNihssDetailService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * NIHSS详情服务实现类
 * Created by wunder on 16/9/9 11:39.
 */
@Service("adminNihssDetailService")
@ServiceTrace
public class AdminNihssDetailServiceImpl implements AdminNihssDetailService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public NihssDetailDmo find(NihssDetailDmo con) {
        return (NihssDetailDmo)commonDao.selectOne(con);
    }

    @Override
    public List<NihssDetailDmo> selectList(NihssDetailDmo con) {
        return commonDao.selectList(con);
    }
}
