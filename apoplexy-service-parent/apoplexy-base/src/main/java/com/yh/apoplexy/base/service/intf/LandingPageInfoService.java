package com.yh.apoplexy.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.LandingPageDmo;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 开机动画信息服务
 * Created by wunder on 16/9/4 11:08.
 */
public interface LandingPageInfoService {

    /**
     * 查找开机动画信息
     * @param landingPageDmo
     * @return
     */
    public LandingPageDmo selectOne(LandingPageDmo landingPageDmo);

    /**
     * 新增开机动画信息
     * @param landingPageDmo
     * @return
     */
    public Result insert(LandingPageDmo landingPageDmo);

    /**
     * 删除开机动画信息
     * @param landingPageDmo
     * @return
     */
    public Result delete(LandingPageDmo landingPageDmo);

    /**
     * 更新开机动画信息
     * @param landingPageDmo
     * @return
     */
    public Result update(LandingPageDmo landingPageDmo);

    /**
     * 查询开机动画信息列表
     * @param landingPageDmo
     * @return
     */
    public List<LandingPageDmo> selectList(LandingPageDmo landingPageDmo);

}
