package com.yh.apoplexy.doctor.open.processor.cases.ast;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseInfoDto;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.QueryAstCaseDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseService;
import com.yh.apoplexy.doctor.open.dto.cases.ast.AstCaseItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.processor.cases.discuss.QueryMyCollectCaseDetailProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.QueryMyCollectAstCaseListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.QueryMyCollectAstCaseListResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我收藏的列表(doc-0047)处理类
 * Created by wunder on 16/9/11 11:55.
 */
public class QueryMyCollectAstCaseListProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyCollectAstCaseDetailProcessor.class);

    @Autowired
    private AstCaseService astCaseService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyCollectAstCaseListResponse queryMyCollectAstCaseListResponse = new QueryMyCollectAstCaseListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyCollectAstCaseListRequest queryMyCollectAstCaseListRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryMyCollectAstCaseListRequest.class);

        if (null == queryMyCollectAstCaseListRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyCollectAstCaseListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryMyCollectAstCaseListRequest.getUserId());

        int currPage = Integer.parseInt(queryMyCollectAstCaseListRequest.getCurPage());

        int pageSize = Integer.parseInt(queryMyCollectAstCaseListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(currPage);
        page.setPageSize(pageSize);

        QueryAstCaseDto queryAstCaseDto = new QueryAstCaseDto();

        queryAstCaseDto.setUserId(userId);

        List<AstCaseInfoDto> astCaseInfoDtoList = astCaseService.queryMyCollectListByPage(queryAstCaseDto,page);

        List<AstCaseItem> astCaseItemList = new ArrayList<>();

        AstCaseItem astCaseItem = null;

        if (CollectionUtils.isEmpty(astCaseInfoDtoList)){

            queryMyCollectAstCaseListResponse.setList(astCaseItemList);
            queryMyCollectAstCaseListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
            queryMyCollectAstCaseListResponse.setPageSize(String.valueOf(page.getPageSize()));
            queryMyCollectAstCaseListResponse.setTotalCount(String.valueOf(page.getCount()));

            response.setParameter(queryMyCollectAstCaseListResponse);
            return response;

        }

        for (AstCaseInfoDto astCaseInfoDto : astCaseInfoDtoList){

            astCaseItem = new AstCaseItem();

            astCaseItem.setDoctorId(String.valueOf(astCaseInfoDto.getUserId()));
            astCaseItem.setPhoto(astCaseInfoDto.getAvatar());
            astCaseItem.setDoctorName(astCaseInfoDto.getDoctorName());
            astCaseItem.setHospital(astCaseInfoDto.getHospital());
            astCaseItem.setRecordId(String.valueOf(astCaseInfoDto.getId()));
            if (null != astCaseInfoDto.getCreateTime()){
                astCaseItem.setRecordTime(DateUtil.format(astCaseInfoDto.getCreateTime(),DateUtil.yyyyMMddHHmmss));
            }
            astCaseItem.setPatientName(astCaseInfoDto.getPatientName());
            astCaseItem.setPatientAge(String.valueOf(astCaseInfoDto.getPatientAge()));
            if (null != astCaseInfoDto.getPatientBirthday()){
                astCaseItem.setPatientBirthday(DateUtil.format(astCaseInfoDto.getPatientBirthday(),DateUtil.yyyyMMddHHmmss));
            }
            astCaseItem.setPatientSex(astCaseInfoDto.getPatientSex());
            if (null != astCaseInfoDto.getOnsetTime()){
                astCaseItem.setOnsetTime(DateUtil.format(astCaseInfoDto.getOnsetTime(),DateUtil.yyyyMMddHHmmss));
            }
            astCaseItem.setMrs(astCaseInfoDto.getMrs());
            astCaseItem.setNihssFee(String.valueOf(astCaseInfoDto.getNihssTotalFee()));
            astCaseItem.setWeight(String.valueOf(astCaseInfoDto.getWeight()));
            astCaseItem.setSmock(astCaseInfoDto.getSmock());
            astCaseItem.setPregnancy(astCaseInfoDto.getPregnancy());
//            astCaseItem.setHisMedication();
            astCaseItem.setHighPressure(String.valueOf(astCaseInfoDto.getHighPressure()));
            astCaseItem.setLowPressure(String.valueOf(astCaseInfoDto.getLowPressure()));
            astCaseItem.setBloodSugar(String.valueOf(astCaseInfoDto.getBloodSugar()));
            astCaseItem.setPlatelet(String.valueOf(astCaseInfoDto.getPlatelet()));
            astCaseItem.setInr(String.valueOf(astCaseInfoDto.getInr()));
            astCaseItem.setPt(String.valueOf(astCaseInfoDto.getPt()));
            astCaseItem.setAptt(String.valueOf(astCaseInfoDto.getAptt()));
            astCaseItem.setEct(String.valueOf(astCaseInfoDto.getEct()));
            astCaseItem.setDtt(String.valueOf(astCaseInfoDto.getDtt()));

            if (StringUtils.isNotBlank(astCaseInfoDto.getCtList())){

                String [] ctList = astCaseInfoDto.getCtList().split(",");

                List<ImageItem> imageItemList = new ArrayList<>();

                ImageItem imageItem = null;

                for (String imageId : ctList){

                    imageItem = new ImageItem();

                    imageItem.setImageId(imageId);

                    imageItemList.add(imageItem);
                }

                astCaseItem.setCtList(imageItemList);

            }

            astCaseItem.setEmergencyIndex(astCaseInfoDto.getEmergencyIndex());
            astCaseItem.setEmergencyDesc(astCaseInfoDto.getEmergencyDesc());
            astCaseItem.setVeinThrombosis(astCaseInfoDto.getVeinThrombosis());
            if (null != astCaseInfoDto.getArriveHospitalTime()){
                astCaseItem.setArriveHospitalTime(DateUtil.format(astCaseInfoDto.getArriveHospitalTime(),DateUtil.yyyyMMddHHmmss));
            }
            if (null != astCaseInfoDto.getCallTime()){
                astCaseItem.setCallTime(DateUtil.format(astCaseInfoDto.getCallTime(),DateUtil.yyyyMMddHHmmss));
            }
            if (null != astCaseInfoDto.getThrombolysisArriveTime()){
                astCaseItem.setThrombolysisArriveTime(DateUtil.format(astCaseInfoDto.getThrombolysisArriveTime(),DateUtil.yyyyMMddHHmmss));
            }
            if (null != astCaseInfoDto.getThrombolysisBeginTime()){
                astCaseItem.setThrombolysisBeginTime(DateUtil.format(astCaseInfoDto.getThrombolysisBeginTime(),DateUtil.yyyyMMddHHmmss));
            }
            astCaseItem.setDnt(String.valueOf(astCaseInfoDto.getDnt()));
            astCaseItem.setNegativeReason(astCaseInfoDto.getNegativeReason());
            astCaseItem.setNegativeReasonDesc(astCaseInfoDto.getNegativeReasonDesc());
            astCaseItem.setMultiImage(astCaseInfoDto.getMultiImage());

            if (StringUtils.isNotBlank(astCaseInfoDto.getCtaList())){

                String [] ctaList = astCaseInfoDto.getCtaList().split(",");

                List<ImageItem> imageItemList = new ArrayList<>();

                ImageItem imageItem = null;

                for (String imageId : ctaList){

                    imageItem = new ImageItem();

                    imageItem.setImageId(imageId);

                    imageItemList.add(imageItem);
                }

                astCaseItem.setCtaList(imageItemList);

            }

            if (StringUtils.isNotBlank(astCaseInfoDto.getCtpList())){

                String [] ctpList = astCaseInfoDto.getCtpList().split(",");

                List<ImageItem> imageItemList = new ArrayList<>();

                ImageItem imageItem = null;

                for (String imageId : ctpList){

                    imageItem = new ImageItem();

                    imageItem.setImageId(imageId);

                    imageItemList.add(imageItem);
                }

                astCaseItem.setCtpList(imageItemList);

            }

            astCaseItem.setMultiImageNegaticeDesc(astCaseInfoDto.getMultiImageNegativeDesc());
            astCaseItem.setMacroangiopathy(astCaseInfoDto.getMacroangiopathy());
            astCaseItem.setMacroangiopathyDesc(astCaseInfoDto.getMacroangiopathyDesc());
            astCaseItem.setMacroangiopathyReason(astCaseInfoDto.getMacroangiopathyReason());
            astCaseItem.setPhoneOne(astCaseInfoDto.getPhoneOne());
            astCaseItem.setPhoneTwo(astCaseInfoDto.getPhoneTwo());
            astCaseItem.setAmbulanceGo(astCaseInfoDto.getAmbulanceGo());
            astCaseItem.setFollowGo(astCaseInfoDto.getFollowGo());
            astCaseItem.setInpatientNo(astCaseInfoDto.getInpatientNo());
            astCaseItem.setFollowGoWhere(astCaseInfoDto.getFollowGoWhere());
            astCaseItem.setReadCount(String.valueOf(astCaseInfoDto.getReadCount()));
            astCaseItem.setCommentCount(String.valueOf(astCaseInfoDto.getCommentCount()));
            astCaseItem.setLikeCount(String.valueOf(astCaseInfoDto.getLikeCount()));
            astCaseItem.setCollectionCount(String.valueOf(astCaseInfoDto.getCollectionCount()));

            astCaseItemList.add(astCaseItem);

        }

        queryMyCollectAstCaseListResponse.setList(astCaseItemList);
        queryMyCollectAstCaseListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
        queryMyCollectAstCaseListResponse.setPageSize(String.valueOf(page.getPageSize()));
        queryMyCollectAstCaseListResponse.setTotalCount(String.valueOf(page.getCount()));

        response.setParameter(queryMyCollectAstCaseListResponse);

        return response;

    }
}
