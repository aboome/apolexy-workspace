package com.yh.apoplexy.doctor.cases.discuss.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseResourceDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.DiscussCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.CaseResourceService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 讨论病例资源服务实现类
 * Created by wunder on 16/9/7 15:14.
 */
@Service("caseResourceService")
@ServiceTrace
public class CaseResourceServiceImpl implements CaseResourceService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(CaseResourceDmo caseResourceDmo) {

        int i = commonDao.insert(caseResourceDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result batchInsert(DiscussCaseDetailDto discussCaseDetailDto) {

        Result result = new Result();

        if (CollectionUtils.isEmpty(discussCaseDetailDto.getImageList())){

            return result;

        }

        int i = commonDao.batchInsert("CaseResourceMapper.batchInsertResource",discussCaseDetailDto);

        if (i != discussCaseDetailDto.getImageList().size()){

            result.fail();
            return result;
        }

        return result;
    }

    @Override
    public CaseResourceDmo find(CaseResourceDmo con) {
        return (CaseResourceDmo)commonDao.selectOne(con);
    }

    @Override
    public List<CaseResourceDmo> selectList(CaseResourceDmo con) {
        return commonDao.selectList(con);
    }
}
