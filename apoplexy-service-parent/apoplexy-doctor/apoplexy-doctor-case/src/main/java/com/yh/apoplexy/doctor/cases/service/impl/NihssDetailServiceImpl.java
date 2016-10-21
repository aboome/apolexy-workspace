package com.yh.apoplexy.doctor.cases.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.service.intf.NihssDetailService;
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
@Service("nihssDetailService")
@ServiceTrace
public class NihssDetailServiceImpl implements NihssDetailService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(NihssDetailDmo nihssDetailDmo) {

        int i = commonDao.insert(nihssDetailDmo);

        return SqlAssertUtils.insertAssert(i);

    }

    @Override
    public Result batchInsertReferral(ReferralCaseDetailDto referralCaseDetailDto) {

        Result result = new Result();

        if (CollectionUtils.isEmpty(referralCaseDetailDto.getNihssList())){

            return result;

        }

        int i = commonDao.batchInsert("NihssDetailMapper.batchInsertReferral",referralCaseDetailDto);

        if (i != referralCaseDetailDto.getNihssList().size()){

            result.fail("RBI-0001","批量插入NIHSS失败");
            return result;
        }

        return result;

    }

    @Override
    public Result batchInsertAst(AstCaseDetailDto astCaseDetailDto) {

        Result result = new Result();

        if (CollectionUtils.isEmpty(astCaseDetailDto.getNihssList())){

            return result;

        }

        int i = commonDao.batchInsert("NihssDetailMapper.batchInsertAst",astCaseDetailDto);

        if (i != astCaseDetailDto.getNihssList().size()){

            result.fail("RBI-0001","批量插入NIHSS失败");
            return result;
        }

        return result;

    }

    @Override
    public NihssDetailDmo find(NihssDetailDmo con) {
        return (NihssDetailDmo)commonDao.selectOne(con);
    }

    @Override
    public List<NihssDetailDmo> selectList(NihssDetailDmo con) {
        return commonDao.selectList(con);
    }
}
