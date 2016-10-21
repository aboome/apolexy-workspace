package com.yh.apoplexy.doctor.cases.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * NIHSS详情服务接口类
 * Created by wunder on 16/9/9 11:37.
 */
public interface NihssDetailService {

    /**
     * 新增NIHSS详情信息
     * @param nihssDetailDmo
     * @return
     */
    public Result insert(NihssDetailDmo nihssDetailDmo);

    /**
     * 批量插入转诊病例NIHSS详情信息
     * @param referralCaseDetailDto
     * @return
     */
    public Result batchInsertReferral(ReferralCaseDetailDto referralCaseDetailDto);

    /**
     * 批量插入AST病例NIHSS详情信息
     * @param astCaseDetailDto
     * @return
     */
    public Result batchInsertAst(AstCaseDetailDto astCaseDetailDto);

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
