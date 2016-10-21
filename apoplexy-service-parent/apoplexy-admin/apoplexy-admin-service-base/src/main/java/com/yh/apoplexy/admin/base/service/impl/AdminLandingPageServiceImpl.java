package com.yh.apoplexy.admin.base.service.impl;

import java.util.List;

import com.yh.apoplexy.admin.base.service.intf.AdminLandingPageService;
import com.yh.apoplexy.common.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.LandingPageDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

@Service("adminLandingPageService")
@ServiceTrace
public class AdminLandingPageServiceImpl implements AdminLandingPageService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public LandingPageDmo selectOne(LandingPageDmo landingPageDmo) {

        return (LandingPageDmo) commonDao.selectOne(landingPageDmo);
    }

    @Override
    public Result add(LandingPageDmo landingPageDmo) {

        int i = commonDao.insert(landingPageDmo);
        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(LandingPageDmo landingPageDmo) {
        int i = commonDao.update(landingPageDmo);
        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result deleteLandingPage(LandingPageDmo landingPageDmo) {

        landingPageDmo.setStatus(Constants.StartPictureStatus.DELETE);

        return update(landingPageDmo);
    }

    @Override
    public List<LandingPageDmo> selectList(LandingPageDmo landingPageDmo) {

        return commonDao.selectList(landingPageDmo);
    }

}
