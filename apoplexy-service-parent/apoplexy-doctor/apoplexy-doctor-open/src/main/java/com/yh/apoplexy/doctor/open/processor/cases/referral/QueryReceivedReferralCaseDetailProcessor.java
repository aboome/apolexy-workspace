package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCommentDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralScreenDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralIntentDoctorDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCaseService;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCommentService;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralReceiveService;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.NihssInfoItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReceiveDoctorItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralScreenItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.QueryReceivedReferralCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.QueryReceivedReferralCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-已被接诊详情(doc-0029)处理类
 * Created by wunder on 16/9/10 10:46.
 */
public class QueryReceivedReferralCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryReceivedReferralCaseDetailProcessor.class);

    @Autowired
    private ReferralCaseService referralCaseService;

    @Autowired
    private ReferralCommentService referralCommentService;

    @Autowired
    private ReferralReceiveService referralReceiveService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryReceivedReferralCaseDetailResponse queryReceivedReferralCaseDetailResponse = new QueryReceivedReferralCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryReceivedReferralCaseDetailRequest queryReceivedReferralCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryReceivedReferralCaseDetailRequest.class);

        if (null == queryReceivedReferralCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryReceivedReferralCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryReceivedReferralCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryReceivedReferralCaseDetailRequest.getUserId());

        ReferralCaseDmo con = new ReferralCaseDmo();

        con.setId(recordId);

        ReferralCaseDetailDto referralCaseDetailDto = referralCaseService.findCaseDetail(con);

        if (null == referralCaseDetailDto || null == referralCaseDetailDto.getDoctorMemberDmo()) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        if(Constants.ReferralCaseStatus.CANCEL.equals(referralCaseDetailDto.getReferralCaseDmo().getStatus())){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        DoctorMemberDmo doctorMemberDmo = referralCaseDetailDto.getDoctorMemberDmo();

        ReferralCaseDmo referralCaseDmo = referralCaseDetailDto.getReferralCaseDmo();

        ReferralResourceDmo video = referralCaseDetailDto.getVideo();

        List<ReferralResourceDmo> imageList = referralCaseDetailDto.getImageList();

        List<NihssDetailDmo> nihssDetailDmoList = referralCaseDetailDto.getNihssList();

        List<ReferralIntentDoctorDto> referralIntentDoctorDtoList = referralCaseDetailDto.getReceiveList();

        ReferralCommentDmo referralCommentCon = new ReferralCommentDmo();

        referralCommentCon.setRecordId(recordId);

        ReferralCommentDmo referralCommentDmo = referralCommentService.selectOne(referralCommentCon);

        if (null == referralCommentDmo){
            queryReceivedReferralCaseDetailResponse.setDoctorStarLevel("0");
        }else {
            queryReceivedReferralCaseDetailResponse.setDoctorStarLevel(String.valueOf(referralCommentDmo.getCaseDetail()+referralCommentDmo.getCaseIntegrity()));
        }


        queryReceivedReferralCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryReceivedReferralCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryReceivedReferralCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryReceivedReferralCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryReceivedReferralCaseDetailResponse.setRecordId(String.valueOf(referralCaseDmo.getId()));
        queryReceivedReferralCaseDetailResponse.setRecordTime(DateUtil.format(referralCaseDmo.getCreateTime(), DateUtil.yyyyMMddHHmmss));
        queryReceivedReferralCaseDetailResponse.setRecordType(referralCaseDmo.getCaseRange());
        queryReceivedReferralCaseDetailResponse.setReferralType(referralCaseDmo.getType());
        queryReceivedReferralCaseDetailResponse.setReferralTitle(referralCaseDmo.getTitle());
        queryReceivedReferralCaseDetailResponse.setPatientAge(String.valueOf(referralCaseDmo.getPatientAge()));
        queryReceivedReferralCaseDetailResponse.setPatientSex(referralCaseDmo.getPatientSex());
        queryReceivedReferralCaseDetailResponse.setTemperature(String.valueOf(referralCaseDmo.getTemperature()));
        queryReceivedReferralCaseDetailResponse.setHeartRate(String.valueOf(referralCaseDmo.getHeartRate()));
        queryReceivedReferralCaseDetailResponse.setBreath(String.valueOf(referralCaseDmo.getBreath()));
        queryReceivedReferralCaseDetailResponse.setConsciousness(referralCaseDmo.getConsciousness());
        queryReceivedReferralCaseDetailResponse.setHighPressure(String.valueOf(referralCaseDmo.getHighPressure()));
        queryReceivedReferralCaseDetailResponse.setLowPressure(String.valueOf(referralCaseDmo.getLowPressure()));
        queryReceivedReferralCaseDetailResponse.setNasogastricTube(DateUtil.format(referralCaseDmo.getNasogastricTube(), DateUtil.yyyyMMddHHmmss));
        queryReceivedReferralCaseDetailResponse.setIndwellingCatheter(DateUtil.format(referralCaseDmo.getIndwellingCatheter(), DateUtil.yyyyMMddHHmmss));
        queryReceivedReferralCaseDetailResponse.setSuperficialVein(DateUtil.format(referralCaseDmo.getSuperficialVein(), DateUtil.yyyyMMddHHmmss));
        queryReceivedReferralCaseDetailResponse.setDeepVein(DateUtil.format(referralCaseDmo.getDeepVein(), DateUtil.yyyyMMddHHmmss));
        queryReceivedReferralCaseDetailResponse.setPicc(DateUtil.format(referralCaseDmo.getPicc(), DateUtil.yyyyMMddHHmmss));
        queryReceivedReferralCaseDetailResponse.setSkinType(referralCaseDmo.getSkinType());
        queryReceivedReferralCaseDetailResponse.setSkinDesc(referralCaseDmo.getSkinDesc());
        queryReceivedReferralCaseDetailResponse.setStatus(referralCaseDmo.getStatus());

        if (null != video) {
            queryReceivedReferralCaseDetailResponse.setVideoId(String.valueOf(video.getResourceId()));
        }

        List<ImageItem> imageItemList = new ArrayList<>();

        ImageItem imageItem = null;

        if (!CollectionUtils.isEmpty(imageList)) {

            for (ReferralResourceDmo image : imageList) {

                imageItem = new ImageItem();

                imageItem.setImageId(String.valueOf(image.getResourceId()));

                imageItemList.add(imageItem);

            }

        }

        queryReceivedReferralCaseDetailResponse.setImageList(imageItemList);

        List<NihssInfoItem> nihssInfoItemList = new ArrayList<>();

        NihssInfoItem nihssInfoItem = null;

        if (!CollectionUtils.isEmpty(nihssDetailDmoList)) {

            for (NihssDetailDmo nihssDetailDmo : nihssDetailDmoList) {

                nihssInfoItem = new NihssInfoItem();

                nihssInfoItem.setIndex(String.valueOf(nihssDetailDmo.getDetailIndex()));
                nihssInfoItem.setFee(String.valueOf(nihssDetailDmo.getResult()));

                nihssInfoItemList.add(nihssInfoItem);

            }
        }

        if (!CollectionUtils.isEmpty(referralCaseDetailDto.getScreenList())){

            List<ReferralScreenItem> referralScreenItemList = new ArrayList<>();

            ReferralScreenItem referralScreenItem = null;

            for (ReferralScreenDetailDmo referralScreenDetailDmo: referralCaseDetailDto.getScreenList()){

                referralScreenItem = new ReferralScreenItem();

                referralScreenItem.setIndex(String.valueOf(referralScreenDetailDmo.getDetailIndex()));
                referralScreenItem.setAnswer(String.valueOf(referralScreenDetailDmo.getResult()));

                referralScreenItemList.add(referralScreenItem);

            }
            queryReceivedReferralCaseDetailResponse.setScreenList(referralScreenItemList);
        }

        queryReceivedReferralCaseDetailResponse.setNihssList(nihssInfoItemList);
        queryReceivedReferralCaseDetailResponse.setNihssTotalFee(String.valueOf(referralCaseDmo.getNihssTotalFee()));
        queryReceivedReferralCaseDetailResponse.setDescription(referralCaseDmo.getPatientSub());
        queryReceivedReferralCaseDetailResponse.setMainDesc(referralCaseDmo.getMainDesc());
        queryReceivedReferralCaseDetailResponse.setNowIllness(referralCaseDmo.getNowIllness());
        queryReceivedReferralCaseDetailResponse.setHistoryIllness(referralCaseDmo.getHisIllness());
        queryReceivedReferralCaseDetailResponse.setHealthCheck(referralCaseDmo.getHealthCheck());
        queryReceivedReferralCaseDetailResponse.setAssistCheck(referralCaseDmo.getAssistCheck());
        queryReceivedReferralCaseDetailResponse.setReadCount(String.valueOf(referralCaseDmo.getReadCount()));
        queryReceivedReferralCaseDetailResponse.setReceiveCount(String.valueOf(referralCaseDmo.getReceiveCount()));
        queryReceivedReferralCaseDetailResponse.setStatus(referralCaseDmo.getStatus());

        Result result = referralReceiveService.hasReceived(recordId,userId);

        if (result.isSuccess()){
            queryReceivedReferralCaseDetailResponse.setHasReceived(APPResponseCodeConstants.ReferralHasReceived.YES);
        }else {
            queryReceivedReferralCaseDetailResponse.setHasReceived(APPResponseCodeConstants.ReferralHasReceived.NO);
        }

        result = referralCommentService.hasScored(recordId,userId);

        if (result.isSuccess()){
            queryReceivedReferralCaseDetailResponse.setHasScored(APPResponseCodeConstants.ReferralHasScored.YES);
        }else {
            queryReceivedReferralCaseDetailResponse.setHasScored(APPResponseCodeConstants.ReferralHasScored.NO);
        }

        response.setParameter(queryReceivedReferralCaseDetailResponse);

        return response;
    }
}
