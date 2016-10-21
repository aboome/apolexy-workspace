package com.yh.apoplexy.admin.doctor.cases.referral.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yh.apoplexy.admin.doctor.cases.referral.service.intf.AdminDoctorReferralService;
import com.yh.apoplexy.admin.doctor.cases.referral.service.intf.AdminReferralResourceService;
import com.yh.apoplexy.admin.doctor.cases.service.intf.AdminNihssDetailService;
import com.yh.apoplexy.admin.doctor.member.service.intf.AdminDoctorMemberService;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.referral.AdminQueryReferralCaseDto;
import com.yh.apoplexy.assist.dto.admin.doctor.cases.referral.AdminReferralCaseDetailDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dto.admin.doctor.CasesReferralDto;
import com.yh.apoplexy.assist.dto.admin.doctor.CasesReferralRecvDto;
import com.yh.apoplexy.assist.dto.admin.doctor.EvalueDto;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

/**
 * 病例转诊管理
 *
 * @author zhangbiao
 */


@Service("adminDoctorReferralService")
@ServiceTrace
public class AdminDoctorReferralServiceImpl implements AdminDoctorReferralService {

    @Autowired
    private CommonDao commonDao;
    @Autowired
    private AdminDoctorMemberService adminDoctorMemberService;

    @Autowired
    private AdminReferralResourceService adminReferralResourceService;

    @Autowired
    private AdminNihssDetailService adminNihssDetailService;

    @Override
    public ReferralCaseDmo find(ReferralCaseDmo con) {

        return (ReferralCaseDmo)commonDao.selectOne(con);

    }

    @Override
    public List<CasesReferralDto> queryCasesReferralList(AdminQueryReferralCaseDto con, Page page) {

        List<String> caseStatus = new ArrayList<>();

        caseStatus.add(Constants.ReferralCaseStatus.NOT_REFERRAL);
        caseStatus.add(Constants.ReferralCaseStatus.REFERRALED);

        con.setCaseStatus(caseStatus);

        return commonDao.selectListByPage("DoctorMapper.queryCasesReferralListCount", "DoctorMapper.queryCasesReferralList", con, page);

    }

    @Override
    public AdminReferralCaseDetailDto queryCasesReferralDetail(ReferralCaseDmo con) {

        AdminReferralCaseDetailDto referralCaseDetailDto = new AdminReferralCaseDetailDto();

        if (null == con.getId()){
            return null;
        }

        //查询病例详情
        ReferralCaseDmo referralCaseDmo = find(con);

        if (null == referralCaseDmo) {
            return null;
        }

        referralCaseDetailDto.setReferralCaseDmo(referralCaseDmo);

        DoctorMemberDmo memberCon = new DoctorMemberDmo();

        memberCon.setId(referralCaseDmo.getUserId());
        memberCon.setStatus(Constants.MemberStatus.NORMAL);
        //查询发布病例详情的医生信息
        DoctorMemberDmo doctorMemberDmo = adminDoctorMemberService.selectOne(memberCon);

        referralCaseDetailDto.setDoctorMemberDmo(doctorMemberDmo);

        ReferralResourceDmo videoCon = new ReferralResourceDmo();

        videoCon.setRecordId(con.getId());
        videoCon.setType(Constants.ResourcesType.VIDEO);
        //查询病例视频信息
        ReferralResourceDmo video = adminReferralResourceService.find(videoCon);

        referralCaseDetailDto.setVideo(video);

        ReferralResourceDmo imageCon = new ReferralResourceDmo();

        imageCon.setRecordId(con.getId());
        imageCon.setType(Constants.ResourcesType.IMAGE);

        //查询病例的图片列表
        List<ReferralResourceDmo> imageList = adminReferralResourceService.selectList(imageCon);

        referralCaseDetailDto.setImageList(imageList);

        NihssDetailDmo nihssDetailCon = new NihssDetailDmo();

        nihssDetailCon.setRecordId(con.getId());
        nihssDetailCon.setType(DoctorConstants.NihssTestSrcType.REFERRAL);

        //查询NIHSS信息列表
        List<NihssDetailDmo> nihssDetailDmoList = adminNihssDetailService.selectList(nihssDetailCon);

        referralCaseDetailDto.setNihssList(nihssDetailDmoList);

        return referralCaseDetailDto;
         
    }
    
    @Override
    public List<CasesReferralRecvDto> queryReceiveDoctorList(ReferralCaseDmo con, Page page) {

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("recordId", con.getId().toString());
        map.put("doctorStatus",Constants.MyRecvStatus.RECVING);
   
        return commonDao.selectListByPage("DoctorMapper.queryReceiveDoctorListCount", "DoctorMapper.queryReceiveDoctorList", map, page);
    }

    @Override
    public EvalueDto queryReferralComment(DiscussCaseDmo con) {

        if (con == null) {
            return null;
        }

        if ((con.getId() == null)) {
            return null;
        }

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("recordId", con.getId().toString());
        return (EvalueDto) commonDao.selectOne("DoctorMapper.queryReferralComment", map);

    }

    @Override
    public Result deleteCasesReferral(ReferralCaseDmo con) {

        Result result = new Result();

        if (con == null) {
            result.fail("", "参数为空,删除失败");
            return result;
        }

        if (con.getId() == null) {
            result.fail("", "参数为空，删除失败");
            return null;
        }
        con.setStatus(Constants.ReferralCaseStatus.CANCEL);

        int i = commonDao.update(con);

        return SqlAssertUtils.updateAssert(i);
     
    }

}
