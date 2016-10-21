package com.yh.apoplexy.admin.statis.intf;

import com.yh.apoplexy.assist.dmo.common.AppDownloadDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.assist.dto.statistics.DownloadStatisticInfoDto;
import com.yh.apoplexy.assist.dto.statistics.PatientRegisterStatisticsInfoDto;
import com.yh.apoplexy.assist.dto.statistics.QueryDoctorRegisterStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.QueryDownloadStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.QueryPatientRegisterStatisticsDto;
import com.yh.apoplexy.assist.dto.statistics.ReferralStatisticsInfoDto;

import java.util.List;

public interface DownloadStatisticsService {



    /***
     * 医生端的APP的下载量
     * @param downloadStatisticsDto
     * @return
     */
    public List<DownloadStatisticInfoDto> queryDownloadTestCount(QueryDownloadStatisticsDto downloadStatisticsDto);
    
    
    /***
     * 医生端注册统计
     * @param downloadStatisticsDto
     * @return
     */
    public List<ReferralStatisticsInfoDto> queryDoctorRegisterCount(QueryDoctorRegisterStatisticsDto doctorRegisterStatisticsDto);
    
    /***
     * 患者端注册统计
     * @param doctorRegisterStatisticsDto
     * @return
     */
    public List<PatientRegisterStatisticsInfoDto> queryPatientRegisterCount(QueryPatientRegisterStatisticsDto patientRegisterStatisticsDto);
    
}
