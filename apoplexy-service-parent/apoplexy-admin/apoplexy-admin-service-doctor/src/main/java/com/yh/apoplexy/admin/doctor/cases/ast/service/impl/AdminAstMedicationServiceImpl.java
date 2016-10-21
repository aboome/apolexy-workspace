package com.yh.apoplexy.admin.doctor.cases.ast.service.impl;

import com.yh.apoplexy.admin.doctor.cases.ast.service.intf.AdminAstMedicationService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstMedicationDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 既往用药服务实现类
 * Created by wunder on 16/9/10 16:58.
 */
@Service("adminAstMedicationService")
@ServiceTrace
public class AdminAstMedicationServiceImpl implements AdminAstMedicationService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public AstMedicationDmo find(AstMedicationDmo con) {
        return (AstMedicationDmo)commonDao.selectOne(con);
    }

    @Override
    public List<AstMedicationDmo> selectList(AstMedicationDmo con) {
        return commonDao.selectList(con);
    }
}
