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
import com.yh.apoplexy.doctor.open.request.cases.discuss.QueryMyCollectCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.QueryMyCollectCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我收藏的详情(doc-0018)处理类
 * Created by wunder on 16/9/8 00:40.
 */
public class QueryMyCollectCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyCollectCaseDetailProcessor.class);

    @Autowired
    private DiscussCaseService discussCaseService;

    @Autowired
    private CaseLikeService caseLikeService;

    @Autowired
    private DiscussCaseCollectService discussCaseCollectService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyCollectCaseDetailResponse queryMyCollectCaseDetailResponse = new QueryMyCollectCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyCollectCaseDetailRequest queryMyCollectCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryMyCollectCaseDetailRequest.class);

        if (null == queryMyCollectCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyCollectCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryMyCollectCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryMyCollectCaseDetailRequest.getUserId());

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

        queryMyCollectCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryMyCollectCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryMyCollectCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryMyCollectCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryMyCollectCaseDetailResponse.setRecordId(String.valueOf(discussCaseDmo.getId()));
        queryMyCollectCaseDetailResponse.setRecordTime(DateUtil.format(discussCaseDmo.getCreateTime(), DateUtil.yyyyMMddHHmmss));
        queryMyCollectCaseDetailResponse.setRecordType(discussCaseDmo.getCaseRange());
        queryMyCollectCaseDetailResponse.setDiscussTopic(discussCaseDmo.getType());
        queryMyCollectCaseDetailResponse.setPatientAge(String.valueOf(discussCaseDmo.getPatientAge()));
        queryMyCollectCaseDetailResponse.setPatientSex(String.valueOf(discussCaseDmo.getPatientSex()));
        queryMyCollectCaseDetailResponse.setDescription(discussCaseDmo.getDescription());
        queryMyCollectCaseDetailResponse.setMainDesc(discussCaseDmo.getMainDesc());
        queryMyCollectCaseDetailResponse.setNowIllness(discussCaseDmo.getNowIllness());
        queryMyCollectCaseDetailResponse.setHistoryIllness(discussCaseDmo.getHisIllness());
        queryMyCollectCaseDetailResponse.setHealthCheck(discussCaseDmo.getHealthCheck());
        queryMyCollectCaseDetailResponse.setAssistCheck(discussCaseDmo.getAssistCheck());

        if (null != video) {
            queryMyCollectCaseDetailResponse.setVideoId(String.valueOf(video.getResourceId()));
        }

        List<ImageItem> imageItemList = new ArrayList<>();

        ImageItem imageItem = null;

        if (!CollectionUtils.isEmpty(imageList)) {

            for (CaseResourceDmo image : imageList) {

                imageItem = new ImageItem();

                imageItem.setImageId(String.valueOf(image.getResourceId()));

                imageItemList.add(imageItem);

            }

            queryMyCollectCaseDetailResponse.setImageList(imageItemList);

        }

        queryMyCollectCaseDetailResponse.setQuestion(discussCaseDmo.getMainQuestion());
        queryMyCollectCaseDetailResponse.setReadCount(String.valueOf(discussCaseDmo.getReadCount()));
        queryMyCollectCaseDetailResponse.setCommentCount(String.valueOf(discussCaseDmo.getCommentCount()));
        queryMyCollectCaseDetailResponse.setLikeCount(String.valueOf(discussCaseDmo.getLikeCount()));
        queryMyCollectCaseDetailResponse.setCollectionCount(String.valueOf(discussCaseDmo.getCollectionCount()));

        CaseLikeDmo caseLikeDmo = new CaseLikeDmo();

        caseLikeDmo.setRecordId(recordId);
        caseLikeDmo.setUserId(userId);

        Result result = caseLikeService.hasLike(caseLikeDmo);

        if (result.isSuccess()){
            queryMyCollectCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.YES);
        }else {
            queryMyCollectCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.NO);
        }

        CaseCollectionDmo caseCollectionDmo = new CaseCollectionDmo();

        caseCollectionDmo.setRecordId(recordId);
        caseCollectionDmo.setUserId(userId);

        result = discussCaseCollectService.hasCollect(caseCollectionDmo);

        if (result.isSuccess()){
            queryMyCollectCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.YES);
        }else {
            queryMyCollectCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.NO);
        }

        response.setParameter(queryMyCollectCaseDetailResponse);

        return response;
    }
}
