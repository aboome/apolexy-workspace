package com.yh.apoplexy.admin.doctor.cases.referral.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.CasesReferralDto;
import com.yh.apoplexy.assist.dto.admin.doctor.CasesReferralRecvDto;
import com.yh.apoplexy.assist.dto.admin.doctor.EvalueDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.referral.AdminQueryReferralCaseDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.referral.AdminReferralCaseDetailDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

public interface AdminDoctorReferralService {

    /**
     * 查找转诊病例信息
     * @param con
     * @return
     */
    public ReferralCaseDmo find(ReferralCaseDmo con);

    /**
     * 查询病例转诊列表
     */
    public List<CasesReferralDto> queryCasesReferralList(AdminQueryReferralCaseDto con, Page page);

    /**
     * 查询病例转诊详情
     *
     * @param con
     * @return
     */
    public AdminReferralCaseDetailDto queryCasesReferralDetail(ReferralCaseDmo con);

    /**
     * 查询接诊医生信息列表，对这个病例有意向的医生全部查出来,数据库里有一个意向病例接诊信息表,通过一个转诊ID就可以全部查出来
     *
     * @return
     */
    public List<CasesReferralRecvDto> queryReceiveDoctorList(ReferralCaseDmo con, Page page);

    /**
     * 查询接诊评价信息,对应的是数据库中的接口信息评价表，要根据userId和recordId来查，用病例讨论信息表中的id和userId
     *
     * @return
     */
    public EvalueDto queryReferralComment(DiscussCaseDmo con);

    /**
     * 删除病例的转诊,根据病例转诊id删除
     *
     * @param con
     * @return
     */
    public Result deleteCasesReferral(ReferralCaseDmo con);
}
