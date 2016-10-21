package com.yh.apoplexy.base.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.BannerInfoDmo;
import com.yh.apoplexy.base.service.intf.BannerInfoService;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐位服务实现类
 * Created by wunder on 16/9/4 09:44.
 */
@Service("bannerInfoService")
@ServiceTrace
public class BannerInfoServiceImpl implements BannerInfoService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public BannerInfoDmo selectOne(BannerInfoDmo bannerInfoDmo) {

        return (BannerInfoDmo)commonDao.selectOne(bannerInfoDmo);
    }

    @Override
    public Result insert(BannerInfoDmo bannerInfoDmo) {

        int i = commonDao.insert(bannerInfoDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result delete(BannerInfoDmo bannerInfoDmo) {

        int i = commonDao.delete(bannerInfoDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public Result update(BannerInfoDmo bannerInfoDmo) {

        int i = commonDao.update(bannerInfoDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public List<BannerInfoDmo> selectList(BannerInfoDmo bannerInfoDmo) {

        return commonDao.selectList("BannerInfoMapper.queryBannerList",bannerInfoDmo);
    }

}

