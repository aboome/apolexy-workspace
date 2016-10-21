package com.yh.apoplexy.base.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.IdeaInfoDmo;
import com.yh.apoplexy.base.service.intf.IdeaInfoService;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 意见反馈服务实现类
 * Created by wunder on 16/9/7 00:49.
 */
@Service("ideaInfoService")
@ServiceTrace
public class IdeaInfoServiceImpl implements IdeaInfoService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result insert(IdeaInfoDmo ideaInfoDmo) {

        int i = commonDao.insert(ideaInfoDmo);

        return SqlAssertUtils.insertAssert(i);
    }
}
