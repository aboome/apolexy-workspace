package com.yh.apoplexy.doctor.cases.referral.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralReceiveDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralScreenDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorScoreDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.*;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.constants.ScoreConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.referral.result.ReferralCasePermissionResult;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCaseService;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralReceiveService;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralResourceService;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralScreenDetailService;
import com.yh.apoplexy.doctor.cases.service.intf.NihssDetailService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorScoreService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 转诊病例服务实现类
 * Created by wunder on 16/9/8 11:07.
 */
@Service("referralCaseService")
@ServiceTrace
public class ReferralCaseServiceImpl implements ReferralCaseService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private ReferralResourceService referralResourceService;

    @Autowired
    private NihssDetailService nihssDetailService;

    @Autowired
    private ReferralReceiveService referralReceiveService;

    @Autowired
    private DoctorScoreService doctorScoreService;

    @Autowired
    private ReferralScreenDetailService referralScreenDetailService;

    @Override
    public Result insert(ReferralCaseDmo referralCaseDmo) {

        int i = commonDao.insert(referralCaseDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(ReferralCaseDmo referralCaseDmo) {

        int i = commonDao.update(referralCaseDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(ReferralCaseDmo referralCaseDmo) {

        int i =commonDao.delete(referralCaseDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public ReferralCaseDmo find(ReferralCaseDmo con) {

        return (ReferralCaseDmo)commonDao.selectOne(con);

    }

    @Override
    public List<ReferralCaseInfoDto> selectListByPage(QueryReferralCaseDto con, Page page) {

        con.setVideoType(Constants.ResourcesType.VIDEO);
        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setImageType(Constants.ResourcesType.IMAGE);
        con.setNihssType(DoctorConstants.NihssTestSrcType.REFERRAL);
        con.setAllRange(Constants.Range.ALL);

        return commonDao.selectListByPage("ReferralCaseMapper.countCase","ReferralCaseMapper.queryCase",con,page);

    }

    @Override
    public List<ReferralCaseInfoDto> queryMyApplyListByPage(QueryReferralCaseDto con, Page page) {

        con.setVideoType(Constants.ResourcesType.VIDEO);
        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setImageType(Constants.ResourcesType.IMAGE);
        con.setNihssType(DoctorConstants.NihssTestSrcType.REFERRAL);

        return commonDao.selectListByPage("ReferralCaseMapper.countMyApplyCase","ReferralCaseMapper.queryMyApplyCase",con,page);

    }

    @Override
    public List<ReferralCaseInfoDto> queryMyReceiveListByPage(QueryReferralCaseDto con, Page page) {

        con.setVideoType(Constants.ResourcesType.VIDEO);
        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setImageType(Constants.ResourcesType.IMAGE);
        con.setNihssType(DoctorConstants.NihssTestSrcType.REFERRAL);

        return commonDao.selectListByPage("ReferralCaseMapper.countMyReceiveCase","ReferralCaseMapper.queryMyReceiveCase",con,page);

    }

    @Override
    public List<ReferralCaseInfoDto> queryReceivedListByPage(QueryReferralCaseDto con, Page page) {

        con.setVideoType(Constants.ResourcesType.VIDEO);
        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setImageType(Constants.ResourcesType.IMAGE);
        con.setNihssType(DoctorConstants.NihssTestSrcType.REFERRAL);

        return commonDao.selectListByPage("ReferralCaseMapper.countReceivedCase","ReferralCaseMapper.queryReceivedCase",con,page);

    }


    @Override
    @Transactional
    public Result addCase(ReferralCaseDetailDto referralCaseDetailDto) {

        Result result = new Result();

        ReferralCaseDmo referralCaseDmo = referralCaseDetailDto.getReferralCaseDmo();

        referralCaseDmo.setCreateTime(DateUtil.getDate());
        referralCaseDmo.setLastUpdateTime(DateUtil.getDate());
        referralCaseDmo.setStatus(Constants.ReferralCaseStatus.NOT_REFERRAL);
        referralCaseDmo.setReadCount(0L);
        referralCaseDmo.setReceiveCount(0L);
        //新增转诊病例信息
        result = insert(referralCaseDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }
        //查询最新插入的转诊病例信息
        ReferralCaseDmo latestReferralCaseDmo = find(referralCaseDmo);

        referralCaseDetailDto.setReferralCaseDmo(latestReferralCaseDmo);
        //批量插入转诊病例图片信息
        result = referralResourceService.batchInsert(referralCaseDetailDto);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        result = nihssDetailService.batchInsertReferral(referralCaseDetailDto);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        result = referralScreenDetailService.batchInsert(referralCaseDetailDto);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        //增加积分
        if(checkFirstCaseToday(latestReferralCaseDmo.getUserId())){

            DoctorScoreDetailDmo doctorScoreDetailDmo = new DoctorScoreDetailDmo();

            doctorScoreDetailDmo.setUserId(latestReferralCaseDmo.getUserId());
            doctorScoreDetailDmo.setUserName(referralCaseDetailDto.getDoctorMemberDmo().getDoctorName());
            doctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.POST_REFERRAL);
            doctorScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_DOCTOR_REFERRAL_CASE));
            doctorScoreDetailDmo.setScore(10L);
            doctorScoreDetailDmo.setTime(DateUtil.getDate());

            result = doctorScoreService.addScoreEvent(doctorScoreDetailDmo);

            if (!result.isSuccess()) {

                throw new AppException(result);
            }

        }

        return result;

    }

    @Override
    public ReferralCaseDetailDto findCaseDetail(ReferralCaseDmo con) {

        ReferralCaseDetailDto referralCaseDetailDto = new ReferralCaseDetailDto();

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
        DoctorMemberDmo doctorMemberDmo = doctorMemberService.selectOne(memberCon);

        referralCaseDetailDto.setDoctorMemberDmo(doctorMemberDmo);

        ReferralResourceDmo videoCon = new ReferralResourceDmo();

        videoCon.setRecordId(con.getId());
        videoCon.setType(Constants.ResourcesType.VIDEO);
        //查询病例视频信息
        ReferralResourceDmo video = referralResourceService.find(videoCon);

        referralCaseDetailDto.setVideo(video);

        ReferralResourceDmo imageCon = new ReferralResourceDmo();

        imageCon.setRecordId(con.getId());
        imageCon.setType(Constants.ResourcesType.IMAGE);

        //查询病例的图片列表
        List<ReferralResourceDmo> imageList = referralResourceService.selectList(imageCon);

        referralCaseDetailDto.setImageList(imageList);

        NihssDetailDmo nihssDetailCon = new NihssDetailDmo();

        nihssDetailCon.setRecordId(con.getId());
        nihssDetailCon.setType(DoctorConstants.NihssTestSrcType.REFERRAL);

        //查询NIHSS信息列表
        List<NihssDetailDmo> nihssDetailDmoList = nihssDetailService.selectList(nihssDetailCon);

        referralCaseDetailDto.setNihssList(nihssDetailDmoList);

        //增加阅读量
        increaseReadCount(referralCaseDmo.getId());

        //已转诊的不需要意向接诊医生列表
        if (Constants.ReferralCaseStatus.REFERRALED.equals(referralCaseDmo.getStatus())){

            return referralCaseDetailDto;

        }

        QueryIntentDoctorDto queryIntentDoctorDto = new QueryIntentDoctorDto();

        queryIntentDoctorDto.setRecordId(con.getId());
        List<String> receiveStatus = new ArrayList<>();
        receiveStatus.add(Constants.MyRecvStatus.RECVING);
        queryIntentDoctorDto.setReceiveStatus(receiveStatus);

        //查询意向接诊医生列表
        List<ReferralIntentDoctorDto> referralIntentDoctorDtoList = referralReceiveService.queryIntentDoctorList(queryIntentDoctorDto);

        referralCaseDetailDto.setReceiveList(referralIntentDoctorDtoList);

        ReferralScreenDetailDmo referralScreenDetailCon = new ReferralScreenDetailDmo();

        referralScreenDetailCon.setRecordId(con.getId());

        List<ReferralScreenDetailDmo> referralScreenDetailList = referralScreenDetailService.selectList(referralScreenDetailCon);

        referralCaseDetailDto.setScreenList(referralScreenDetailList);

        return referralCaseDetailDto;

    }

    @Override
    public ReferralCasePermissionResult checkDoctorCasePermission(Long recordId, Long userId) {

        ReferralCasePermissionResult result = new ReferralCasePermissionResult();

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(userId);
        doctorMemberCon.setStatus(Constants.MemberStatus.NORMAL);
        //查询访问医生信息
        DoctorMemberDto doctorMemberDto = doctorMemberService.findDoctorBaseInfo(doctorMemberCon);
        //医生信息不存在
        if (null == doctorMemberDto){

            result.fail("CDC-0000","医生信息不存在");
            return result;

        }

        ReferralCaseDmo referralCaseCon = new ReferralCaseDmo();

        referralCaseCon.setId(recordId);
        //查询病例信息
        ReferralCaseDmo referralCaseDmo = find(referralCaseCon);
        //病例信息不存在
        if (null == referralCaseDmo||Constants.ReferralCaseStatus.CANCEL.equals(referralCaseDmo.getStatus())){

            result.fail("CDC-0001","病例信息不存在");
            return result;

        }
        //病例属于"1+1+1"讨论范围，且访问医生不属于该范围
        if (Constants.Range.ONE_BY_ONE_BY_ONE.equals(referralCaseDmo.getCaseRange())){
            //医生所属医院或者所属医院的顶级医院都不属于讨论范围
            if (!doctorMemberDto.getHospitalId().equals(referralCaseDmo.getRootHospitalId())&&!doctorMemberDto.getRootHospitalId().equals(referralCaseDmo.getRootHospitalId())){

                result.fail("CDC-0002","医生没有病例访问权限");
                return result;
            }

        }

        result.setPostDoctorId(referralCaseDmo.getUserId());
        result.setReferralStatus(referralCaseDmo.getStatus());
        result.setDoctorName(doctorMemberDto.getDoctorName());
        result.setTitle(referralCaseDmo.getTitle());

        return result;
    }

    @Override
    public Result increaseReadCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("ReferralCaseMapper.increaseReadCount",conMap );

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public Result increaseReceiveCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("ReferralCaseMapper.increaseReceiveCount",conMap );

        return SqlAssertUtils.updateAssert(i);

    }

    @Override
    public Result decreaseReceiveCount(Long recordId) {

        HashMap<String, String> conMap = new HashMap<>();

        conMap.put("id",recordId.toString());

        int i = commonDao.update("ReferralCaseMapper.decreaseReceiveCount",conMap );

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result cancelCase(Long recordId, Long userId) {

        Result result = new Result();

        ReferralCaseDmo referralCaseCon = new ReferralCaseDmo();

        referralCaseCon.setId(recordId);
        referralCaseCon.setStatus(Constants.ReferralCaseStatus.NOT_REFERRAL);
        //查询病例信息
        ReferralCaseDmo referralCaseDmo = find(referralCaseCon);
        //病例信息不存在
        if (null == referralCaseDmo){

            result.fail("DLC-0000","病例信息不存在");
            return result;

        }
        //病例删除用户不是病例发布用户
        if (!referralCaseDmo.getUserId().equals(userId)){

            result.fail("DLC-0001","删除用户不是病例发布用户");
            return result;

        }

        referralCaseDmo.setStatus(Constants.ReferralCaseStatus.CANCEL);

        result = update(referralCaseDmo);

        if (!result.isSuccess()){

            result.fail("DLC-0002","取消转诊失败");
            return result;
        }

        return result;
    }

    @Override
    public Long countMyReferral(Long userId) {

        if (null == userId){
            return null;
        }

        QueryReferralCaseDto con = new QueryReferralCaseDto();

        con.setVideoType(Constants.ResourcesType.VIDEO);
        con.setDoctorStatus(Constants.MemberStatus.NORMAL);
        con.setImageType(Constants.ResourcesType.IMAGE);
        con.setNihssType(DoctorConstants.NihssTestSrcType.REFERRAL);

        con.setUserId(userId);

        List<String> caseStatus = new ArrayList<>();

        caseStatus.add(Constants.ReferralCaseStatus.NOT_REFERRAL);
        caseStatus.add(Constants.ReferralCaseStatus.REFERRALED);

        con.setCaseStatus(caseStatus);

        return commonDao.selectCount("ReferralCaseMapper.countMyApplyCase",con);

    }

    /**
     * 检查是否今日首次发布
     * @param doctorId
     * @return
     */
    private boolean checkFirstCaseToday(Long doctorId){

        QueryReferralCaseDto con = new QueryReferralCaseDto();

        con.setUserId(doctorId);

        List<String> caseStatus = new ArrayList<>();

        caseStatus.add(Constants.ReferralCaseStatus.NOT_REFERRAL);
        caseStatus.add(Constants.ReferralCaseStatus.REFERRALED);

        con.setCaseStatus(caseStatus);

        Long count =  commonDao.selectCount("ReferralCaseMapper.countCaseToday",con);

        return count.equals(1L);

    }
}
