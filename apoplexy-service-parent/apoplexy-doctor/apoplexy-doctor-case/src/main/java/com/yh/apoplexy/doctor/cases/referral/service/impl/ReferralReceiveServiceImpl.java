package com.yh.apoplexy.doctor.cases.referral.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralReceiveDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMessageDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorScoreDetailDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.QueryIntentDoctorDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralIntentDoctorDto;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.*;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.cases.referral.dto.ConfirmReferralDto;
import com.yh.apoplexy.doctor.cases.referral.dto.UpdateReferralReceiveDto;
import com.yh.apoplexy.doctor.cases.referral.result.ReferralCasePermissionResult;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCaseService;
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

import java.util.List;

/**
 * 意向接诊服务实现类
 * Created by wunder on 16/9/9 14:10.
 */
@Service("referralReceiveService")
@ServiceTrace
public class ReferralReceiveServiceImpl implements ReferralReceiveService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private ReferralCaseService referralCaseService;

    @Autowired
    private DoctorScoreService doctorScoreService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private DoctorMessageService doctorMessageService;

    @Override
    public Result insert(ReferralReceiveDmo referralReceiveDmo) {

        int i = commonDao.insert(referralReceiveDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result update(ReferralReceiveDmo referralReceiveDmo) {

        int i = commonDao.update(referralReceiveDmo);

        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public Result delete(ReferralReceiveDmo referralReceiveDmo) {

        int i = commonDao.delete(referralReceiveDmo);

        return SqlAssertUtils.deleteAssert(i);
    }

    @Override
    public ReferralReceiveDmo selectOne(ReferralReceiveDmo con) {
        return (ReferralReceiveDmo) commonDao.selectOne(con);
    }

    @Override
    public List<ReferralReceiveDmo> selectList(ReferralReceiveDmo con) {
        return commonDao.selectList(con);
    }

    @Override
    public List<ReferralIntentDoctorDto> queryIntentDoctorList(QueryIntentDoctorDto con) {

        con.setDoctorStatus(Constants.MemberStatus.NORMAL);

        return commonDao.selectList("ReferralReceiveMapper.queryIntentDoctor", con);
    }

    @Override
    @Transactional
    public Result submitReferralReceive(ReferralReceiveDmo referralReceiveDmo) {

        Result result = new Result();
        //检查提交接诊申请医生的病例访问权限
        ReferralCasePermissionResult permissionResult = referralCaseService.checkDoctorCasePermission(referralReceiveDmo.getRecordId(), referralReceiveDmo.getUserId());

        if (!permissionResult.isSuccess()) {
            result.fail("SRR-0000", "提交接诊申请用户没有病例访问权限");
            return result;
        }

        if (!Constants.ReferralCaseStatus.NOT_REFERRAL.equals(permissionResult.getReferralStatus())) {
            result.fail("SRR-0000", "转诊信息状态异常");
            return result;
        }

        referralReceiveDmo.setStatus(Constants.MyRecvStatus.RECVING);
        ReferralReceiveDmo existReferralReceiveDmo = selectOne(referralReceiveDmo);

        if (null != existReferralReceiveDmo) {

            result.fail("SRR-0001", "提交接诊申请用户重复提交申请");
            return result;

        }

        //新增意向接诊信息
        referralReceiveDmo.setCreateTime(DateUtil.getDate());
        referralReceiveDmo.setLastUpdateTime(DateUtil.getDate());
        referralReceiveDmo.setStatus(Constants.MyRecvStatus.RECVING);

        result = insert(referralReceiveDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        //增加意向接诊数量
        result = referralCaseService.increaseReceiveCount(referralReceiveDmo.getRecordId());

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        DoctorMessageDmo doctorMessageDmo = new DoctorMessageDmo();

        doctorMessageDmo.setUserId(permissionResult.getPostDoctorId());
        doctorMessageDmo.setTitle(permissionResult.getTitle());
        doctorMessageDmo.setType(MessageConstants.MESSAGE_REFERRAL_RECEIVE);

        doctorMessageService.addMessage(doctorMessageDmo);

        return result;

    }

    @Override
    @Transactional
    public Result confirmReferral(ConfirmReferralDto confirmReferralDto) {

        Result result = new Result();
        //检查意向接诊医生的病例访问权限
        ReferralCasePermissionResult permissionResult = referralCaseService.checkDoctorCasePermission(confirmReferralDto.getRecordId(), confirmReferralDto.getToDoctorId());

        if (!permissionResult.isSuccess()) {
            result.fail("SCC-0000", "提交接诊申请用户没有病例访问权限");
            return result;
        }

        if (!Constants.ReferralCaseStatus.NOT_REFERRAL.equals(permissionResult.getReferralStatus())) {
            result.fail("SRR-0000", "转诊信息状态异常");
            return result;
        }

        //检查转诊申请医生信息
        if (null == confirmReferralDto.getUserId() || !confirmReferralDto.getUserId().equals(permissionResult.getPostDoctorId())) {

            result.fail("SCC-0000", "发布转诊信息医生与确认转诊信息医生不相同");
            return result;

        }
        //更新意向接诊信息
        result = updateReferralReceiveInfo(confirmReferralDto.getRecordId(), confirmReferralDto.getToDoctorId(),permissionResult.getTitle());

        if (!result.isSuccess()) {

            throw new AppException(result);

        }
        //更新转诊信息
        ReferralCaseDmo referralCaseDmo = new ReferralCaseDmo();

        referralCaseDmo.setId(confirmReferralDto.getRecordId());
        referralCaseDmo.setStatus(Constants.ReferralCaseStatus.REFERRALED);
        referralCaseDmo.setLastUpdateTime(DateUtil.getDate());

        result = referralCaseService.update(referralCaseDmo);


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
        postDoctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.REFERRAL_SUCCESS);
        postDoctorScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_DOCTOR_REFERRAL_CONFIRM));
        postDoctorScoreDetailDmo.setScore(10L);
        postDoctorScoreDetailDmo.setTime(DateUtil.getDate());

        result = doctorScoreService.addScoreEvent(postDoctorScoreDetailDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        //增加接诊医生积分
        DoctorScoreDetailDmo doctorScoreDetailDmo = new DoctorScoreDetailDmo();

        doctorScoreDetailDmo.setUserId(confirmReferralDto.getToDoctorId());
        doctorScoreDetailDmo.setUserName(permissionResult.getDoctorName());
        doctorScoreDetailDmo.setEvent(DoctorConstants.ScoreEvent.RECEIVE_SUCCESS);
        doctorScoreDetailDmo.setEventDesc(ScoreConstants.SCORE_TYPE_EVENT_MAP.get(ScoreConstants.SCORE_DOCTOR_RECEIVE_SUCCESS));
        doctorScoreDetailDmo.setScore(10L);
        doctorScoreDetailDmo.setTime(DateUtil.getDate());

        result = doctorScoreService.addScoreEvent(doctorScoreDetailDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);
        }

        DoctorMessageDmo doctorMessageDmo = new DoctorMessageDmo();

        doctorMessageDmo.setUserId(permissionResult.getPostDoctorId());
        doctorMessageDmo.setTitle(permissionResult.getTitle());
        doctorMessageDmo.setType(MessageConstants.MESSAGE_REFERRAL_CONFIRM);

        doctorMessageService.addMessage(doctorMessageDmo);

        return result;
    }

    @Override
    @Transactional
    public Result cancelReceive(Long recordId, Long userId) {

        Result result = new Result();

        //检查意向接诊医生的病例访问权限
        ReferralCasePermissionResult permissionResult = referralCaseService.checkDoctorCasePermission(recordId, userId);

        if (!permissionResult.isSuccess()) {
            result.fail("CRR-0000", "提交申请用户没有病例访问权限");
            return result;
        }

        if (!Constants.ReferralCaseStatus.NOT_REFERRAL.equals(permissionResult.getReferralStatus())) {
            result.fail("CRR-0001", "转诊信息状态异常");
            return result;
        }

        //查询意向接诊信息
        ReferralReceiveDmo con = new ReferralReceiveDmo();

        con.setUserId(userId);
        con.setRecordId(recordId);
        con.setStatus(Constants.MyRecvStatus.RECVING);

        ReferralReceiveDmo referralReceiveDmo = selectOne(con);

        if (null == referralReceiveDmo || !Constants.MyRecvStatus.RECVING.equals(referralReceiveDmo.getStatus())) {

            result.fail("CRR-0002", "意向接诊信息状态异常");
            return result;

        }

        //更新意向接诊信息
        referralReceiveDmo.setStatus(Constants.MyRecvStatus.CANCEL);

        result = update(referralReceiveDmo);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        //减少转诊病例接诊数
        result = referralCaseService.decreaseReceiveCount(recordId);

        if (!result.isSuccess()) {

            throw new AppException(result);

        }

        return result;
    }

    @Override
    public Result hasReceived(Long recordId, Long userId) {

        Result result = new Result();

        //查询意向接诊信息
        ReferralReceiveDmo con = new ReferralReceiveDmo();

        con.setUserId(userId);
        con.setRecordId(recordId);
        con.setStatus(Constants.MyRecvStatus.RECVING);

        ReferralReceiveDmo referralReceiveDmo = selectOne(con);

        if (null == referralReceiveDmo || !Constants.MyRecvStatus.RECVING.equals(referralReceiveDmo.getStatus())) {

            result.fail("HRD-0001", "意向接诊信息不存在");
            return result;

        }

        return result;
    }

    /**
     * 更新意向接诊信息
     *
     * @param recordId 转诊记录id
     * @param userId   接诊医生
     * @return
     */
    private Result updateReferralReceiveInfo(Long recordId, Long userId,String title) {

        Result result = new Result();
        //参数判空
        if (null == recordId || null == userId) {

            result.fail("URR-0000", "更新意向接诊信息失败");

            return result;

        }
        //更新接诊成功的接诊意向信息
        result = updateReceiveSuccess(recordId, userId,title);

        if (!result.isSuccess()) {

            result.fail("URR-0001", "更新接诊成功意向接诊信息失败");

            return result;

        }

        //更新接诊失败的接诊意向信息
        result = updateReceiveFailed(recordId, userId,title);

        if (!result.isSuccess()) {

            result.fail("URR-0002", "更新接诊失败意向接诊信息失败");

            return result;

        }

        return result;
    }

    /**
     * 更新接诊成功信息
     *
     * @param recordId
     * @param userId
     * @return
     */
    private Result updateReceiveSuccess(Long recordId, Long userId,String title) {

        ReferralReceiveDmo con = new ReferralReceiveDmo();

        con.setRecordId(recordId);
        con.setUserId(userId);
        con.setStatus(Constants.MyRecvStatus.RECVING);

        ReferralReceiveDmo referralReceiveDmo = selectOne(con);

        referralReceiveDmo.setStatus(Constants.MyRecvStatus.RECV_SUCCESS);
        referralReceiveDmo.setLastUpdateTime(DateUtil.getDate());

        DoctorMessageDmo doctorMessageDmo = new DoctorMessageDmo();

        doctorMessageDmo.setUserId(userId);
        doctorMessageDmo.setTitle(title);
        doctorMessageDmo.setType(MessageConstants.MESSAGE_RECEIVE_SUCCESS);

        doctorMessageService.addMessage(doctorMessageDmo);

        return update(referralReceiveDmo);
    }

    /**
     * 更新接诊失败信息
     *
     * @param recordId
     * @param userId
     * @return
     */
    private Result updateReceiveFailed(Long recordId, Long userId,String title) {

        Result result = new Result();

        ReferralReceiveDmo con = new ReferralReceiveDmo();

        con.setRecordId(recordId);
        con.setStatus(Constants.MyRecvStatus.RECVING);

        List<ReferralReceiveDmo> referralReceiveDmoList = selectList(con);

        UpdateReferralReceiveDto updateReferralReceiveDto = new UpdateReferralReceiveDto();

        updateReferralReceiveDto.setRecordId(recordId);
        updateReferralReceiveDto.setUserId(userId);
        updateReferralReceiveDto.setNewStatus(Constants.MyRecvStatus.RECV_FAILED);
        updateReferralReceiveDto.setOldStatus(Constants.MyRecvStatus.RECVING);
        updateReferralReceiveDto.setLastUpdateTime(DateUtil.getDate());

        int i = commonDao.update("ReferralReceiveMapper.updateReceiveFailed", updateReferralReceiveDto);

        if (null != referralReceiveDmoList && i != referralReceiveDmoList.size()) {

            result.fail("URF-0000", "更新接诊意向信息失败");
            return result;
        }

        for (ReferralReceiveDmo referralReceiveDmo: referralReceiveDmoList){

            if (!userId.equals(referralReceiveDmo.getUserId())){
                DoctorMessageDmo doctorMessageDmo = new DoctorMessageDmo();

                doctorMessageDmo.setUserId(referralReceiveDmo.getUserId());
                doctorMessageDmo.setTitle(title);
                doctorMessageDmo.setType(MessageConstants.MESSAGE_RECEIVE_FAILED);

                doctorMessageService.addMessage(doctorMessageDmo);
            }

        }

        return result;

    }
}
