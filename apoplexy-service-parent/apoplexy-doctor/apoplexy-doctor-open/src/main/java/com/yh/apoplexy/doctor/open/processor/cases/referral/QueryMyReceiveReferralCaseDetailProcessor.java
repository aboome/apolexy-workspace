package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
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
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralScreenItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.QueryMyReceiveReferralCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.QueryMyReceiveReferralCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我的接诊详情(doc-0031)处理类
 * Created by wunder on 16/9/10 11:25.
 */
public class QueryMyReceiveReferralCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyReceiveReferralCaseDetailProcessor.class);

    @Autowired
    private ReferralCaseService referralCaseService;

    @Autowired
    private ReferralCommentService referralCommentService;

    @Autowired
    private ReferralReceiveService referralReceiveService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyReceiveReferralCaseDetailResponse queryMyReceiveReferralCaseDetailResponse = new QueryMyReceiveReferralCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyReceiveReferralCaseDetailRequest queryMyReceiveReferralCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryMyReceiveReferralCaseDetailRequest.class);

        if (null == queryMyReceiveReferralCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyReceiveReferralCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryMyReceiveReferralCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryMyReceiveReferralCaseDetailRequest.getUserId());

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

        queryMyReceiveReferralCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryMyReceiveReferralCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryMyReceiveReferralCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryMyReceiveReferralCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryMyReceiveReferralCaseDetailResponse.setRecordId(String.valueOf(referralCaseDmo.getId()));
        queryMyReceiveReferralCaseDetailResponse.setRecordTime(DateUtil.format(referralCaseDmo.getCreateTime(), DateUtil.yyyyMMddHHmmss));
        queryMyReceiveReferralCaseDetailResponse.setRecordType(referralCaseDmo.getCaseRange());
        queryMyReceiveReferralCaseDetailResponse.setReferralType(referralCaseDmo.getType());
        queryMyReceiveReferralCaseDetailResponse.setReferralTitle(referralCaseDmo.getTitle());
        queryMyReceiveReferralCaseDetailResponse.setPatientAge(String.valueOf(referralCaseDmo.getPatientAge()));
        queryMyReceiveReferralCaseDetailResponse.setPatientSex(referralCaseDmo.getPatientSex());
        queryMyReceiveReferralCaseDetailResponse.setTemperature(String.valueOf(referralCaseDmo.getTemperature()));
        queryMyReceiveReferralCaseDetailResponse.setHeartRate(String.valueOf(referralCaseDmo.getHeartRate()));
        queryMyReceiveReferralCaseDetailResponse.setBreath(String.valueOf(referralCaseDmo.getBreath()));
        queryMyReceiveReferralCaseDetailResponse.setConsciousness(referralCaseDmo.getConsciousness());
        queryMyReceiveReferralCaseDetailResponse.setHighPressure(String.valueOf(referralCaseDmo.getHighPressure()));
        queryMyReceiveReferralCaseDetailResponse.setLowPressure(String.valueOf(referralCaseDmo.getLowPressure()));
        queryMyReceiveReferralCaseDetailResponse.setNasogastricTube(DateUtil.format(referralCaseDmo.getNasogastricTube(), DateUtil.yyyyMMddHHmmss));
        queryMyReceiveReferralCaseDetailResponse.setIndwellingCatheter(DateUtil.format(referralCaseDmo.getIndwellingCatheter(), DateUtil.yyyyMMddHHmmss));
        queryMyReceiveReferralCaseDetailResponse.setSuperficialVein(DateUtil.format(referralCaseDmo.getSuperficialVein(), DateUtil.yyyyMMddHHmmss));
        queryMyReceiveReferralCaseDetailResponse.setDeepVein(DateUtil.format(referralCaseDmo.getDeepVein(), DateUtil.yyyyMMddHHmmss));
        queryMyReceiveReferralCaseDetailResponse.setPicc(DateUtil.format(referralCaseDmo.getPicc(), DateUtil.yyyyMMddHHmmss));
        queryMyReceiveReferralCaseDetailResponse.setSkinType(referralCaseDmo.getSkinType());
        queryMyReceiveReferralCaseDetailResponse.setSkinDesc(referralCaseDmo.getSkinDesc());

        if (null != video) {
            queryMyReceiveReferralCaseDetailResponse.setVideoId(String.valueOf(video.getResourceId()));
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

        queryMyReceiveReferralCaseDetailResponse.setImageList(imageItemList);

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
            queryMyReceiveReferralCaseDetailResponse.setScreenList(referralScreenItemList);

        }

        queryMyReceiveReferralCaseDetailResponse.setNihssList(nihssInfoItemList);
        queryMyReceiveReferralCaseDetailResponse.setNihssTotalFee(String.valueOf(referralCaseDmo.getNihssTotalFee()));
        queryMyReceiveReferralCaseDetailResponse.setDescription(referralCaseDmo.getPatientSub());
        queryMyReceiveReferralCaseDetailResponse.setMainDesc(referralCaseDmo.getMainDesc());
        queryMyReceiveReferralCaseDetailResponse.setNowIllness(referralCaseDmo.getNowIllness());
        queryMyReceiveReferralCaseDetailResponse.setHistoryIllness(referralCaseDmo.getHisIllness());
        queryMyReceiveReferralCaseDetailResponse.setHealthCheck(referralCaseDmo.getHealthCheck());
        queryMyReceiveReferralCaseDetailResponse.setAssistCheck(referralCaseDmo.getAssistCheck());
        queryMyReceiveReferralCaseDetailResponse.setReadCount(String.valueOf(referralCaseDmo.getReadCount()));
        queryMyReceiveReferralCaseDetailResponse.setReceiveCount(String.valueOf(referralCaseDmo.getReceiveCount()));
        queryMyReceiveReferralCaseDetailResponse.setStatus(referralCaseDmo.getStatus());

        Result result = referralReceiveService.hasReceived(recordId,userId);

        if (result.isSuccess()){
            queryMyReceiveReferralCaseDetailResponse.setHasReceived(APPResponseCodeConstants.ReferralHasReceived.YES);
        }else {
            queryMyReceiveReferralCaseDetailResponse.setHasReceived(APPResponseCodeConstants.ReferralHasReceived.NO);
        }

        result = referralCommentService.hasScored(recordId,userId);

        if (result.isSuccess()){
            queryMyReceiveReferralCaseDetailResponse.setHasScored(APPResponseCodeConstants.ReferralHasScored.YES);
        }else {
            queryMyReceiveReferralCaseDetailResponse.setHasScored(APPResponseCodeConstants.ReferralHasScored.NO);
        }

        response.setParameter(queryMyReceiveReferralCaseDetailResponse);

        return response;
    }
}
