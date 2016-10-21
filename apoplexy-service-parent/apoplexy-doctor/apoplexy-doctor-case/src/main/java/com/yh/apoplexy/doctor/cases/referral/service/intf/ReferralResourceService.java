package com.yh.apoplexy.doctor.cases.referral.service.intf;


import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralResourceDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * 转诊病例资源服务接口类
 * Created by wunder on 16/9/9 11:09.
 */
public interface ReferralResourceService {

    /**
     * 新增讨论病例资源信息
     * @param referralResourceDmo
     * @return
     */
    public Result insert(ReferralResourceDmo referralResourceDmo);

    /**
     * 批量插入讨论病例资源信息
     * @param referralCaseDetailDto
     * @return
     */
    public Result batchInsert(ReferralCaseDetailDto referralCaseDetailDto);

    /**
     * 查找讨论病例资源信息
     * @param con
     * @return
     */
    public ReferralResourceDmo find(ReferralResourceDmo con);

    /**
     * 查询讨论病例资源列表
     * @param con
     * @return
     */
    public List<ReferralResourceDmo> selectList(ReferralResourceDmo con);
}
