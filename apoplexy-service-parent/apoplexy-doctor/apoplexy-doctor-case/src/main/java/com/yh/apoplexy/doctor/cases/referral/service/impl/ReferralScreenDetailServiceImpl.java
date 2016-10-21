package com.yh.apoplexy.doctor.cases.referral.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralScreenDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralScreenDetailService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 转诊筛查详情服务实现类
 * Created by wunder on 16/9/22 14:13.
 */
@Service("referralScreenDetailService")
@ServiceTrace
public class ReferralScreenDetailServiceImpl implements ReferralScreenDetailService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(ReferralScreenDetailDmo referralScreenDetailDmo) {

        int i = commonDao.insert(referralScreenDetailDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(ReferralScreenDetailDmo referralScreenDetailDmo) {

        int i = commonDao.update(referralScreenDetailDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(ReferralScreenDetailDmo referralScreenDetailDmo) {

        int i = commonDao.delete(referralScreenDetailDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public ReferralScreenDetailDmo selectOne(ReferralScreenDetailDmo referralScreenDetailDmo) {

        return (ReferralScreenDetailDmo) commonDao.selectOne(referralScreenDetailDmo);

    }

    @Override
    public List<ReferralScreenDetailDmo> selectList(ReferralScreenDetailDmo referralScreenDetailDmo) {
        return commonDao.selectList(referralScreenDetailDmo);
    }

    @Override
    public Result batchInsert(ReferralCaseDetailDto referralScreenDetailDto) {

        Result result = new Result();

        if (CollectionUtils.isEmpty(referralScreenDetailDto.getScreenList())){

            return result;

        }

        int i = commonDao.batchInsert("ReferralCaseMapper.batchInsertDetail", referralScreenDetailDto);

        if (i != referralScreenDetailDto.getScreenList().size()){
            result.fail();
            return result;
        }

        return result;

    }
}
