package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.QueryReferralCaseDto;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseInfoDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCaseService;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.NihssInfoItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralCaseItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.QueryMyApplyCaseListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.QueryMyApplyCaseListResponse;
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
 * 我的-我的申请列表(doc-0023)处理类
 * Created by wunder on 16/9/10 10:15.
 */
public class QueryMyApplyCaseListProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyApplyCaseListProcessor.class);

    @Autowired
    private ReferralCaseService referralCaseService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyApplyCaseListResponse queryMyApplyCaseListResponse = new QueryMyApplyCaseListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyApplyCaseListRequest queryMyApplyCaseListRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryMyApplyCaseListRequest.class);

        if (null == queryMyApplyCaseListRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyApplyCaseListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryMyApplyCaseListRequest.getUserId());

        int currPage = Integer.parseInt(queryMyApplyCaseListRequest.getCurPage());

        int pageSize = Integer.parseInt(queryMyApplyCaseListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(currPage);
        page.setPageSize(pageSize);

        QueryReferralCaseDto queryReferralCaseDto = new QueryReferralCaseDto();

        queryReferralCaseDto.setUserId(userId);

        List<String> caseStatus = new ArrayList<>();

        caseStatus.add(Constants.ReferralCaseStatus.NOT_REFERRAL);
        caseStatus.add(Constants.ReferralCaseStatus.REFERRALED);

        queryReferralCaseDto.setCaseStatus(caseStatus);

        List<ReferralCaseInfoDto> referralCaseInfoDtoList = referralCaseService.queryMyApplyListByPage(queryReferralCaseDto, page);

        List<ReferralCaseItem> referralCaseItemList = new ArrayList<>();

        ReferralCaseItem referralCaseItem = null;

        if (CollectionUtils.isEmpty(referralCaseInfoDtoList)) {

            queryMyApplyCaseListResponse.setList(referralCaseItemList);
            queryMyApplyCaseListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
            queryMyApplyCaseListResponse.setPageSize(String.valueOf(page.getPageSize()));
            queryMyApplyCaseListResponse.setTotalCount(String.valueOf(page.getCount()));

        }

        for (ReferralCaseInfoDto referralCaseInfoDto : referralCaseInfoDtoList) {

            referralCaseItem = new ReferralCaseItem();

            referralCaseItem.setDoctorId(String.valueOf(referralCaseInfoDto.getUserId()));
            referralCaseItem.setPhoto(referralCaseInfoDto.getAvatar());
            referralCaseItem.setDoctorName(referralCaseInfoDto.getDoctorName());
            referralCaseItem.setHospital(referralCaseInfoDto.getHospital());
            referralCaseItem.setStatus(referralCaseInfoDto.getStatus());
            referralCaseItem.setRecordId(String.valueOf(referralCaseInfoDto.getId()));
            referralCaseItem.setRecordTime(DateUtil.format(referralCaseInfoDto.getCreateTime(), DateUtil.yyyyMMddHHmmss));
            referralCaseItem.setRecordType(referralCaseInfoDto.getCaseRange());
            referralCaseItem.setReferralType(referralCaseInfoDto.getCaseType());
            referralCaseItem.setReferralTitle(referralCaseInfoDto.getTitle());
            referralCaseItem.setPatientAge(String.valueOf(referralCaseInfoDto.getPatientAge()));
            referralCaseItem.setPatientSex(referralCaseInfoDto.getPatientSex());
            referralCaseItem.setTemperature(String.valueOf(referralCaseInfoDto.getTemperature()));
            referralCaseItem.setHeartRate(String.valueOf(referralCaseInfoDto.getHeartRate()));
            referralCaseItem.setBreath(referralCaseInfoDto.getBreath());
            referralCaseItem.setConsciousness(referralCaseInfoDto.getConsciousness());
            referralCaseItem.setHighPressure(String.valueOf(referralCaseInfoDto.getHighPressure()));
            referralCaseItem.setLowPressure(String.valueOf(referralCaseInfoDto.getLowPressure()));
            if (null != referralCaseInfoDto.getNasogastricTube()){
                referralCaseItem.setNasogastricTube(DateUtil.format(referralCaseInfoDto.getNasogastricTube(), DateUtil.yyyyMMddHHmmss));
            }
            if (null != referralCaseInfoDto.getIndwellingCatheter()){
                referralCaseItem.setIndwellingCatheter(DateUtil.format(referralCaseInfoDto.getIndwellingCatheter(), DateUtil.yyyyMMddHHmmss));
            }
            if (null != referralCaseInfoDto.getSuperficialVein()){
                referralCaseItem.setSuperficialVein(DateUtil.format(referralCaseInfoDto.getSuperficialVein(), DateUtil.yyyyMMddHHmmss));
            }
            if (null != referralCaseInfoDto.getDeepVein()){
                referralCaseItem.setDeepVein(DateUtil.format(referralCaseInfoDto.getDeepVein(), DateUtil.yyyyMMddHHmmss));
            }
            if (null != referralCaseInfoDto.getPicc()){
                referralCaseItem.setPicc(DateUtil.format(referralCaseInfoDto.getPicc(), DateUtil.yyyyMMddHHmmss));
            }
            referralCaseItem.setSkinType(referralCaseInfoDto.getSkinType());
            referralCaseItem.setSkinDesc(referralCaseInfoDto.getSkinDesc());

            if (StringUtils.isNotBlank(referralCaseInfoDto.getNihssIndexList())&&StringUtils.isNotBlank(referralCaseInfoDto.getNihssResultList())){

                String[] nihssIndexList = referralCaseInfoDto.getNihssIndexList().split(",");

                String[] nihssResultList = referralCaseInfoDto.getNihssResultList().split(",");

                if (nihssIndexList.length == nihssResultList.length) {

                    List<NihssInfoItem> nihssInfoItemList = new ArrayList<>();

                    NihssInfoItem nihssInfoItem = null;

                    for (int i = 0; i < nihssIndexList.length; i++){

                        nihssInfoItem = new NihssInfoItem();

                        nihssInfoItem.setIndex(nihssIndexList[i]);
                        nihssInfoItem.setFee(nihssResultList[i]);

                        nihssInfoItemList.add(nihssInfoItem);

                    }

                    referralCaseItem.setNihssList(nihssInfoItemList);

                }

            }

            referralCaseItem.setNihssTotalFee(String.valueOf(referralCaseInfoDto.getNihssTotalFee()));
            referralCaseItem.setDescription(referralCaseInfoDto.getPatientSub());
            referralCaseItem.setMainDesc(referralCaseInfoDto.getMainDesc());
            referralCaseItem.setNowIllness(referralCaseInfoDto.getNowIllness());
            referralCaseItem.setHistoryIllness(referralCaseInfoDto.getHisIllness());
            referralCaseItem.setHealthCheck(referralCaseInfoDto.getHealthCheck());
            referralCaseItem.setAssistCheck(referralCaseInfoDto.getAssistCheck());

            if(StringUtils.isNotBlank(referralCaseInfoDto.getImageList())){

                String[] imageList = referralCaseInfoDto.getImageList().split(",");

                List<ImageItem> imageItemList = new ArrayList<>();

                ImageItem imageItem = null;

                for (String imageId : imageList) {

                    imageItem = new ImageItem();

                    imageItem.setImageId(imageId);

                    imageItemList.add(imageItem);
                }

                referralCaseItem.setImageList(imageItemList);
            }

            referralCaseItem.setReadCount(String.valueOf(referralCaseInfoDto.getReadCount()));
            referralCaseItem.setReceiveCount(String.valueOf(referralCaseInfoDto.getReceiveCount()));

            referralCaseItemList.add(referralCaseItem);
        }

        queryMyApplyCaseListResponse.setList(referralCaseItemList);
        queryMyApplyCaseListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
        queryMyApplyCaseListResponse.setPageSize(String.valueOf(page.getPageSize()));
        queryMyApplyCaseListResponse.setTotalCount(String.valueOf(page.getCount()));

        response.setParameter(queryMyApplyCaseListResponse);

        return response;
    }
}
