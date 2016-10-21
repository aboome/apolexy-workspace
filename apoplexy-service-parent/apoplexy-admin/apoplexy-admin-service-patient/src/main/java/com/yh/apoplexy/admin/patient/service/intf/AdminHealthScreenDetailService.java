package com.yh.apoplexy.admin.patient.service.intf;

import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;

import java.util.List;

/**
 * Admin端高危筛查详情服务接口类
 * Created by wunder on 16/9/19 16:20.
 */
public interface AdminHealthScreenDetailService {

    /**
     * 查询高危筛查详情列表
     * @param con
     * @return
     */
    public List<PatientScreenDetailDmo> selectList(PatientScreenDetailDmo con);

}
