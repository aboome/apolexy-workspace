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
import com.yh.apoplexy.doctor.open.processor.cases.discuss.QueryDiscussCaseListProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.QueryAstCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.QueryAstCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询AST病例详情(doc-0037)处理类
 * Created by wunder on 16/9/11 12:18.
 */
public class QueryAstCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDiscussCaseListProcessor.class);

    @Autowired
    private AstCaseService astCaseService;

    @Autowired
    private AstLikeService astLikeService;

    @Autowired
    private AstCaseCollectService astCaseCollectService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryAstCaseDetailResponse queryAstCaseDetailResponse = new QueryAstCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryAstCaseDetailRequest queryAstCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryAstCaseDetailRequest.class);

        if (null == queryAstCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryAstCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryAstCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryAstCaseDetailRequest.getUserId());

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

        queryAstCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryAstCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryAstCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryAstCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryAstCaseDetailResponse.setRecordId(String.valueOf(astCasesDmo.getId()));
        queryAstCaseDetailResponse.setRecordId(String.valueOf(astCasesDmo.getId()));
        if (null != astCasesDmo.getCreateTime()){
            queryAstCaseDetailResponse.setRecordTime(DateUtil.format(astCasesDmo.getCreateTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryAstCaseDetailResponse.setPatientName(astCasesDmo.getPatientName());
        queryAstCaseDetailResponse.setPatientAge(String.valueOf(astCasesDmo.getPatientAge()));
        if (null != astCasesDmo.getPatientBirthday()){
            queryAstCaseDetailResponse.setPatientBirthday(DateUtil.format(astCasesDmo.getPatientBirthday(),DateUtil.yyyyMMddHHmmss));
        }
        queryAstCaseDetailResponse.setPatientSex(astCasesDmo.getPatientSex());
        if (null != astCasesDmo.getOnsetTime()){
            queryAstCaseDetailResponse.setOnsetTime(DateUtil.format(astCasesDmo.getOnsetTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryAstCaseDetailResponse.setMrs(astCasesDmo.getMrs());
        queryAstCaseDetailResponse.setNihssFee(String.valueOf(astCasesDmo.getNihssTotalFee()));
        queryAstCaseDetailResponse.setWeight(String.valueOf(astCasesDmo.getWeight()));
        queryAstCaseDetailResponse.setSmock(astCasesDmo.getSmock());
        queryAstCaseDetailResponse.setPregnancy(astCasesDmo.getPregnancy());
//            queryAstCaseDetailResponse.setHisMedication();
        queryAstCaseDetailResponse.setHighPressure(String.valueOf(astCasesDmo.getHighPressure()));
        queryAstCaseDetailResponse.setLowPressure(String.valueOf(astCasesDmo.getLowPressure()));
        queryAstCaseDetailResponse.setBloodSugar(String.valueOf(astCasesDmo.getBloodSugar()));
        queryAstCaseDetailResponse.setPlatelet(String.valueOf(astCasesDmo.getPlatelet()));
        queryAstCaseDetailResponse.setInr(String.valueOf(astCasesDmo.getInr()));
        queryAstCaseDetailResponse.setPt(String.valueOf(astCasesDmo.getPt()));
        queryAstCaseDetailResponse.setAptt(String.valueOf(astCasesDmo.getAptt()));
        queryAstCaseDetailResponse.setEct(String.valueOf(astCasesDmo.getEct()));
        queryAstCaseDetailResponse.setDtt(String.valueOf(astCasesDmo.getDtt()));

        if (!CollectionUtils.isEmpty(nihssList)){

            List<NihssInfoItem> nihssItemList = new ArrayList<>();

            NihssInfoItem nihssItem = null;

            for (NihssDetailDmo nihssDetailDmo : nihssList){

                nihssItem = new NihssInfoItem();

                nihssItem.setIndex(String.valueOf(nihssDetailDmo.getDetailIndex()));
                nihssItem.setFee(String.valueOf(nihssDetailDmo.getResult()));

                nihssItemList.add(nihssItem);
            }

            queryAstCaseDetailResponse.setNihssList(nihssItemList);

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

        queryAstCaseDetailResponse.setEmergencyIndex(astCasesDmo.getEmergencyIndex());
        queryAstCaseDetailResponse.setEmergencyDesc(astCasesDmo.getEmergencyDesc());
        queryAstCaseDetailResponse.setVeinThrombosis(astCasesDmo.getVeinThrombosis());
        if (null != astCasesDmo.getArriveHospitalTime()){
            queryAstCaseDetailResponse.setArriveHospitalTime(DateUtil.format(astCasesDmo.getArriveHospitalTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getCallTime()){
            queryAstCaseDetailResponse.setCallTime(DateUtil.format(astCasesDmo.getCallTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getThrombolysisArriveTime()){
            queryAstCaseDetailResponse.setThrombolysisArriveTime(DateUtil.format(astCasesDmo.getThrombolysisArriveTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (null != astCasesDmo.getThrombolysisBeginTime()){
            queryAstCaseDetailResponse.setThrombolysisBeginTime(DateUtil.format(astCasesDmo.getThrombolysisBeginTime(),DateUtil.yyyyMMddHHmmss));
        }
        queryAstCaseDetailResponse.setDnt(String.valueOf(astCasesDmo.getDnt()));
        queryAstCaseDetailResponse.setNegativeReason(astCasesDmo.getNegativeReason());
        queryAstCaseDetailResponse.setNegativeReasonDesc(astCasesDmo.getNegativeReasonDesc());
        queryAstCaseDetailResponse.setMultiImage(astCasesDmo.getMultiImage());

        if (!CollectionUtils.isEmpty(ctList)){

            List<ImageItem> ctItemList = new ArrayList<>();

            ImageItem ctItem = null;

            for (AstResourcesDmo astResourcesDmo : ctList){

                ctItem = new ImageItem();

                ctItem.setImageId(astResourcesDmo.getImageId());

                ctItemList.add(ctItem);
            }

            queryAstCaseDetailResponse.setCtList(ctItemList);

        }

        if (!CollectionUtils.isEmpty(ctaList)){

            List<ImageItem> ctaItemList = new ArrayList<>();

            ImageItem ctaItem = null;

            for (AstResourcesDmo astResourcesDmo : ctaList){

                ctaItem = new ImageItem();

                ctaItem.setImageId(astResourcesDmo.getImageId());

                ctaItemList.add(ctaItem);
            }

            queryAstCaseDetailResponse.setCtaList(ctaItemList);

        }

        if (!CollectionUtils.isEmpty(ctpList)){

            List<ImageItem> ctpItemList = new ArrayList<>();

            ImageItem ctpItem = null;

            for (AstResourcesDmo astResourcesDmo : ctpList){

                ctpItem = new ImageItem();

                ctpItem.setImageId(astResourcesDmo.getImageId());

                ctpItemList.add(ctpItem);
            }

            queryAstCaseDetailResponse.setCtpList(ctpItemList);

        }

        queryAstCaseDetailResponse.setMultiImageNegaticeDesc(astCasesDmo.getMultiImageNegativeDesc());
        queryAstCaseDetailResponse.setMacroangiopathy(astCasesDmo.getMacroangiopathy());
        queryAstCaseDetailResponse.setMacroangiopathyDesc(astCasesDmo.getMacroangiopathyDesc());
        queryAstCaseDetailResponse.setMacroangiopathyReason(astCasesDmo.getMacroangiopathyReason());
        queryAstCaseDetailResponse.setPhoneOne(astCasesDmo.getPhoneOne());
        queryAstCaseDetailResponse.setPhoneTwo(astCasesDmo.getPhoneTwo());
        queryAstCaseDetailResponse.setAmbulanceGo(astCasesDmo.getAmbulanceGo());
        queryAstCaseDetailResponse.setFollowGo(astCasesDmo.getFollowGo());
        queryAstCaseDetailResponse.setInpatientNo(astCasesDmo.getInpatientNo());
        queryAstCaseDetailResponse.setFollowGoWhere(astCasesDmo.getFollowGoWhere());
        queryAstCaseDetailResponse.setReadCount(String.valueOf(astCasesDmo.getReadCount()));
        queryAstCaseDetailResponse.setCommentCount(String.valueOf(astCasesDmo.getCommentCount()));
        queryAstCaseDetailResponse.setLikeCount(String.valueOf(astCasesDmo.getLikeCount()));
        queryAstCaseDetailResponse.setCollectionCount(String.valueOf(astCasesDmo.getCollectionCount()));

        AstLikeDmo astLikeDmo = new AstLikeDmo();

        astLikeDmo.setRecordId(recordId);
        astLikeDmo.setUserId(userId);

        Result result = astLikeService.hasLike(astLikeDmo);

        if (result.isSuccess()){
            queryAstCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.YES);
        }else {
            queryAstCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.NO);
        }

        AstCollectionDmo astCollectionDmo = new AstCollectionDmo();

        astCollectionDmo.setRecordId(recordId);
        astCollectionDmo.setUserId(userId);

        result = astCaseCollectService.hasCollect(astCollectionDmo);

        if (result.isSuccess()){
            queryAstCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.YES);
        }else {
            queryAstCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.NO);
        }

        response.setParameter(queryAstCaseDetailResponse);

        return response;
    }
}
