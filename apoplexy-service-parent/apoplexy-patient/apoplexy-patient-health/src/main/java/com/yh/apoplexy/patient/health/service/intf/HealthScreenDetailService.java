package com.yh.apoplexy.patient.health.service.intf;

import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yh.apoplexy.assist.dto.patient.health.HealthScreenDetailDto;
import com.yjh.framework.lang.Result;


/**
 * 高危筛选详情服务接口类
 * Created by wunder on 16/9/5 11:25.
 */
public interface HealthScreenDetailService {

    /**
     * 新增高危筛查详情信息
     * @param patientScreenDetailDmo
     * @return
     */
    public Result insert(PatientScreenDetailDmo patientScreenDetailDmo);

    /**
     * 更新高危筛查详情信息
     * @param patientScreenDetailDmo
     * @return
     */
    public Result update(PatientScreenDetailDmo patientScreenDetailDmo);

    /**
     * 删除高危筛选详情信息
     * @param patientScreenDetailDmo
     * @return
     */
    public Result delete(PatientScreenDetailDmo patientScreenDetailDmo);

    /**
     * 查找高危筛查详情信息
     * @param patientScreenDetailDmo
     * @return
     */
    public PatientScreenDetailDmo selectOne(PatientScreenDetailDmo patientScreenDetailDmo);

    /**
     * 批量插入高危筛选详情列表
     * @param healthScreenDetailDto
     * @return
     */
    public Result batchInsert(HealthScreenDetailDto healthScreenDetailDto);
}
