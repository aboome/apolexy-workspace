package com.yh.apoplexy.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.BannerInfoDmo;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 推荐位服务接口类
 * Created by wunder on 16/9/4 09:44.
 */
public interface BannerInfoService {

    /**
     * 查找推荐位信息
     * @param bannerInfoDmo
     * @return
     */
    public BannerInfoDmo selectOne(BannerInfoDmo bannerInfoDmo);

    /**
     * 新增推荐位信息
     * @param bannerInfoDmo
     * @return
     */
    public Result insert(BannerInfoDmo bannerInfoDmo);

    /**
     * 删除推荐位信息
     * @param bannerInfoDmo
     * @return
     */
    public Result delete(BannerInfoDmo bannerInfoDmo);

    /**
     * 更新推荐位信息
     * @param bannerInfoDmo
     * @return
     */
    public Result update(BannerInfoDmo bannerInfoDmo);

    /**
     * 查询推荐位信息列表
     * @param bannerInfoDmo
     * @return
     */
    public List<BannerInfoDmo> selectList(BannerInfoDmo bannerInfoDmo);

}
