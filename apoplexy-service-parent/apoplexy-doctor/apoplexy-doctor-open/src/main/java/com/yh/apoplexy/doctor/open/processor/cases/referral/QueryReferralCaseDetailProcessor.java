package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralReceiveDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralScreenDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralIntentDoctorDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
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
import com.yh.apoplexy.doctor.open.request.cases.referral.QueryReferralCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.QueryReferralCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看转诊病例详情(doc-0021)处理类
 * Created by wunder on 16/9/9 09:51.
 */
public class QueryReferralCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryReferralCaseDetailProcessor.class);

    @Autowired
    private ReferralCaseService referralCaseService;

    @Autowired
    private ReferralCommentService referralCommentService;

    @Autowired
    private ReferralReceiveService referralReceiveService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryReferralCaseDetailResponse queryReferralCaseDetailResponse = new QueryReferralCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryReferralCaseDetailRequest queryReferralCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryReferralCaseDetailRequest.class);

        if (null == queryReferralCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryReferralCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryReferralCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryReferralCaseDetailRequest.getUserId());

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

        queryReferralCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryReferralCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryReferralCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryReferralCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryReferralCaseDetailResponse.setRecordId(String.valueOf(referralCaseDmo.getId()));
        queryReferralCaseDetailResponse.setRecordTime(DateUtil.format(referralCaseDmo.getCreateTime(), DateUtil.yyyyMMddHHmmss));
        queryReferralCaseDetailResponse.setRecordType(referralCaseDmo.getCaseRange());
        queryReferralCaseDetailResponse.setReferralType(referralCaseDmo.getType());
        queryReferralCaseDetailResponse.setReferralTitle(referralCaseDmo.getTitle());
        queryReferralCaseDetailResponse.setPatientAge(String.valueOf(referralCaseDmo.getPatientAge()));
        queryReferralCaseDetailResponse.setPatientSex(referralCaseDmo.getPatientSex());
        queryReferralCaseDetailResponse.setTemperature(String.valueOf(referralCaseDmo.getTemperature()));
        queryReferralCaseDetailResponse.setHeartRate(String.valueOf(referralCaseDmo.getHeartRate()));
        queryReferralCaseDetailResponse.setBreath(String.valueOf(referralCaseDmo.getBreath()));
        queryReferralCaseDetailResponse.setConsciousness(referralCaseDmo.getConsciousness());
        queryReferralCaseDetailResponse.setHighPressure(String.valueOf(referralCaseDmo.getHighPressure()));
        queryReferralCaseDetailResponse.setLowPressure(String.valueOf(referralCaseDmo.getLowPressure()));
        queryReferralCaseDetailResponse.setNasogastricTube(DateUtil.format(referralCaseDmo.getNasogastricTube(), DateUtil.yyyyMMddHHmmss));
        queryReferralCaseDetailResponse.setIndwellingCatheter(DateUtil.format(referralCaseDmo.getIndwellingCatheter(), DateUtil.yyyyMMddHHmmss));
        queryReferralCaseDetailResponse.setSuperficialVein(DateUtil.format(referralCaseDmo.getSuperficialVein(), DateUtil.yyyyMMddHHmmss));
        queryReferralCaseDetailResponse.setDeepVein(DateUtil.format(referralCaseDmo.getDeepVein(), DateUtil.yyyyMMddHHmmss));
        queryReferralCaseDetailResponse.setPicc(DateUtil.format(referralCaseDmo.getPicc(), DateUtil.yyyyMMddHHmmss));
        queryReferralCaseDetailResponse.setSkinType(referralCaseDmo.getSkinType());
        queryReferralCaseDetailResponse.setSkinDesc(referralCaseDmo.getSkinDesc());

        if (null != video) {
            queryReferralCaseDetailResponse.setVideoId(String.valueOf(video.getResourceId()));
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

        queryReferralCaseDetailResponse.setImageList(imageItemList);

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

        queryReferralCaseDetailResponse.setNihssList(nihssInfoItemList);
        queryReferralCaseDetailResponse.setNihssTotalFee(String.valueOf(referralCaseDmo.getNihssTotalFee()));
        queryReferralCaseDetailResponse.setDescription(referralCaseDmo.getPatientSub());
        queryReferralCaseDetailResponse.setMainDesc(referralCaseDmo.getMainDesc());
        queryReferralCaseDetailResponse.setNowIllness(referralCaseDmo.getNowIllness());
        queryReferralCaseDetailResponse.setHistoryIllness(referralCaseDmo.getHisIllness());
        queryReferralCaseDetailResponse.setHealthCheck(referralCaseDmo.getHealthCheck());
        queryReferralCaseDetailResponse.setAssistCheck(referralCaseDmo.getAssistCheck());
        queryReferralCaseDetailResponse.setReadCount(String.valueOf(referralCaseDmo.getReadCount()));
        queryReferralCaseDetailResponse.setReceiveCount(String.valueOf(referralCaseDmo.getReceiveCount()));
        queryReferralCaseDetailResponse.setStatus(referralCaseDmo.getStatus());

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

            queryReferralCaseDetailResponse.setDoctorList(receiveDoctorItemList);

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
            queryReferralCaseDetailResponse.setScreenList(referralScreenItemList);
        }

        Result result = referralReceiveService.hasReceived(recordId,userId);

        if (result.isSuccess()){
            queryReferralCaseDetailResponse.setHasReceived(APPResponseCodeConstants.ReferralHasReceived.YES);
        }else {
            queryReferralCaseDetailResponse.setHasReceived(APPResponseCodeConstants.ReferralHasReceived.NO);
        }

        result = referralCommentService.hasScored(recordId,userId);

        if (result.isSuccess()){
            queryReferralCaseDetailResponse.setHasScored(APPResponseCodeConstants.ReferralHasScored.YES);
        }else {
            queryReferralCaseDetailResponse.setHasScored(APPResponseCodeConstants.ReferralHasScored.NO);
        }

        response.setParameter(queryReferralCaseDetailResponse);

        return response;

    }
}
