package com.yh.apoplexy.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.SystemVersionInfoDmo;
import com.yjh.framework.lang.Result;

/**
 * 系统版本信息服务接口类
 * Created by wunder on 16/9/4 13:55.
 */
public interface SystemVersionService {

    /**
     * 新增系统版本信息
     * @param systemVersionInfoDmo
     * @return
     */
    public Result insert(SystemVersionInfoDmo systemVersionInfoDmo);

    /**
     * 更新系统版本信息
     * @param systemVersionInfoDmo
     * @return
     */
    public Result update(SystemVersionInfoDmo systemVersionInfoDmo);

    /**
     * 删除系统版本信息
     * @param systemVersionInfoDmo
     * @return
     */
    public Result delete(SystemVersionInfoDmo systemVersionInfoDmo);

    /**
     * 查询系统版本信息
     * @param systemVersionInfoDmo
     * @return
     */
    public SystemVersionInfoDmo selectOne(SystemVersionInfoDmo systemVersionInfoDmo);
}
