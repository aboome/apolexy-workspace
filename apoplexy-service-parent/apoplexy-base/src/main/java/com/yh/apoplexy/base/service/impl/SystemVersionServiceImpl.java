package com.yh.apoplexy.base.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.SystemVersionInfoDmo;
import com.yh.apoplexy.base.service.intf.SystemVersionService;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统版本信息服务实现类
 * Created by wunder on 16/9/4 13:58.
 */
@Service("systemVersionService")
@ServiceTrace
public class SystemVersionServiceImpl implements SystemVersionService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(SystemVersionInfoDmo systemVersionInfoDmo) {

        int i = commonDao.insert(systemVersionInfoDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(SystemVersionInfoDmo systemVersionInfoDmo) {

        int i = commonDao.update(systemVersionInfoDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(SystemVersionInfoDmo systemVersionInfoDmo) {

        int i = commonDao.delete(systemVersionInfoDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public SystemVersionInfoDmo selectOne(SystemVersionInfoDmo systemVersionInfoDmo) {

        return (SystemVersionInfoDmo)commonDao.selectOne(systemVersionInfoDmo);
    }
}
