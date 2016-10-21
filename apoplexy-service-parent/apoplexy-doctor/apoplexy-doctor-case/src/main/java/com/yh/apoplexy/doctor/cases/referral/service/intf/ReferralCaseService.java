package com.yh.apoplexy.doctor.cases.referral.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.QueryReferralCaseDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseInfoDto;
import com.yh.apoplexy.doctor.cases.referral.result.ReferralCasePermissionResult;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * 转诊病例服务接口类
 * Created by wunder on 16/9/8 10:07.
 */
public interface ReferralCaseService {

    /**
     * 新增转诊病例信息
     * @param referralCaseDmo
     * @return
     */
    public Result insert(ReferralCaseDmo referralCaseDmo);

    /**
     * 更新转诊病例信息
     * @param referralCaseDmo
     * @return
     */
    public Result update(ReferralCaseDmo referralCaseDmo);

    /**
     * 删除转诊病例信息
     * @param referralCaseDmo
     * @return
     */
    public Result delete(ReferralCaseDmo referralCaseDmo);

    /**
     * 查找转诊病例信息
     * @param con
     * @return
     */
    public ReferralCaseDmo find(ReferralCaseDmo con);

    /**
     * 分页查询转诊病例列表
     * @param con
     * @param page
     * @return
     */
    public List<ReferralCaseInfoDto> selectListByPage(QueryReferralCaseDto con, Page page);

    /**
     * 分页查询我申请的转诊病例列表
     * @param con
     * @param page
     * @return
     */
    public List<ReferralCaseInfoDto> queryMyApplyListByPage(QueryReferralCaseDto con, Page page);

    /**
     * 分页查询我的接诊病例列表
     * @param con
     * @param page
     * @return
     */
    public List<ReferralCaseInfoDto> queryMyReceiveListByPage(QueryReferralCaseDto con, Page page);

    /**
     * 分页查询已被接诊病例列表
     * @param con
     * @param page
     * @return
     */
    public List<ReferralCaseInfoDto> queryReceivedListByPage(QueryReferralCaseDto con, Page page);

    /**
     * 新增转诊病例（包括图片和视频）
     * @param referralCaseDetailDto
     * @return
     */
    public Result addCase(ReferralCaseDetailDto referralCaseDetailDto);

    /**
     * 查询转诊病例详情
     * @param con
     * @return
     */
    public ReferralCaseDetailDto findCaseDetail(ReferralCaseDmo con);

    /**
     * 检查医生的病例访问权限
     * @param recordId
     * @param userId
     * @return
     */
    public ReferralCasePermissionResult checkDoctorCasePermission(Long recordId, Long userId);

    /**
     * 增加阅读量
     *
     * @param recordId
     * @return
     */
    public Result increaseReadCount(Long recordId);

    /**
     * 增加意向接诊量
     *
     * @param recordId
     * @return
     */
    public Result increaseReceiveCount(Long recordId);

    /**
     * 减少意向接诊量
     *
     * @param recordId
     * @return
     */
    public Result decreaseReceiveCount(Long recordId);

    /**
     * 删除转诊病例
     * @param recordId
     * @param userId
     * @return
     */
    public Result cancelCase(Long recordId, Long userId);

    /**
     * 统计我的发布申请数量
     * @param userId
     * @return
     */
    public Long countMyReferral(Long userId);

}
