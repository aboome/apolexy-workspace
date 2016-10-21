package com.yh.apoplexy.doctor.cases.ast.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstResourcesDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstResourceService;
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
@Service("astResourceService")
@ServiceTrace
public class AstResourceServiceImpl implements AstResourceService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(AstResourcesDmo astResourcesDmo) {

        int i = commonDao.insert(astResourcesDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result batchInsert(AstCaseDetailDto astCaseDetailDto) {

        Result result = new Result();

        List<AstResourcesDmo> imageList = new ArrayList<>();

        imageList.addAll(astCaseDetailDto.getCtList());
        imageList.addAll(astCaseDetailDto.getCtaList());
        imageList.addAll(astCaseDetailDto.getCtpList());

        astCaseDetailDto.setImageList(imageList);

        if (CollectionUtils.isEmpty(astCaseDetailDto.getImageList())){

            return result;

        }

        int i = commonDao.batchInsert("AstResourceMapper.batchInsertResource",astCaseDetailDto);

        if (i != astCaseDetailDto.getImageList().size()){

            result.fail("IBI-0000","批量插入图片失败");
            return result;
        }

        return result;

    }

    @Override
    public AstResourcesDmo find(AstResourcesDmo con) {

        return (AstResourcesDmo)commonDao.selectOne(con);
    }

    @Override
    public List<AstResourcesDmo> selectList(AstResourcesDmo con) {
        return commonDao.selectList(con);
    }
}
