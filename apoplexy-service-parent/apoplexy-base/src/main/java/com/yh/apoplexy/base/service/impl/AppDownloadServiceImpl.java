package com.yh.apoplexy.base.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.AppDownloadDmo;
import com.yh.apoplexy.base.service.intf.AppDownloadService;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * App下载量统计服务实现类
 * Created by wunder on 16/9/7 00:35.
 */
@Service("appDownloadService")
@ServiceTrace
public class AppDownloadServiceImpl implements AppDownloadService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(AppDownloadDmo appDownloadDmo) {

        int i = commonDao.insert(appDownloadDmo);

        return SqlAssertUtils.insertAssert(i);

    }

    @Override
    public AppDownloadDmo selectOne(AppDownloadDmo con) {
        return (AppDownloadDmo)commonDao.selectOne(con);
    }

    @Override
    public Result report(AppDownloadDmo appDownloadDmo) {

        Result result = new Result();

        AppDownloadDmo con = new AppDownloadDmo();

        con.setIemId(appDownloadDmo.getIemId());
        con.setType(appDownloadDmo.getType());
        con.setClientType(appDownloadDmo.getClientType());

        AppDownloadDmo existDmo = selectOne(con);

        if(null != existDmo){

            return result;

        }

        result = insert(appDownloadDmo);

        return result;
    }

}
