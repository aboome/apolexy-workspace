package com.yh.apoplexy.admin.doctor.cases.ast.service.impl;

import com.yh.apoplexy.admin.doctor.cases.ast.service.intf.AdminAstHistoryService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstHistoryDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
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
@Service("adminAstHistoryService")
@ServiceTrace
public class AdminAstHistoryServiceImpl implements AdminAstHistoryService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public AstHistoryDmo find(AstHistoryDmo con) {
        return (AstHistoryDmo)commonDao.selectList(con);
    }

    @Override
    public List<AstHistoryDmo> selectList(AstHistoryDmo con) {
        return commonDao.selectList(con);
    }
}
