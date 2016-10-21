package com.yh.apoplexy.admin.statis.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dto.statistics.HospitalStatisticsDto;
import com.yjh.framework.page.Page;

/**
 * 医院 数据统计管理
 *
 * @author zhangbiao
 */
public interface HospitalStatisticsService {

    /**
     * 查询医院的个数，市级，县级，社区级
     *
     * @return
     */
    public List<HospitalStatisticsDto> queryHospitalCount(HospitalInfoDmo con);


} 
