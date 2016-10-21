package com.yh.apoplexy.doctor.cases.ast.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstMedicationDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstMedicationService;
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
@Service("astMedicationService")
@ServiceTrace
public class AstMedicationServiceImpl implements AstMedicationService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(AstMedicationDmo astMedicationDmo) {

        int i = commonDao.insert(astMedicationDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result batchInsert(AstCaseDetailDto astCaseDetailDto) {

        Result result = new Result();

        if (CollectionUtils.isEmpty(astCaseDetailDto.getHisMedicaitionList())){

            return result;

        }

        int i = commonDao.batchInsert("AstMedicationMapper.batchInsertMedication",astCaseDetailDto);

        if (i != astCaseDetailDto.getHisMedicaitionList().size()){

            result.fail("IBI-0000","批量插入既往用药失败");
            return result;
        }

        return result;
    }

    @Override
    public AstMedicationDmo find(AstMedicationDmo con) {
        return (AstMedicationDmo)commonDao.selectOne(con);
    }

    @Override
    public List<AstMedicationDmo> selectList(AstMedicationDmo con) {
        return commonDao.selectList(con);
    }
}
