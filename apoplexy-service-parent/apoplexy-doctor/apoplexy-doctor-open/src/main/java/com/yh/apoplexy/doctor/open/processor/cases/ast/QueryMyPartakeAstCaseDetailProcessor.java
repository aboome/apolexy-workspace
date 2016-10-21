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
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.QueryMyPartakeAstCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.QueryMyPartakeAstCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我参与的详情(doc-0046)处理类
 * Created by wunder on 16/9/11 21:29.
 */
public class QueryMyPartakeAstCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyPartakeAstCaseDetailProcessor.class);

    @Autowired
    private AstCaseService astCaseService;

    @Autowired
    private AstLikeService astLikeService;

    @Autowired
    private AstCaseCollectService astCaseCollectService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyPartakeAstCaseDetailResponse queryMyPartakeAstCaseDetailResponse = new QueryMyPartakeAstCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyPartakeAstCaseDetailRequest queryMyPartakeAstCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryMyPartakeAstCaseDetailRequest.class);

        if (null == queryMyPartakeAstCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyPartakeAstCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryMyPartakeAstCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryMyPartakeAstCaseDetailRequest.getUserId());

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

        queryMyPartakeAstCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryMyPartakeAstCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryMyPartakeAstCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryMyPartakeAstCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryMyPartakeAstCaseDetailResponse.setRecordId(String.valueOf(astCasesDmo.getId()));
        queryMyPartakeAstCaseDetailResponse.setRecordId(String.valueOf(astCasesDmo.getId()));
        if (null != astCasesDmo.getCreateTime()){
            queryMyPartakeAstCaseDetailResponse.setRecordTime(DateUtil.format(astCasesDmo.getCreateTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyPartakeAstCaseDetailResponse.setPatientName(astCasesDmo.getPatientName());
        queryMyPartakeAstCaseDetailResponse.setPatientAge(String.valueOf(astCasesDmo.getPatientAge()));
        if (null != astCasesDmo.getPatientBirthday()){
            queryMyPartakeAstCaseDetailResponse.setPatientBirthday(DateUtil.format(astCasesDmo.getPatientBirthday(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyPartakeAstCaseDetailResponse.setPatientSex(astCasesDmo.getPatientSex());
        if (null != astCasesDmo.getOnsetTime()){
            queryMyPartakeAstCaseDetailResponse.setOnsetTime(DateUtil.format(astCasesDmo.getOnsetTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyPartakeAstCaseDetailResponse.setMrs(astCasesDmo.getMrs());
        queryMyPartakeAstCaseDetailResponse.setNihssFee(String.valueOf(astCasesDmo.getNihssTotalFee()));
        queryMyPartakeAstCaseDetailResponse.setWeight(String.valueOf(astCasesDmo.getWeight()));
        queryMyPartakeAstCaseDetailResponse.setSmock(astCasesDmo.getSmock());
        queryMyPartakeAstCaseDetailResponse.setPregnancy(astCasesDmo.getPregnancy());
//            queryMyPartakeAstCaseDetailResponse.setHisMedication();
        queryMyPartakeAstCaseDetailResponse.setHighPressure(String.valueOf(astCasesDmo.getHighPressure()));
        queryMyPartakeAstCaseDetailResponse.setLowPressure(String.valueOf(astCasesDmo.getLowPressure()));
        queryMyPartakeAstCaseDetailResponse.setBloodSugar(String.valueOf(astCasesDmo.getBloodSugar()));
        queryMyPartakeAstCaseDetailResponse.setPlatelet(String.valueOf(astCasesDmo.getPlatelet()));
        queryMyPartakeAstCaseDetailResponse.setInr(String.valueOf(astCasesDmo.getInr()));
        queryMyPartakeAstCaseDetailResponse.setPt(String.valueOf(astCasesDmo.getPt()));
        queryMyPartakeAstCaseDetailResponse.setAptt(String.valueOf(astCasesDmo.getAptt()));
        queryMyPartakeAstCaseDetailResponse.setEct(String.valueOf(astCasesDmo.getEct()));
        queryMyPartakeAstCaseDetailResponse.setDtt(String.valueOf(astCasesDmo.getDtt()));

        if (!CollectionUtils.isEmpty(nihssList)){

            List<NihssInfoItem> nihssItemList = new ArrayList<>();

            NihssInfoItem nihssItem = null;

            for (NihssDetailDmo nihssDetailDmo : nihssList){

                nihssItem = new NihssInfoItem();

                nihssItem.setIndex(String.valueOf(nihssDetailDmo.getDetailIndex()));
                nihssItem.setFee(String.valueOf(nihssDetailDmo.getResult()));

                nihssItemList.add(nihssItem);
            }

            queryMyPartakeAstCaseDetailResponse.setNihssList(nihssItemList);

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

        queryMyPartakeAstCaseDetailResponse.setEmergencyIndex(astCasesDmo.getEmergencyIndex());
        queryMyPartakeAstCaseDetailResponse.setEmergencyDesc(astCasesDmo.getEmergencyDesc());
        queryMyPartakeAstCaseDetailResponse.setVeinThrombosis(astCasesDmo.getVeinThrombosis());
        if (null != astCasesDmo.getArriveHospitalTime()){
            queryMyPartakeAstCaseDetailResponse.setArriveHospitalTime(DateUtil.format(astCasesDmo.getArriveHospitalTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getCallTime()){
            queryMyPartakeAstCaseDetailResponse.setCallTime(DateUtil.format(astCasesDmo.getCallTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getThrombolysisArriveTime()){
            queryMyPartakeAstCaseDetailResponse.setThrombolysisArriveTime(DateUtil.format(astCasesDmo.getThrombolysisArriveTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getThrombolysisBeginTime()){
            queryMyPartakeAstCaseDetailResponse.setThrombolysisBeginTime(DateUtil.format(astCasesDmo.getThrombolysisBeginTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyPartakeAstCaseDetailResponse.setDnt(String.valueOf(astCasesDmo.getDnt()));
        queryMyPartakeAstCaseDetailResponse.setNegativeReason(astCasesDmo.getNegativeReason());
        queryMyPartakeAstCaseDetailResponse.setNegativeReasonDesc(astCasesDmo.getNegativeReasonDesc());
        queryMyPartakeAstCaseDetailResponse.setMultiImage(astCasesDmo.getMultiImage());

        if (!CollectionUtils.isEmpty(ctList)){

            List<ImageItem> ctItemList = new ArrayList<>();

            ImageItem ctItem = null;

            for (AstResourcesDmo astResourcesDmo : ctList){

                ctItem = new ImageItem();

                ctItem.setImageId(astResourcesDmo.getImageId());

                ctItemList.add(ctItem);
            }

            queryMyPartakeAstCaseDetailResponse.setCtList(ctItemList);

        }

        if (!CollectionUtils.isEmpty(ctaList)){

            List<ImageItem> ctaItemList = new ArrayList<>();

            ImageItem ctaItem = null;

            for (AstResourcesDmo astResourcesDmo : ctaList){

                ctaItem = new ImageItem();

                ctaItem.setImageId(astResourcesDmo.getImageId());

                ctaItemList.add(ctaItem);
            }

            queryMyPartakeAstCaseDetailResponse.setCtaList(ctaItemList);

        }

        if (!CollectionUtils.isEmpty(ctpList)){

            List<ImageItem> ctpItemList = new ArrayList<>();

            ImageItem ctpItem = null;

            for (AstResourcesDmo astResourcesDmo : ctpList){

                ctpItem = new ImageItem();

                ctpItem.setImageId(astResourcesDmo.getImageId());

                ctpItemList.add(ctpItem);
            }

            queryMyPartakeAstCaseDetailResponse.setCtpList(ctpItemList);

        }

        queryMyPartakeAstCaseDetailResponse.setMultiImageNegaticeDesc(astCasesDmo.getMultiImageNegativeDesc());
        queryMyPartakeAstCaseDetailResponse.setMacroangiopathy(astCasesDmo.getMacroangiopathy());
        queryMyPartakeAstCaseDetailResponse.setMacroangiopathyDesc(astCasesDmo.getMacroangiopathyDesc());
        queryMyPartakeAstCaseDetailResponse.setMacroangiopathyReason(astCasesDmo.getMacroangiopathyReason());
        queryMyPartakeAstCaseDetailResponse.setPhoneOne(astCasesDmo.getPhoneOne());
        queryMyPartakeAstCaseDetailResponse.setPhoneTwo(astCasesDmo.getPhoneTwo());
        queryMyPartakeAstCaseDetailResponse.setAmbulanceGo(astCasesDmo.getAmbulanceGo());
        queryMyPartakeAstCaseDetailResponse.setFollowGo(astCasesDmo.getFollowGo());
        queryMyPartakeAstCaseDetailResponse.setInpatientNo(astCasesDmo.getInpatientNo());
        queryMyPartakeAstCaseDetailResponse.setFollowGoWhere(astCasesDmo.getFollowGoWhere());
        queryMyPartakeAstCaseDetailResponse.setReadCount(String.valueOf(astCasesDmo.getReadCount()));
        queryMyPartakeAstCaseDetailResponse.setCommentCount(String.valueOf(astCasesDmo.getCommentCount()));
        queryMyPartakeAstCaseDetailResponse.setLikeCount(String.valueOf(astCasesDmo.getLikeCount()));
        queryMyPartakeAstCaseDetailResponse.setCollectionCount(String.valueOf(astCasesDmo.getCollectionCount()));

        AstLikeDmo astLikeDmo = new AstLikeDmo();

        astLikeDmo.setRecordId(recordId);
        astLikeDmo.setUserId(userId);

        Result result = astLikeService.hasLike(astLikeDmo);

        if (result.isSuccess()){
            queryMyPartakeAstCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.YES);
        }else {
            queryMyPartakeAstCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.NO);
        }

        AstCollectionDmo astCollectionDmo = new AstCollectionDmo();

        astCollectionDmo.setRecordId(recordId);
        astCollectionDmo.setUserId(userId);

        result = astCaseCollectService.hasCollect(astCollectionDmo);

        if (result.isSuccess()){
            queryMyPartakeAstCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.YES);
        }else {
            queryMyPartakeAstCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.NO);
        }

        response.setParameter(queryMyPartakeAstCaseDetailResponse);

        return response;
    }
}
