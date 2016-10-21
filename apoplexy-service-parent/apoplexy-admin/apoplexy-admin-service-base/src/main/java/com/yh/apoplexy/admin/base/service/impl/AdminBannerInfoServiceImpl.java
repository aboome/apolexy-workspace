package com.yh.apoplexy.admin.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.base.service.intf.AdminBannerInfoService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.BannerInfoDmo;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

@Service("adminBannerInfoService")
@ServiceTrace
public class AdminBannerInfoServiceImpl implements AdminBannerInfoService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Result addBanner(BannerInfoDmo bannerInfoDmo) {

        int i = commonDao.insert(bannerInfoDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result updateBanner(BannerInfoDmo bannerInfoDmo) {
        int i = commonDao.update(bannerInfoDmo);
        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result deleteBanner(BannerInfoDmo bannerInfoDmo) {

        bannerInfoDmo.setStatus(Constants.BannerStatus.DELETE);

        int i = commonDao.update(bannerInfoDmo);
        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public List<BannerInfoDmo> selectList(BannerInfoDmo bannerInfoDmo) {

        return commonDao.selectList("AdminBannerInfoMapper.queryBannerList", bannerInfoDmo);
    }

}
