package com.yh.apoplexy.admin.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.LandingPageDmo;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * Created by wunder on 16/9/22 20:23.
 */
public interface AdminLandingPageService {

    /**
     * 查询开机动画列表
     */
    public List<LandingPageDmo> selectList(LandingPageDmo landingPageDmo);

    /**
     * 查询开机动画详情
     * @param landingPageDmo
     * @return
     */
    public LandingPageDmo selectOne(LandingPageDmo landingPageDmo);

    /**
     * 新增开机动画
     * @param landingPageDmo
     * @return
     */
    public Result add(LandingPageDmo landingPageDmo);

    /**
     * 修改开机动画
     * @param landingPageDmo
     * @return
     */
    public Result update(LandingPageDmo landingPageDmo);


    /**
     * 删除开机动画
     * @param landingPageDmo
     * @return
     */
    public Result deleteLandingPage(LandingPageDmo landingPageDmo);
}
