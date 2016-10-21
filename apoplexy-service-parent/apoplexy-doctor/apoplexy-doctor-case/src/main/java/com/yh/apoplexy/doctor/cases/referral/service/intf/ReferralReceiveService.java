package com.yh.apoplexy.doctor.cases.referral.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralReceiveDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.QueryIntentDoctorDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralIntentDoctorDto;
import com.yh.apoplexy.doctor.cases.referral.dto.ConfirmReferralDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 意向接诊服务接口类
 * Created by wunder on 16/9/9 13:57.
 */
public interface ReferralReceiveService {

    /**
     * 新增意向接诊信息
     *
     * @param referralReceiveDmo
     * @return
     */
    public Result insert(ReferralReceiveDmo referralReceiveDmo);

    /**
     * 更新意向接诊信息
     *
     * @param referralReceiveDmo
     * @return
     */
    public Result update(ReferralReceiveDmo referralReceiveDmo);

    /**
     * 删除意向接诊信息
     *
     * @param referralReceiveDmo
     * @return
     */
    public Result delete(ReferralReceiveDmo referralReceiveDmo);

    /**
     * 查找意向接诊信息
     *
     * @param con
     * @return
     */
    public ReferralReceiveDmo selectOne(ReferralReceiveDmo con);

    /**
     * 查询意向接诊信息
     *
     * @param con
     * @return
     */
    public List<ReferralReceiveDmo> selectList(ReferralReceiveDmo con);

    /**
     * 查询意向接诊医生列表
     *
     * @param con
     * @return
     */
    public List<ReferralIntentDoctorDto> queryIntentDoctorList(QueryIntentDoctorDto con);

    /**
     * 提交接诊意向
     *
     * @param referralReceiveDmo
     * @return
     */
    public Result submitReferralReceive(ReferralReceiveDmo referralReceiveDmo);

    /**
     * 确认接诊
     *
     * @param confirmReferralDto
     * @return
     */
    public Result confirmReferral(ConfirmReferralDto confirmReferralDto);

    /**
     * 取消意向接诊
     *
     * @param recordId
     * @param userId
     * @return
     */
    public Result cancelReceive(Long recordId, Long userId);

    /**
     * 是否已接诊
     *
     * @param recordId
     * @param userId
     * @return
     */
    public Result hasReceived(Long recordId, Long userId);

}
