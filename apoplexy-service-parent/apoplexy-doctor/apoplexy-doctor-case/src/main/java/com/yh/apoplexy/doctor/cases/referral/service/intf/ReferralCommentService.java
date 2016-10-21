package com.yh.apoplexy.doctor.cases.referral.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCommentDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.CalStarLevelDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 转诊评价服务接口类
 * Created by wunder on 16/9/10 14:07.
 */
public interface ReferralCommentService {

    /**
     * 新增转诊评价信息
     *
     * @param referralCommentDmo
     * @return
     */
    public Result insert(ReferralCommentDmo referralCommentDmo);

    /**
     * 更新转诊评价信息
     *
     * @param referralCommentDmo
     * @return
     */
    public Result update(ReferralCommentDmo referralCommentDmo);

    /**
     * 删除转诊评价信息
     *
     * @param referralCommentDmo
     * @return
     */
    public Result delete(ReferralCommentDmo referralCommentDmo);

    /**
     * 查找转诊评价信息
     *
     * @param con
     * @return
     */
    public ReferralCommentDmo selectOne(ReferralCommentDmo con);

    /**
     * 查询转诊评价信息
     *
     * @param con
     * @return
     */
    public List<ReferralCommentDmo> selectList(ReferralCommentDmo con);

    /**
     * 提交转诊评价信息
     *
     * @param con
     * @return
     */
    public Result submitReferralComment(ReferralCommentDmo con);

    /**
     * 查询医生星级信息
     *
     * @param con
     * @return
     */
    public List<CalStarLevelDto> queryScoreInfo(ReferralCommentDmo con);

    /**
     * 是否已评价
     *
     * @param recordId
     * @param userId
     * @return
     */
    public Result hasScored(Long recordId, Long userId);
}
