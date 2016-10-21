package com.yh.apoplexy.patient.health.service.intf;

import com.yh.apoplexy.assist.dmo.patient.health.HealthDataDmo;
import com.yjh.framework.lang.Result;

import java.util.Date;
import java.util.List;

/**
 * 患者健康数据服务接口类
 * Created by wunder on 16/9/5 18:21.
 */
public interface HealthDataService {

    /**
     * 新增患者健康数据信息
     * @param healthDataDmo
     * @return
     */
    public Result insert(HealthDataDmo healthDataDmo);

    /**
     * 更新患者健康数据信息
     * @param healthDataDmo
     * @return
     */
    public Result update(HealthDataDmo healthDataDmo);

    /**
     * 删除患者健康数据信息
     * @param healthDataDmo
     * @return
     */
    public Result delete(HealthDataDmo healthDataDmo);

    /**
     * 查询患者健康数据信息
     * @param healthDataDmo
     * @return
     */
    public HealthDataDmo find(HealthDataDmo healthDataDmo);

    /**
     * 提交健康数据信息
     * @param healthDataDmo
     * @return
     */
    public Result submitHealthData(HealthDataDmo healthDataDmo);

    /**
     * 根据日期查询患者健康数据
     * @param userId
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<HealthDataDmo> selectListByDate(Long userId, Date beginDate, Date endDate);

    /**
     * 查找今日体征数据
     * @param healthDataDmo
     * @return
     */
    public HealthDataDmo findTodayHealthData(HealthDataDmo healthDataDmo);
}
