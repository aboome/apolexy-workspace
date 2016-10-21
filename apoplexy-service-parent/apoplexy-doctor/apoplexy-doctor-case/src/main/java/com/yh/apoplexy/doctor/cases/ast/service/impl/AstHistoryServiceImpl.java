package com.yh.apoplexy.doctor.cases.ast.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstHistoryDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstHistoryService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 既往史服务实现类
 * Created by wunder on 16/9/10 16:49.
 */
@Service("astHistoryService")
@ServiceTrace
public class AstHistoryServiceImpl implements AstHistoryService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(AstHistoryDmo astHistoryDmo) {

        int i = commonDao.insert(astHistoryDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result batchInsert(AstCaseDetailDto astCaseDetailDto) {

        Result result = new Result();

        if (CollectionUtils.isEmpty(astCaseDetailDto.getHisIllness())){

            return result;

        }

        int i = commonDao.batchInsert("AstHistoryMapper.batchInsertHistory",astCaseDetailDto);

        if (i != astCaseDetailDto.getHisIllness().size()){

            result.fail("IBI-0000","批量插入既往史失败");
            return result;
        }

        return result;

    }

    @Override
    public AstHistoryDmo find(AstHistoryDmo con) {
        return (AstHistoryDmo)commonDao.selectList(con);
    }

    @Override
    public List<AstHistoryDmo> selectList(AstHistoryDmo con) {
        return commonDao.selectList(con);
    }
}
