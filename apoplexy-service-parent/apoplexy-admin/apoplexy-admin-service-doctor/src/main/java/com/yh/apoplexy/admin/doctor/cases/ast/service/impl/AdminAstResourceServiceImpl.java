package com.yh.apoplexy.admin.doctor.cases.ast.service.impl;

import com.yh.apoplexy.admin.doctor.cases.ast.service.intf.AdminAstResourceService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstResourcesDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * AST病例资源服务实现类
 * Created by wunder on 16/9/10 16:37.
 */
@Service("adminAstResourceService")
@ServiceTrace
public class AdminAstResourceServiceImpl implements AdminAstResourceService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public AstResourcesDmo find(AstResourcesDmo con) {

        return (AstResourcesDmo)commonDao.selectOne(con);
    }

    @Override
    public List<AstResourcesDmo> selectList(AstResourcesDmo con) {
        return commonDao.selectList(con);
    }
}
