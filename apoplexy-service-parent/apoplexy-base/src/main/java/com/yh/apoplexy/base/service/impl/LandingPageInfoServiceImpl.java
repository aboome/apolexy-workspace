package com.yh.apoplexy.base.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.LandingPageDmo;
import com.yh.apoplexy.base.service.intf.LandingPageInfoService;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开机动画信息服务实现类
 * Created by wunder on 16/9/4 11:10.
 */
@Service("landingPageInfoService")
@ServiceTrace
public class LandingPageInfoServiceImpl implements LandingPageInfoService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public LandingPageDmo selectOne(LandingPageDmo landingPageDmo) {
        return (LandingPageDmo)commonDao.selectOne(landingPageDmo);
    }

    @Override
    public Result insert(LandingPageDmo landingPageDmo) {

        int i = commonDao.insert(landingPageDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result delete(LandingPageDmo landingPageDmo) {

        int i = commonDao.delete(landingPageDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public Result update(LandingPageDmo landingPageDmo) {

        int i = commonDao.update(landingPageDmo);

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public List<LandingPageDmo> selectList(LandingPageDmo landingPageDmo) {

        return commonDao.selectList("LandingPageInfoMapper.queryLandingPageList",landingPageDmo);

    }
}
