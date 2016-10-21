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
import com.yh.apoplexy.doctor.open.processor.cases.discuss.QueryMyCollectCaseDetailProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.QueryMyCollectAstCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.QueryMyCollectAstCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我收藏的详情(doc-0048)处理类
 * Created by wunder on 16/9/11 21:23.
 */
public class QueryMyCollectAstCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyCollectAstCaseDetailProcessor.class);

    @Autowired
    private AstCaseService astCaseService;

    @Autowired
    private AstLikeService astLikeService;

    @Autowired
    private AstCaseCollectService astCaseCollectService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyCollectAstCaseDetailResponse queryMyCollectAstCaseDetailResponse = new QueryMyCollectAstCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyCollectAstCaseDetailRequest queryMyCollectAstCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryMyCollectAstCaseDetailRequest.class);

        if (null == queryMyCollectAstCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyCollectAstCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryMyCollectAstCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryMyCollectAstCaseDetailRequest.getUserId());

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

        queryMyCollectAstCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryMyCollectAstCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryMyCollectAstCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryMyCollectAstCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryMyCollectAstCaseDetailResponse.setRecordId(String.valueOf(astCasesDmo.getId()));
        queryMyCollectAstCaseDetailResponse.setRecordId(String.valueOf(astCasesDmo.getId()));
        if (null != astCasesDmo.getCreateTime()){
            queryMyCollectAstCaseDetailResponse.setRecordTime(DateUtil.format(astCasesDmo.getCreateTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyCollectAstCaseDetailResponse.setPatientName(astCasesDmo.getPatientName());
        queryMyCollectAstCaseDetailResponse.setPatientAge(String.valueOf(astCasesDmo.getPatientAge()));
        if (null != astCasesDmo.getPatientBirthday()){
            queryMyCollectAstCaseDetailResponse.setPatientBirthday(DateUtil.format(astCasesDmo.getPatientBirthday(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyCollectAstCaseDetailResponse.setPatientSex(astCasesDmo.getPatientSex());
        if (null != astCasesDmo.getOnsetTime()){
            queryMyCollectAstCaseDetailResponse.setOnsetTime(DateUtil.format(astCasesDmo.getOnsetTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyCollectAstCaseDetailResponse.setMrs(astCasesDmo.getMrs());
        queryMyCollectAstCaseDetailResponse.setNihssFee(String.valueOf(astCasesDmo.getNihssTotalFee()));
        queryMyCollectAstCaseDetailResponse.setWeight(String.valueOf(astCasesDmo.getWeight()));
        queryMyCollectAstCaseDetailResponse.setSmock(astCasesDmo.getSmock());
        queryMyCollectAstCaseDetailResponse.setPregnancy(astCasesDmo.getPregnancy());
//            queryMyCollectAstCaseDetailResponse.setHisMedication();
        queryMyCollectAstCaseDetailResponse.setHighPressure(String.valueOf(astCasesDmo.getHighPressure()));
        queryMyCollectAstCaseDetailResponse.setLowPressure(String.valueOf(astCasesDmo.getLowPressure()));
        queryMyCollectAstCaseDetailResponse.setBloodSugar(String.valueOf(astCasesDmo.getBloodSugar()));
        queryMyCollectAstCaseDetailResponse.setPlatelet(String.valueOf(astCasesDmo.getPlatelet()));
        queryMyCollectAstCaseDetailResponse.setInr(String.valueOf(astCasesDmo.getInr()));
        queryMyCollectAstCaseDetailResponse.setPt(String.valueOf(astCasesDmo.getPt()));
        queryMyCollectAstCaseDetailResponse.setAptt(String.valueOf(astCasesDmo.getAptt()));
        queryMyCollectAstCaseDetailResponse.setEct(String.valueOf(astCasesDmo.getEct()));
        queryMyCollectAstCaseDetailResponse.setDtt(String.valueOf(astCasesDmo.getDtt()));

        if (!CollectionUtils.isEmpty(nihssList)){

            List<NihssInfoItem> nihssItemList = new ArrayList<>();

            NihssInfoItem nihssItem = null;

            for (NihssDetailDmo nihssDetailDmo : nihssList){

                nihssItem = new NihssInfoItem();

                nihssItem.setIndex(String.valueOf(nihssDetailDmo.getDetailIndex()));
                nihssItem.setFee(String.valueOf(nihssDetailDmo.getResult()));

                nihssItemList.add(nihssItem);
            }

            queryMyCollectAstCaseDetailResponse.setNihssList(nihssItemList);

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

        queryMyCollectAstCaseDetailResponse.setEmergencyIndex(astCasesDmo.getEmergencyIndex());
        queryMyCollectAstCaseDetailResponse.setEmergencyDesc(astCasesDmo.getEmergencyDesc());
        queryMyCollectAstCaseDetailResponse.setVeinThrombosis(astCasesDmo.getVeinThrombosis());
        if (null != astCasesDmo.getArriveHospitalTime()){
            queryMyCollectAstCaseDetailResponse.setArriveHospitalTime(DateUtil.format(astCasesDmo.getArriveHospitalTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getCallTime()){
            queryMyCollectAstCaseDetailResponse.setCallTime(DateUtil.format(astCasesDmo.getCallTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getThrombolysisArriveTime()){
            queryMyCollectAstCaseDetailResponse.setThrombolysisArriveTime(DateUtil.format(astCasesDmo.getThrombolysisArriveTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getThrombolysisBeginTime()){
            queryMyCollectAstCaseDetailResponse.setThrombolysisBeginTime(DateUtil.format(astCasesDmo.getThrombolysisBeginTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryMyCollectAstCaseDetailResponse.setDnt(String.valueOf(astCasesDmo.getDnt()));
        queryMyCollectAstCaseDetailResponse.setNegativeReason(astCasesDmo.getNegativeReason());
        queryMyCollectAstCaseDetailResponse.setNegativeReasonDesc(astCasesDmo.getNegativeReasonDesc());
        queryMyCollectAstCaseDetailResponse.setMultiImage(astCasesDmo.getMultiImage());

        if (!CollectionUtils.isEmpty(ctList)){

            List<ImageItem> ctItemList = new ArrayList<>();

            ImageItem ctItem = null;

            for (AstResourcesDmo astResourcesDmo : ctList){

                ctItem = new ImageItem();

                ctItem.setImageId(astResourcesDmo.getImageId());

                ctItemList.add(ctItem);
            }

            queryMyCollectAstCaseDetailResponse.setCtList(ctItemList);

        }

        if (!CollectionUtils.isEmpty(ctaList)){

            List<ImageItem> ctaItemList = new ArrayList<>();

            ImageItem ctaItem = null;

            for (AstResourcesDmo astResourcesDmo : ctaList){

                ctaItem = new ImageItem();

                ctaItem.setImageId(astResourcesDmo.getImageId());

                ctaItemList.add(ctaItem);
            }

            queryMyCollectAstCaseDetailResponse.setCtaList(ctaItemList);

        }

        if (!CollectionUtils.isEmpty(ctpList)){

            List<ImageItem> ctpItemList = new ArrayList<>();

            ImageItem ctpItem = null;

            for (AstResourcesDmo astResourcesDmo : ctpList){

                ctpItem = new ImageItem();

                ctpItem.setImageId(astResourcesDmo.getImageId());

                ctpItemList.add(ctpItem);
            }

            queryMyCollectAstCaseDetailResponse.setCtpList(ctpItemList);

        }

        queryMyCollectAstCaseDetailResponse.setMultiImageNegaticeDesc(astCasesDmo.getMultiImageNegativeDesc());
        queryMyCollectAstCaseDetailResponse.setMacroangiopathy(astCasesDmo.getMacroangiopathy());
        queryMyCollectAstCaseDetailResponse.setMacroangiopathyDesc(astCasesDmo.getMacroangiopathyDesc());
        queryMyCollectAstCaseDetailResponse.setMacroangiopathyReason(astCasesDmo.getMacroangiopathyReason());
        queryMyCollectAstCaseDetailResponse.setPhoneOne(astCasesDmo.getPhoneOne());
        queryMyCollectAstCaseDetailResponse.setPhoneTwo(astCasesDmo.getPhoneTwo());
        queryMyCollectAstCaseDetailResponse.setAmbulanceGo(astCasesDmo.getAmbulanceGo());
        queryMyCollectAstCaseDetailResponse.setFollowGo(astCasesDmo.getFollowGo());
        queryMyCollectAstCaseDetailResponse.setInpatientNo(astCasesDmo.getInpatientNo());
        queryMyCollectAstCaseDetailResponse.setFollowGoWhere(astCasesDmo.getFollowGoWhere());
        queryMyCollectAstCaseDetailResponse.setReadCount(String.valueOf(astCasesDmo.getReadCount()));
        queryMyCollectAstCaseDetailResponse.setCommentCount(String.valueOf(astCasesDmo.getCommentCount()));
        queryMyCollectAstCaseDetailResponse.setLikeCount(String.valueOf(astCasesDmo.getLikeCount()));
        queryMyCollectAstCaseDetailResponse.setCollectionCount(String.valueOf(astCasesDmo.getCollectionCount()));

        AstLikeDmo astLikeDmo = new AstLikeDmo();

        astLikeDmo.setRecordId(recordId);
        astLikeDmo.setUserId(userId);

        Result result = astLikeService.hasLike(astLikeDmo);

        if (result.isSuccess()){
            queryMyCollectAstCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.YES);
        }else {
            queryMyCollectAstCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.NO);
        }

        AstCollectionDmo astCollectionDmo = new AstCollectionDmo();

        astCollectionDmo.setRecordId(recordId);
        astCollectionDmo.setUserId(userId);

        result = astCaseCollectService.hasCollect(astCollectionDmo);

        if (result.isSuccess()){
            queryMyCollectAstCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.YES);
        }else {
            queryMyCollectAstCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.NO);
        }


        response.setParameter(queryMyCollectAstCaseDetailResponse);

        return response;
    }
}
