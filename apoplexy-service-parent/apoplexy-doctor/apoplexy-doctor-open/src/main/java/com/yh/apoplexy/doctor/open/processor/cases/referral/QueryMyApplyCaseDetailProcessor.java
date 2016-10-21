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
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReceiveDoctorItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralScreenItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.QueryMyApplyCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.QueryMyApplyCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我的申请详情(doc-0024)处理类
 * Created by wunder on 16/9/10 10:33.
 */
public class QueryMyApplyCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyApplyCaseDetailProcessor.class);

    @Autowired
    private ReferralCaseService referralCaseService;

    @Autowired
    private ReferralCommentService referralCommentService;

    @Autowired
    private ReferralReceiveService referralReceiveService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyApplyCaseDetailResponse queryMyApplyCaseDetailResponse = new QueryMyApplyCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyApplyCaseDetailRequest queryMyApplyCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryMyApplyCaseDetailRequest.class);

        if (null == queryMyApplyCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyApplyCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryMyApplyCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryMyApplyCaseDetailRequest.getUserId());

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

        queryMyApplyCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryMyApplyCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryMyApplyCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryMyApplyCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryMyApplyCaseDetailResponse.setRecordId(String.valueOf(referralCaseDmo.getId()));
        queryMyApplyCaseDetailResponse.setRecordTime(DateUtil.format(referralCaseDmo.getCreateTime(), DateUtil.yyyyMMddHHmmss));
        queryMyApplyCaseDetailResponse.setRecordType(referralCaseDmo.getCaseRange());
        queryMyApplyCaseDetailResponse.setReferralType(referralCaseDmo.getType());
        queryMyApplyCaseDetailResponse.setReferralTitle(referralCaseDmo.getTitle());
        queryMyApplyCaseDetailResponse.setPatientAge(String.valueOf(referralCaseDmo.getPatientAge()));
        queryMyApplyCaseDetailResponse.setPatientSex(referralCaseDmo.getPatientSex());
        queryMyApplyCaseDetailResponse.setTemperature(String.valueOf(referralCaseDmo.getTemperature()));
        queryMyApplyCaseDetailResponse.setHeartRate(String.valueOf(referralCaseDmo.getHeartRate()));
        queryMyApplyCaseDetailResponse.setBreath(String.valueOf(referralCaseDmo.getBreath()));
        queryMyApplyCaseDetailResponse.setConsciousness(referralCaseDmo.getConsciousness());
        queryMyApplyCaseDetailResponse.setHighPressure(String.valueOf(referralCaseDmo.getHighPressure()));
        queryMyApplyCaseDetailResponse.setLowPressure(String.valueOf(referralCaseDmo.getLowPressure()));
        queryMyApplyCaseDetailResponse.setNasogastricTube(DateUtil.format(referralCaseDmo.getNasogastricTube(), DateUtil.yyyyMMddHHmmss));
        queryMyApplyCaseDetailResponse.setIndwellingCatheter(DateUtil.format(referralCaseDmo.getIndwellingCatheter(), DateUtil.yyyyMMddHHmmss));
        queryMyApplyCaseDetailResponse.setSuperficialVein(DateUtil.format(referralCaseDmo.getSuperficialVein(), DateUtil.yyyyMMddHHmmss));
        queryMyApplyCaseDetailResponse.setDeepVein(DateUtil.format(referralCaseDmo.getDeepVein(), DateUtil.yyyyMMddHHmmss));
        queryMyApplyCaseDetailResponse.setPicc(DateUtil.format(referralCaseDmo.getPicc(), DateUtil.yyyyMMddHHmmss));
        queryMyApplyCaseDetailResponse.setSkinType(referralCaseDmo.getSkinType());
        queryMyApplyCaseDetailResponse.setSkinDesc(referralCaseDmo.getSkinDesc());

        if (null != video) {
            queryMyApplyCaseDetailResponse.setVideoId(String.valueOf(video.getResourceId()));
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

        queryMyApplyCaseDetailResponse.setImageList(imageItemList);

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

        queryMyApplyCaseDetailResponse.setNihssList(nihssInfoItemList);
        queryMyApplyCaseDetailResponse.setNihssTotalFee(String.valueOf(referralCaseDmo.getNihssTotalFee()));
        queryMyApplyCaseDetailResponse.setDescription(referralCaseDmo.getPatientSub());
        queryMyApplyCaseDetailResponse.setMainDesc(referralCaseDmo.getMainDesc());
        queryMyApplyCaseDetailResponse.setNowIllness(referralCaseDmo.getNowIllness());
        queryMyApplyCaseDetailResponse.setHistoryIllness(referralCaseDmo.getHisIllness());
        queryMyApplyCaseDetailResponse.setHealthCheck(referralCaseDmo.getHealthCheck());
        queryMyApplyCaseDetailResponse.setAssistCheck(referralCaseDmo.getAssistCheck());
        queryMyApplyCaseDetailResponse.setReadCount(String.valueOf(referralCaseDmo.getReadCount()));
        queryMyApplyCaseDetailResponse.setReceiveCount(String.valueOf(referralCaseDmo.getReceiveCount()));
        queryMyApplyCaseDetailResponse.setStatus(referralCaseDmo.getStatus());

        if (!CollectionUtils.isEmpty(referralIntentDoctorDtoList)) {

            List<ReceiveDoctorItem> receiveDoctorItemList = new ArrayList<>();

            ReceiveDoctorItem receiveDoctorItem = null;

            for (ReferralIntentDoctorDto referralIntentDoctorDto : referralIntentDoctorDtoList) {

                receiveDoctorItem = new ReceiveDoctorItem();

                receiveDoctorItem.setDoctorId(String.valueOf(referralIntentDoctorDto.getDoctorId()));
                receiveDoctorItem.setDoctorName(referralIntentDoctorDto.getDoctorName());
                receiveDoctorItem.setDoctorPhoto(referralIntentDoctorDto.getDoctorPhoto());
                receiveDoctorItem.setHospital(referralIntentDoctorDto.getHospital());
                receiveDoctorItem.setRecvTime(DateUtil.format(referralIntentDoctorDto.getRecvTime(), DateUtil.yyyyMMddHHmmss));

                receiveDoctorItemList.add(receiveDoctorItem);

            }

            queryMyApplyCaseDetailResponse.setDoctorList(receiveDoctorItemList);

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
            queryMyApplyCaseDetailResponse.setScreenList(referralScreenItemList);

        }

        Result result = referralReceiveService.hasReceived(recordId,userId);

        if (result.isSuccess()){
            queryMyApplyCaseDetailResponse.setHasReceived(APPResponseCodeConstants.ReferralHasReceived.YES);
        }else {
            queryMyApplyCaseDetailResponse.setHasReceived(APPResponseCodeConstants.ReferralHasReceived.NO);
        }

        result = referralCommentService.hasScored(recordId,userId);

        if (result.isSuccess()){
            queryMyApplyCaseDetailResponse.setHasScored(APPResponseCodeConstants.ReferralHasScored.YES);
        }else {
            queryMyApplyCaseDetailResponse.setHasScored(APPResponseCodeConstants.ReferralHasScored.NO);
        }

        response.setParameter(queryMyApplyCaseDetailResponse);

        return response;

    }
}
