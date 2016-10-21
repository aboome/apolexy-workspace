package com.yh.apoplexy.admin.statis.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dto.common.StatisReferralInputDto;
import com.yh.apoplexy.assist.dto.statistics.ReferralStatisticsDto;

/**
 * 
 * 病例转诊统计列表
 * @author zhangbiao
 *
 */
public interface ReferralStatisService {
	 
    /**
     * 根据医院等级统计病例转诊发单
     * @return
     */
    public  List<ReferralStatisticsDto>  querySendReferralList(ReferralCaseDmo referralCaseDmo);
   
    /**
     * 根据转诊患者类型统计病例转诊发单
     * @param referralCaseDmo
     * @return
     */
    public  List<ReferralStatisticsDto>  queryReceiveReferralListCount(ReferralCaseDmo referralCaseDmo);
    
    
    /***
     * 根据医院等级统计接单病例转诊
     * @param referralCaseDmo
     * @return
     */
    public  List<ReferralStatisticsDto> queryReceiveReferralList(ReferralCaseDmo referralCaseDmo);
    
    /**
     * 根据患者类型统计接单病例转诊
     * @param referralCaseDmo
     * @return
     */
    public List<ReferralStatisticsDto> queryReceivePatientList(ReferralCaseDmo referralCaseDmo);
    
    /**
     * 根据月份统计发单病例转诊
     * @param referralCaseDmo
     * @return
     */
    public List<ReferralStatisticsDto> queryReceiveTiemList(ReferralCaseDmo referralCaseDmo);
    /**
     * 按月份统计接单病例转诊
     * @param referralCaseDmo
     * @return
     */
    public List<ReferralStatisticsDto> queryReferralTiemList(ReferralCaseDmo referralCaseDmo);
}
