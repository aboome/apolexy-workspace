package com.yh.apoplexy.doctor.open.processor.cases.ast;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.*;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseCollectService;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseService;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstLikeService;
import com.yh.apoplexy.doctor.open.dto.cases.ast.HisIllnessItem;
import com.yh.apoplexy.doctor.open.dto.cases.ast.HisMedicineItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.NihssInfoItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.processor.cases.discuss.QueryMyPostCaseDetailProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.QueryMyPostAstCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.QueryMyPostAstCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我发布的详情(doc-0043)处理类
 * Created by wunder on 16/9/11 21:35.
 */
public class QueryMyPostAstCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyPostCaseDetailProcessor.class);

    @Autowired
    private AstCaseService astCaseService;

    @Autowired
    private AstLikeService astLikeService;

    @Autowired
    private AstCaseCollectService astCaseCollectService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyPostAstCaseDetailResponse queryMyPostAstCaseDetailResponse = new QueryMyPostAstCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyPostAstCaseDetailRequest queryMyPostAstCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryMyPostAstCaseDetailRequest.class);

        if (null == queryMyPostAstCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyPostAstCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryMyPostAstCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryMyPostAstCaseDetailRequest.getUserId());

        AstCasesDmo con = new AstCasesDmo();

        con.setId(recordId);
        con.setStatus(DoctorConstants.CaseStatus.NORMAL);

        AstCaseDetailDto astCaseDetailDto = astCaseService.findCaseDetail(con);

        if (null == astCaseDetailDto || null == astCaseDetailDto.getDoctorMemberDmo()) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        DoctorMemberDmo doctorMemberDmo = astCaseDetailDto.getDoctorMemberDmo();

        AstCasesDmo astCasesDmo = astCaseDetailDto.getAstCasesDmo();

        List<NihssDetailDmo> nihssList = astCaseDetailDto.getNihssList();

        List<AstHistoryDmo> hisIllnessList = astCaseDetailDto.getHisIllness();

        List<AstMedicationDmo> hisMedicaitionList = astCaseDetailDto.getHisMedicaitionList();

        List<AstResourcesDmo> ctList = astCaseDetailDto.getCtList();

        List<AstResourcesDmo> ctaList = astCaseDetailDto.getCtaList();

        List<AstResourcesDmo> ctpList = astCaseDetailDto.getCtpList();

        queryMyPostAstCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryMyPostAstCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryMyPostAstCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryMyPostAstCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryMyPostAstCaseDetailResponse.setRecordId(String.valueOf(astCasesDmo.getId()));
        queryMyPostAstCaseDetailResponse.setRecordId(String.valueOf(astCasesDmo.getId()));
        if (null != astCasesDmo.getCreateTime()){
            queryMyPostAstCaseDetailResponse.setRecordTime(DateUtil.format(astCasesDmo.getCreateTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyPostAstCaseDetailResponse.setPatientName(astCasesDmo.getPatientName());
        queryMyPostAstCaseDetailResponse.setPatientAge(String.valueOf(astCasesDmo.getPatientAge()));
        if (null != astCasesDmo.getPatientBirthday()){
            queryMyPostAstCaseDetailResponse.setPatientBirthday(DateUtil.format(astCasesDmo.getPatientBirthday(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyPostAstCaseDetailResponse.setPatientSex(astCasesDmo.getPatientSex());
        if (null != astCasesDmo.getOnsetTime()){
            queryMyPostAstCaseDetailResponse.setOnsetTime(DateUtil.format(astCasesDmo.getOnsetTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyPostAstCaseDetailResponse.setMrs(astCasesDmo.getMrs());
        queryMyPostAstCaseDetailResponse.setNihssFee(String.valueOf(astCasesDmo.getNihssTotalFee()));
        queryMyPostAstCaseDetailResponse.setWeight(String.valueOf(astCasesDmo.getWeight()));
        queryMyPostAstCaseDetailResponse.setSmock(astCasesDmo.getSmock());
        queryMyPostAstCaseDetailResponse.setPregnancy(astCasesDmo.getPregnancy());
//            queryMyPostAstCaseDetailResponse.setHisMedication();
        queryMyPostAstCaseDetailResponse.setHighPressure(String.valueOf(astCasesDmo.getHighPressure()));
        queryMyPostAstCaseDetailResponse.setLowPressure(String.valueOf(astCasesDmo.getLowPressure()));
        queryMyPostAstCaseDetailResponse.setBloodSugar(String.valueOf(astCasesDmo.getBloodSugar()));
        queryMyPostAstCaseDetailResponse.setPlatelet(String.valueOf(astCasesDmo.getPlatelet()));
        queryMyPostAstCaseDetailResponse.setInr(String.valueOf(astCasesDmo.getInr()));
        queryMyPostAstCaseDetailResponse.setPt(String.valueOf(astCasesDmo.getPt()));
        queryMyPostAstCaseDetailResponse.setAptt(String.valueOf(astCasesDmo.getAptt()));
        queryMyPostAstCaseDetailResponse.setEct(String.valueOf(astCasesDmo.getEct()));
        queryMyPostAstCaseDetailResponse.setDtt(String.valueOf(astCasesDmo.getDtt()));

        if (!CollectionUtils.isEmpty(nihssList)){

            List<NihssInfoItem> nihssItemList = new ArrayList<>();

            NihssInfoItem nihssItem = null;

            for (NihssDetailDmo nihssDetailDmo : nihssList){

                nihssItem = new NihssInfoItem();

                nihssItem.setIndex(String.valueOf(nihssDetailDmo.getDetailIndex()));
                nihssItem.setFee(String.valueOf(nihssDetailDmo.getResult()));

                nihssItemList.add(nihssItem);
            }

            queryMyPostAstCaseDetailResponse.setNihssList(nihssItemList);

        }

        if (!CollectionUtils.isEmpty(hisIllnessList)){

            List<HisIllnessItem> hisIllnessItemList = new ArrayList<>();

            HisIllnessItem hisIllnessItem = null;

            for (AstHistoryDmo astHistoryDmo: hisIllnessList){

                hisIllnessItem = new HisIllnessItem();

                hisIllnessItem.setIndex(String.valueOf(astHistoryDmo.getDetailIndex()));
                hisIllnessItem.setAnswer(astHistoryDmo.getResult());
                hisIllnessItem.setTime(astHistoryDmo.getDescOne());
                hisIllnessItem.setDesc(astHistoryDmo.getDescTwo());

                hisIllnessItemList.add(hisIllnessItem);

            }

        }

        if (!CollectionUtils.isEmpty(hisMedicaitionList)){

            List<HisMedicineItem> hisMedicineItemList = new ArrayList<>();

            HisMedicineItem hisMedicineItem = null;

            for (AstMedicationDmo astMedicationDmo : hisMedicaitionList){

                hisMedicineItem = new HisMedicineItem();

                hisMedicineItem.setIndex(String.valueOf(astMedicationDmo.getDetailIndex()));
                hisMedicineItem.setUsed(astMedicationDmo.getResult());
                hisMedicineItem.setMedicaitionName(astMedicationDmo.getDescription());


                hisMedicineItemList.add(hisMedicineItem);
            }


        }

        queryMyPostAstCaseDetailResponse.setEmergencyIndex(astCasesDmo.getEmergencyIndex());
        queryMyPostAstCaseDetailResponse.setEmergencyDesc(astCasesDmo.getEmergencyDesc());
        queryMyPostAstCaseDetailResponse.setVeinThrombosis(astCasesDmo.getVeinThrombosis());
        if (null != astCasesDmo.getArriveHospitalTime()){
            queryMyPostAstCaseDetailResponse.setArriveHospitalTime(DateUtil.format(astCasesDmo.getArriveHospitalTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getCallTime()){
            queryMyPostAstCaseDetailResponse.setCallTime(DateUtil.format(astCasesDmo.getCallTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getThrombolysisArriveTime()){
            queryMyPostAstCaseDetailResponse.setThrombolysisArriveTime(DateUtil.format(astCasesDmo.getThrombolysisArriveTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getThrombolysisBeginTime()){
            queryMyPostAstCaseDetailResponse.setThrombolysisBeginTime(DateUtil.format(astCasesDmo.getThrombolysisBeginTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyPostAstCaseDetailResponse.setDnt(String.valueOf(astCasesDmo.getDnt()));
        queryMyPostAstCaseDetailResponse.setNegativeReason(astCasesDmo.getNegativeReason());
        queryMyPostAstCaseDetailResponse.setNegativeReasonDesc(astCasesDmo.getNegativeReasonDesc());
        queryMyPostAstCaseDetailResponse.setMultiImage(astCasesDmo.getMultiImage());

        if (!CollectionUtils.isEmpty(ctList)){

            List<ImageItem> ctItemList = new ArrayList<>();

            ImageItem ctItem = null;

            for (AstResourcesDmo astResourcesDmo : ctList){

                ctItem = new ImageItem();

                ctItem.setImageId(astResourcesDmo.getImageId());

                ctItemList.add(ctItem);
            }

            queryMyPostAstCaseDetailResponse.setCtList(ctItemList);

        }

        if (!CollectionUtils.isEmpty(ctaList)){

            List<ImageItem> ctaItemList = new ArrayList<>();

            ImageItem ctaItem = null;

            for (AstResourcesDmo astResourcesDmo : ctaList){

                ctaItem = new ImageItem();

                ctaItem.setImageId(astResourcesDmo.getImageId());

                ctaItemList.add(ctaItem);
            }

            queryMyPostAstCaseDetailResponse.setCtaList(ctaItemList);

        }

        if (!CollectionUtils.isEmpty(ctpList)){

            List<ImageItem> ctpItemList = new ArrayList<>();

            ImageItem ctpItem = null;

            for (AstResourcesDmo astResourcesDmo : ctpList){

                ctpItem = new ImageItem();

                ctpItem.setImageId(astResourcesDmo.getImageId());

                ctpItemList.add(ctpItem);
            }

            queryMyPostAstCaseDetailResponse.setCtpList(ctpItemList);

        }

        queryMyPostAstCaseDetailResponse.setMultiImageNegaticeDesc(astCasesDmo.getMultiImageNegativeDesc());
        queryMyPostAstCaseDetailResponse.setMacroangiopathy(astCasesDmo.getMacroangiopathy());
        queryMyPostAstCaseDetailResponse.setMacroangiopathyDesc(astCasesDmo.getMacroangiopathyDesc());
        queryMyPostAstCaseDetailResponse.setMacroangiopathyReason(astCasesDmo.getMacroangiopathyReason());
        queryMyPostAstCaseDetailResponse.setPhoneOne(astCasesDmo.getPhoneOne());
        queryMyPostAstCaseDetailResponse.setPhoneTwo(astCasesDmo.getPhoneTwo());
        queryMyPostAstCaseDetailResponse.setAmbulanceGo(astCasesDmo.getAmbulanceGo());
        queryMyPostAstCaseDetailResponse.setFollowGo(astCasesDmo.getFollowGo());
        queryMyPostAstCaseDetailResponse.setInpatientNo(astCasesDmo.getInpatientNo());
        queryMyPostAstCaseDetailResponse.setFollowGoWhere(astCasesDmo.getFollowGoWhere());
        queryMyPostAstCaseDetailResponse.setReadCount(String.valueOf(astCasesDmo.getReadCount()));
        queryMyPostAstCaseDetailResponse.setCommentCount(String.valueOf(astCasesDmo.getCommentCount()));
        queryMyPostAstCaseDetailResponse.setLikeCount(String.valueOf(astCasesDmo.getLikeCount()));
        queryMyPostAstCaseDetailResponse.setCollectionCount(String.valueOf(astCasesDmo.getCollectionCount()));

        AstLikeDmo astLikeDmo = new AstLikeDmo();

        astLikeDmo.setRecordId(recordId);
        astLikeDmo.setUserId(userId);

        Result result = astLikeService.hasLike(astLikeDmo);

        if (result.isSuccess()){
            queryMyPostAstCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.YES);
        }else {
            queryMyPostAstCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.NO);
        }

        AstCollectionDmo astCollectionDmo = new AstCollectionDmo();

        astCollectionDmo.setRecordId(recordId);
        astCollectionDmo.setUserId(userId);

        result = astCaseCollectService.hasCollect(astCollectionDmo);

        if (result.isSuccess()){
            queryMyPostAstCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.YES);
        }else {
            queryMyPostAstCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.NO);
        }


        response.setParameter(queryMyPostAstCaseDetailResponse);

        return response;
    }
}
