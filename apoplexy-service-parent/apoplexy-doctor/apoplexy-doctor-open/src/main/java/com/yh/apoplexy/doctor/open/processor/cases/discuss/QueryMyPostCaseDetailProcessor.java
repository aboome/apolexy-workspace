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
import com.yh.apoplexy.doctor.open.request.cases.discuss.QueryMyPostCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.QueryMyPostCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我发布的详情(doc-0013)处理类
 * Created by wunder on 16/9/8 00:25.
 */
public class QueryMyPostCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyPostCaseDetailProcessor.class);

    @Autowired
    private DiscussCaseService discussCaseService;

    @Autowired
    private CaseLikeService caseLikeService;

    @Autowired
    private DiscussCaseCollectService discussCaseCollectService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyPostCaseDetailResponse queryMyPostCaseDetailResponse = new QueryMyPostCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyPostCaseDetailRequest queryMyPostCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryMyPostCaseDetailRequest.class);

        if (null == queryMyPostCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyPostCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryMyPostCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryMyPostCaseDetailRequest.getUserId());

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

        queryMyPostCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryMyPostCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryMyPostCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryMyPostCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryMyPostCaseDetailResponse.setRecordId(String.valueOf(discussCaseDmo.getId()));
        queryMyPostCaseDetailResponse.setRecordTime(DateUtil.format(discussCaseDmo.getCreateTime(), DateUtil.yyyyMMddHHmmss));
        queryMyPostCaseDetailResponse.setRecordType(discussCaseDmo.getCaseRange());
        queryMyPostCaseDetailResponse.setDiscussTopic(discussCaseDmo.getType());
        queryMyPostCaseDetailResponse.setPatientAge(String.valueOf(discussCaseDmo.getPatientAge()));
        queryMyPostCaseDetailResponse.setPatientSex(String.valueOf(discussCaseDmo.getPatientSex()));
        queryMyPostCaseDetailResponse.setDescription(discussCaseDmo.getDescription());
        queryMyPostCaseDetailResponse.setMainDesc(discussCaseDmo.getMainDesc());
        queryMyPostCaseDetailResponse.setNowIllness(discussCaseDmo.getNowIllness());
        queryMyPostCaseDetailResponse.setHistoryIllness(discussCaseDmo.getHisIllness());
        queryMyPostCaseDetailResponse.setHealthCheck(discussCaseDmo.getHealthCheck());
        queryMyPostCaseDetailResponse.setAssistCheck(discussCaseDmo.getAssistCheck());

        if (null != video) {
            queryMyPostCaseDetailResponse.setVideoId(String.valueOf(video.getResourceId()));
        }

        List<ImageItem> imageItemList = new ArrayList<>();

        ImageItem imageItem = null;

        if (!CollectionUtils.isEmpty(imageList)) {

            for (CaseResourceDmo image : imageList) {

                imageItem = new ImageItem();

                imageItem.setImageId(String.valueOf(image.getResourceId()));

                imageItemList.add(imageItem);

            }

            queryMyPostCaseDetailResponse.setImageList(imageItemList);

        }

        queryMyPostCaseDetailResponse.setQuestion(discussCaseDmo.getMainQuestion());
        queryMyPostCaseDetailResponse.setReadCount(String.valueOf(discussCaseDmo.getReadCount()));
        queryMyPostCaseDetailResponse.setCommentCount(String.valueOf(discussCaseDmo.getCommentCount()));
        queryMyPostCaseDetailResponse.setLikeCount(String.valueOf(discussCaseDmo.getLikeCount()));
        queryMyPostCaseDetailResponse.setCollectionCount(String.valueOf(discussCaseDmo.getCollectionCount()));

        CaseLikeDmo caseLikeDmo = new CaseLikeDmo();

        caseLikeDmo.setRecordId(recordId);
        caseLikeDmo.setUserId(userId);

        Result result = caseLikeService.hasLike(caseLikeDmo);

        if (result.isSuccess()){
            queryMyPostCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.YES);
        }else {
            queryMyPostCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.NO);
        }

        CaseCollectionDmo caseCollectionDmo = new CaseCollectionDmo();

        caseCollectionDmo.setRecordId(recordId);
        caseCollectionDmo.setUserId(userId);

        result = discussCaseCollectService.hasCollect(caseCollectionDmo);

        if (result.isSuccess()){
            queryMyPostCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.YES);
        }else {
            queryMyPostCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.NO);
        }

        response.setParameter(queryMyPostCaseDetailResponse);

        return response;
    }
}
