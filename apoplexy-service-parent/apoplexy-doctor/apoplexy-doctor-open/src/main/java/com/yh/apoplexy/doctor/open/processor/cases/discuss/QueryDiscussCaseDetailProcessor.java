package com.yh.apoplexy.doctor.open.processor.cases.discuss;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCollectionDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseLikeDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.DiscussCaseDetailDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.CaseLikeService;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseCollectService;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseService;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.discuss.QueryDiscussCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.QueryDiscussCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询病例讨论详情(doc-0007)处理类
 * Created by wunder on 16/9/7 16:02.
 */
public class QueryDiscussCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDiscussCaseDetailProcessor.class);

    @Autowired
    private DiscussCaseService discussCaseService;

    @Autowired
    private CaseLikeService caseLikeService;

    @Autowired
    private DiscussCaseCollectService discussCaseCollectService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryDiscussCaseDetailResponse queryDiscussCaseDetailResponse = new QueryDiscussCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryDiscussCaseDetailRequest queryDiscussCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryDiscussCaseDetailRequest.class);

        if (null == queryDiscussCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryDiscussCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryDiscussCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryDiscussCaseDetailRequest.getUserId());

        DiscussCaseDmo con = new DiscussCaseDmo();

        con.setId(recordId);
        con.setStatus(DoctorConstants.CaseStatus.NORMAL);

        DiscussCaseDetailDto discussCaseDetailDto = discussCaseService.findDiscussCaseDetail(con);

        if (null == discussCaseDetailDto || null == discussCaseDetailDto.getDoctorMemberDmo()) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        DoctorMemberDmo doctorMemberDmo = discussCaseDetailDto.getDoctorMemberDmo();

        DiscussCaseDmo discussCaseDmo = discussCaseDetailDto.getDiscussCaseDmo();

        CaseResourceDmo video = discussCaseDetailDto.getVideo();

        List<CaseResourceDmo> imageList = discussCaseDetailDto.getImageList();

        queryDiscussCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryDiscussCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryDiscussCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryDiscussCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryDiscussCaseDetailResponse.setRecordId(String.valueOf(discussCaseDmo.getId()));
        queryDiscussCaseDetailResponse.setRecordTime(DateUtil.format(discussCaseDmo.getCreateTime(), DateUtil.yyyyMMddHHmmss));
        queryDiscussCaseDetailResponse.setRecordType(discussCaseDmo.getCaseRange());
        queryDiscussCaseDetailResponse.setDiscussTopic(discussCaseDmo.getType());
        queryDiscussCaseDetailResponse.setPatientAge(String.valueOf(discussCaseDmo.getPatientAge()));
        queryDiscussCaseDetailResponse.setPatientSex(String.valueOf(discussCaseDmo.getPatientSex()));
        queryDiscussCaseDetailResponse.setDescription(discussCaseDmo.getDescription());
        queryDiscussCaseDetailResponse.setMainDesc(discussCaseDmo.getMainDesc());
        queryDiscussCaseDetailResponse.setNowIllness(discussCaseDmo.getNowIllness());
        queryDiscussCaseDetailResponse.setHistoryIllness(discussCaseDmo.getHisIllness());
        queryDiscussCaseDetailResponse.setHealthCheck(discussCaseDmo.getHealthCheck());
        queryDiscussCaseDetailResponse.setAssistCheck(discussCaseDmo.getAssistCheck());

        if (null != video) {
            queryDiscussCaseDetailResponse.setVideoId(String.valueOf(video.getResourceId()));
        }

        List<ImageItem> imageItemList = new ArrayList<>();

        ImageItem imageItem = null;

        if (!CollectionUtils.isEmpty(imageList)) {

            for (CaseResourceDmo image : imageList) {

                imageItem = new ImageItem();

                imageItem.setImageId(String.valueOf(image.getResourceId()));

                imageItemList.add(imageItem);

            }

            queryDiscussCaseDetailResponse.setImageList(imageItemList);

        }

        queryDiscussCaseDetailResponse.setQuestion(discussCaseDmo.getMainQuestion());
        queryDiscussCaseDetailResponse.setReadCount(String.valueOf(discussCaseDmo.getReadCount()));
        queryDiscussCaseDetailResponse.setCommentCount(String.valueOf(discussCaseDmo.getCommentCount()));
        queryDiscussCaseDetailResponse.setLikeCount(String.valueOf(discussCaseDmo.getLikeCount()));
        queryDiscussCaseDetailResponse.setCollectionCount(String.valueOf(discussCaseDmo.getCollectionCount()));

        CaseLikeDmo caseLikeDmo = new CaseLikeDmo();

        caseLikeDmo.setRecordId(recordId);
        caseLikeDmo.setUserId(userId);

        Result result = caseLikeService.hasLike(caseLikeDmo);

        if (result.isSuccess()){
            queryDiscussCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.YES);
        }else {
            queryDiscussCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.NO);
        }

        CaseCollectionDmo caseCollectionDmo = new CaseCollectionDmo();

        caseCollectionDmo.setRecordId(recordId);
        caseCollectionDmo.setUserId(userId);

        result = discussCaseCollectService.hasCollect(caseCollectionDmo);

        if (result.isSuccess()){
            queryDiscussCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.YES);
        }else {
            queryDiscussCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.NO);
        }

        response.setParameter(queryDiscussCaseDetailResponse);

        return response;
    }
}
