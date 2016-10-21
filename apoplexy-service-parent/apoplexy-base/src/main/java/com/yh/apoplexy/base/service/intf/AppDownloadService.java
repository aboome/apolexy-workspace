package com.yh.apoplexy.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.AppDownloadDmo;
import com.yjh.framework.lang.Result;

/**
 * App下载量统计服务接口类
 * Created by wunder on 16/9/7 00:34.
 */
public interface AppDownloadService {

    /**
     * 新增App下载信息
     * @param appDownloadDmo
     * @return
     */
    public Result insert(AppDownloadDmo appDownloadDmo);

    /**
     * 查找App下载信息
     * @param con
     * @return
     */
    public AppDownloadDmo selectOne(AppDownloadDmo con);

    /**
     * 上报APP下载信息
     * @param appDownloadDmo
     * @return
     */
    public Result report(AppDownloadDmo appDownloadDmo);

}
