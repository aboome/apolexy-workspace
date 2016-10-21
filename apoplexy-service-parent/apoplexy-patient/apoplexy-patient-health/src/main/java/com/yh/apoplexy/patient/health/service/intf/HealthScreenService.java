package com.yh.apoplexy.patient.health.service.intf;

import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDmo;
import com.yh.apoplexy.assist.dto.patient.health.SubmitHealthScreenDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 高危筛查服务接口类
 * Created by wunder on 16/9/5 09:54.
 */
public interface HealthScreenService {

    /**
     * 新增高危筛查信息
     * @param patientScreenDmo
     * @return
     */
    public Result insert(PatientScreenDmo patientScreenDmo);

    /**
     * 更新高危筛查信息
     * @param patientScreenDmo
     * @return
     */
    public Result update(PatientScreenDmo patientScreenDmo);

    /**
     * 删除高危筛选信息
     * @param patientScreenDmo
     * @return
     */
    public Result delete(PatientScreenDmo patientScreenDmo);

    /**
     * 查找高危筛查信息
     * @param patientScreenDmo
     * @return
     */
    public PatientScreenDmo selectOne(PatientScreenDmo patientScreenDmo);

    /**
     * 患者提交高危筛选信息
     * @param submitHealthScreenDto
     * @return
     */
    public Result submitHealthScreenInfo(SubmitHealthScreenDto submitHealthScreenDto);

}
