package com.yh.apoplexy.patient.health.service.intf;

import com.yh.apoplexy.assist.dmo.patient.health.HealthDataHisDmo;
import com.yjh.framework.lang.Result;

/**
 * 患者历史健康数据服务接口类
 * Created by wunder on 16/9/5 18:22.
 */
public interface HealthHisDataService {

    /**
     * 新增患者历史健康数据信息
     * @param healthDataHisDmo
     * @return
     */
    public Result insert(HealthDataHisDmo healthDataHisDmo);

    /**
     * 更新患者历史健康数据信息
     * @param healthDataHisDmo
     * @return
     */
    public Result update(HealthDataHisDmo healthDataHisDmo);

    /**
     * 删除患者历史健康数据信息
     * @param healthDataHisDmo
     * @return
     */
    public Result delete(HealthDataHisDmo healthDataHisDmo);

    /**
     * 查询患者历史健康数据信息
     * @param healthDataHisDmo
     * @return
     */
    public HealthDataHisDmo find(HealthDataHisDmo healthDataHisDmo);
}
