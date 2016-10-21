package com.yh.apoplexy.patient.health.service.intf;

import com.yh.apoplexy.assist.dmo.patient.health.PatientEmergencyDmo;
import com.yjh.framework.lang.Result;

/**
 * 中风急救服务接口类
 * Created by wunder on 16/9/5 22:12.
 */
public interface PatientEmergencyService {

    /**
     * 新增中风急救信息
     * @param patientEmergencyDmo
     * @return
     */
    public Result insert(PatientEmergencyDmo patientEmergencyDmo);

    /**
     * 提交中风急救
     * @param patientEmergencyDmo
     * @return
     */
    public Result submitEmergency(PatientEmergencyDmo patientEmergencyDmo);
}
