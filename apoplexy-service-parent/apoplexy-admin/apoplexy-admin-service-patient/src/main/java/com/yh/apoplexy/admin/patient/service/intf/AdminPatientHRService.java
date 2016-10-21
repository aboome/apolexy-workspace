package com.yh.apoplexy.admin.patient.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDmo;
import com.yh.apoplexy.assist.dto.admin.patient.PatientHRDto;
import com.yh.apoplexy.assist.dto.admin.patient.health.PatientHRInputDto;
import com.yh.apoplexy.assist.dto.admin.patient.health.PatientScreenDto;
import com.yjh.framework.page.Page;

/**
 * 查询高危筛查列表
 *
 * @author zhangbiao
 */
public interface AdminPatientHRService {

    /**
     * 查询高危筛查列表
     *
     * @param con
     * @return
     */
    public List<PatientScreenDto> queryHRList(PatientHRInputDto con, Page page);

    /**
     * 查询高危筛查详情,通过高危筛查信息表里的id作为recordId去另外两张表里去找
     *
     * @return
     */
    public PatientHRDto queryHRDetail(PatientScreenDmo con);

}
