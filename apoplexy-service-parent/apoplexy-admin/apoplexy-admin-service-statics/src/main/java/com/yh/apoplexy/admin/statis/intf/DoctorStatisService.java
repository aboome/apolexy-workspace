package com.yh.apoplexy.admin.statis.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.statistics.DoctorStatisticsDto;
import com.yjh.framework.page.Page;

 

/**
 * 
 * 医生数据统计管理
 * @author zhangbiao
 *
 */
public interface DoctorStatisService {
	 
    /**
     * 查询医生的数目
     * @param con
     * @return
     */
    public List<DoctorStatisticsDto> queryDoctorCount(DoctorMemberDmo doctorMemberDmo);
    
    
}
