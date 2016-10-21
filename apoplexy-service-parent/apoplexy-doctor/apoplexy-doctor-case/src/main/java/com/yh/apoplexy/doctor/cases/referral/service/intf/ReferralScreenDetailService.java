package com.yh.apoplexy.doctor.cases.referral.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralScreenDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 转诊筛查详情服务接口类
 * Created by wunder on 16/9/5 09:54.
 */
public interface ReferralScreenDetailService {

    /**
     * 新增转诊筛查详情信息
     * @param referralScreenDetailDmo
     * @return
     */
    public Result insert(ReferralScreenDetailDmo referralScreenDetailDmo);

    /**
     * 更新转诊筛查详情信息
     * @param referralScreenDetailDmo
     * @return
     */
    public Result update(ReferralScreenDetailDmo referralScreenDetailDmo);

    /**
     * 删除转诊筛选详情信息
     * @param referralScreenDetailDmo
     * @return
     */
    public Result delete(ReferralScreenDetailDmo referralScreenDetailDmo);

    /**
     * 查找转诊筛查详情信息
     * @param referralScreenDetailDmo
     * @return
     */
    public ReferralScreenDetailDmo selectOne(ReferralScreenDetailDmo referralScreenDetailDmo);

    /**
     * 查找转诊筛查列表信息
     * @param referralScreenDetailDmo
     * @return
     */
    public List<ReferralScreenDetailDmo> selectList(ReferralScreenDetailDmo referralScreenDetailDmo);

    /**
     * 批量插入转诊筛选详情列表
     * @param referralCaseDetailDto
     * @return
     */
    public Result batchInsert(ReferralCaseDetailDto referralCaseDetailDto);
}
