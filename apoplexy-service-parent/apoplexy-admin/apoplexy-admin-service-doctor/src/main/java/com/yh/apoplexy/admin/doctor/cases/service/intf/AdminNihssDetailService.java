package com.yh.apoplexy.admin.doctor.cases.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * NIHSS详情服务接口类
 * Created by wunder on 16/9/9 11:37.
 */
public interface AdminNihssDetailService {

    /**
     * 查找NIHSS详情信息
     * @param con
     * @return
     */
    public NihssDetailDmo find(NihssDetailDmo con);

    /**
     * 查询NIHSS详情列表
     * @param con
     * @return
     */
    public List<NihssDetailDmo> selectList(NihssDetailDmo con);

}
