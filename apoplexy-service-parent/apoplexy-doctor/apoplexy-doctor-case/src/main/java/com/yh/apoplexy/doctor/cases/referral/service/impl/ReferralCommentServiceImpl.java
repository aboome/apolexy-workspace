package com.yh.apoplexy.doctor.cases.referral.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralReceiveDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMessageDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorScoreDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.CalStarLevelDto;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.constants.MessageConstants;
import com.yh.apoplexy.common.constants.ScoreConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.referral.result.ReferralCasePermissionResult;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCaseService;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCommentService;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralReceiveService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMessageService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorScoreService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * 转诊评价服务实现类
 * Created by wunder on 16/9/10 14:12.
 */
@Service("referralCommentService")
@ServiceTrace
public class ReferralCommentServiceImpl implements ReferralCommentService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private ReferralCaseService referralCaseService;

    @Autowired
    private DoctorScoreService doctorScoreService;

    @Autowired
    private DoctorMessageService doctorMessageService;

    @Autowired
    private ReferralReceiveService referralReceiveService;

    @Override
    public Result insert(ReferralCommentDmo referralCommentDmo) {

        int i = commonDao.insert(referralCommentDmo);

        return SqlAssertUtils.insertAssert(i);

    }

    @Override
    public Result update(ReferralCommentDmo referralCommentDmo) {

        int i = commonDao.update(referralCommentDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(ReferralCommentDmo referralCommentDmo) {

        int i = commonDao.delete(referralCommentDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public ReferralCommentDmo selectOne(ReferralCommentDmo con) {

        return (ReferralCommentDmo) commonDao.selectOne(con);

    }

    @Override
    public List<ReferralCommentDmo> selectList(ReferralCommentDmo con) {

        return commonDao.selectList(con);

    }

    @Override
    @Transactional
    public Result submitReferralComment(ReferralCommentDmo con) {

        Result result = new Result();

        //检查评论医生的病例访问权限
        ReferralCasePermissionResult permissionResult = referralCaseService.checkDoctorCasePermission(con.getRecordId(), con.getUserId());

        if (!permissionResult.isSuccess()) {
            result.fail("SCC-0000", "提交评论用户没有病例访问权限");
            return result;
        }
        //检查转诊病例状态
        if (!Constants.ReferralCaseStatus.REFERRALED.equals(permissionResult.getReferralStatus())) {
            result.fail("SRR-0000", "转诊信息状态异常");
            return result;
        }
        //检查评论医生是否是确认接诊医生
        ReferralReceiveDmo referralReceiveCon = new ReferralReceiveDmo();

        referralReceiveCon.setRecordId(con.getRecordId());
        referralReceiveCon.setUserId(con.getUserId());
        referralReceiveCon.setStatus(Constants.MyRecvStatus.RECV_SUCCESS);

        ReferralReceiveDmo referralReceiveDmo = referralReceiveService.selectOne(referralReceiveCon);

        if (null == referralReceiveDmo){
            result.fail("SRR-0001", "评分医生非确认接诊医生，评分失败");
            return result;
        }

        //检查评论信息是否已存在
        ReferralCommentDmo referralCommentCon = new ReferralCommentDmo();

        referralCommentCon.setUserId(con.getUserId());
        referralCommentCon.setRecordId(con.getRecordId());

        ReferralCommentDmo existReferralCommentDmo = selectOne(referralCommentCon);

        if (null != existReferralCommentDmo){
            result.fail("SRR-0002", "评分医生已经评论过该转诊病例，评分失败");
            return result;
        }

        //新增转诊评论信息
        ReferralCommentDmo referralCommentDmo = new ReferralCommentDmo();

        referralCommentDmo.setRecordId(con.getRecordId());
        referralCommentDmo.setUserId(con.getUserId());
        referralCommentDmo.setCaseDetail(con.getCaseDetail());
        referralCommentDmo.setCaseIntegrity(con.getCaseIntegrity());
        referralCommentDmo.setCreateTime(DateUtil.getDate());

        result = insert(referralCommentDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        //更新医生评分信息
        DoctorMemberDmo doctorMemberDmo = new DoctorMemberDmo();

        doctorMemberDmo.setId(permissionResult.getPostDoctorId());

        doctorMemberDmo.setStarLevel(calStarLevel(permissionResult.getPostDoctorId()));

        result = doctorMemberService.update(doctorMemberDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(permissionResult.getPostDoctorId());
        doctorMemberCon.setStatus(Constants.MemberStatus.NORMAL);

        DoctorMemberDto doctorMemberDto = doctorMemberService.findDoctorBaseInfo(doctorMemberCon);

        if (null == doctorMemberDto){

            throw new AppException(result);
        }

        //增加转诊医生积分
        DoctorScoreDetailDmo postDoctorScoreDetailDmo = new DoctorScoreDetailDmo();

        postDoctorScoreDetailDmo.setUserId(permissionResult.getPostDoctorId());
        postDoctorScoreDetailDmo.setUserName(doctorMemberDto.getDoctorName());
        postDoctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.REFERRAL_SCORED);
        postDoctorScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_DOCTOR_REFERRAL_COMMENT));
        postDoctorScoreDetailDmo.setScore(con.getCaseDetail()+con.getCaseIntegrity());
        postDoctorScoreDetailDmo.setTime(DateUtil.getDate());

        result = doctorScoreService.addScoreEvent(postDoctorScoreDetailDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        //增加接诊医生积分
        DoctorScoreDetailDmo doctorScoreDetailDmo = new DoctorScoreDetailDmo();

        doctorScoreDetailDmo.setUserId(con.getUserId());
        doctorScoreDetailDmo.setUserName(permissionResult.getDoctorName());
        doctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.SCORE_REFERRAL);
        doctorScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_DOCTOR_RECEIVE_COMMENT));
        doctorScoreDetailDmo.setScore(10L);
        doctorScoreDetailDmo.setTime(DateUtil.getDate());

        result = doctorScoreService.addScoreEvent(doctorScoreDetailDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        DoctorMessageDmo doctorMessageDmo = new DoctorMessageDmo();

        doctorMessageDmo.setUserId(permissionResult.getPostDoctorId());
        doctorMessageDmo.setTitle(permissionResult.getTitle());
        doctorMessageDmo.setType(MessageConstants.MESSAGE_REFERRAL_COMMENT);

        doctorMessageService.addMessage(doctorMessageDmo);

        return result;

    }

    @Override
    public List<CalStarLevelDto> queryScoreInfo(ReferralCommentDmo con) {
        return commonDao.selectList("ReferralCommentMapper.queryScoreInfo",con);
    }

    @Override
    public Result hasScored(Long recordId, Long userId) {

        Result result = new Result();

        //检查评论信息是否已存在
        ReferralCommentDmo referralCommentCon = new ReferralCommentDmo();

        referralCommentCon.setUserId(userId);
        referralCommentCon.setRecordId(recordId);

        ReferralCommentDmo existReferralCommentDmo = selectOne(referralCommentCon);

        if (null == existReferralCommentDmo){
            result.fail("HSD-0001", "评分医生未评价");
            return result;
        }

        return result;
    }

    /**
     * 计算医生星级
     * @param userId
     * @return
     */
    private Long calStarLevel(Long userId){

        if (null == userId){
            return null;
        }

        HashMap<String,String> conMap = new HashMap<>();

        conMap.put("userId",userId.toString());

        CalStarLevelDto calStarLevelDto = (CalStarLevelDto)commonDao.selectOne("ReferralCommentMapper.calStarLevel",conMap);

        if (0 == calStarLevelDto.getScoreCount()){

            return 0L;

        }

        return calStarLevelDto.getScoreSum()/calStarLevelDto.getScoreCount();

    }
}
