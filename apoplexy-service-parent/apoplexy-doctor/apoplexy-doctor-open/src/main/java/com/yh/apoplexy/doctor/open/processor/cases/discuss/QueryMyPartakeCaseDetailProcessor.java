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
import com.yh.apoplexy.doctor.open.request.cases.discuss.QueryMyPartakeCaseDetailRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.QueryMyPartakeCaseDetailResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-我参与的详情(doc-0016)处理类
 * Created by wunder on 16/9/8 00:41.
 */
public class QueryMyPartakeCaseDetailProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyPartakeCaseDetailProcessor.class);

    @Autowired
    private DiscussCaseService discussCaseService;

    @Autowired
    private CaseLikeService caseLikeService;

    @Autowired
    private DiscussCaseCollectService discussCaseCollectService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyPartakeCaseDetailResponse queryMyPartakeCaseDetailResponse = new QueryMyPartakeCaseDetailResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyPartakeCaseDetailRequest queryMyPartakeCaseDetailRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryMyPartakeCaseDetailRequest.class);

        if (null == queryMyPartakeCaseDetailRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyPartakeCaseDetailRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryMyPartakeCaseDetailRequest.getRecordId());

        Long userId = Long.parseLong(queryMyPartakeCaseDetailRequest.getUserId());

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

        queryMyPartakeCaseDetailResponse.setDoctorId(String.valueOf(doctorMemberDmo.getId()));
        queryMyPartakeCaseDetailResponse.setPhoto(doctorMemberDmo.getAvatar());
        queryMyPartakeCaseDetailResponse.setDoctorName(doctorMemberDmo.getDoctorName());
        queryMyPartakeCaseDetailResponse.setHospital(doctorMemberDmo.getHospital());
        queryMyPartakeCaseDetailResponse.setRecordId(String.valueOf(discussCaseDmo.getId()));
        queryMyPartakeCaseDetailResponse.setRecordTime(DateUtil.format(discussCaseDmo.getCreateTime(), DateUtil.yyyyMMddHHmmss));
        queryMyPartakeCaseDetailResponse.setRecordType(discussCaseDmo.getCaseRange());
        queryMyPartakeCaseDetailResponse.setDiscussTopic(discussCaseDmo.getType());
        queryMyPartakeCaseDetailResponse.setPatientAge(String.valueOf(discussCaseDmo.getPatientAge()));
        queryMyPartakeCaseDetailResponse.setPatientSex(String.valueOf(discussCaseDmo.getPatientSex()));
        queryMyPartakeCaseDetailResponse.setDescription(discussCaseDmo.getDescription());
        queryMyPartakeCaseDetailResponse.setMainDesc(discussCaseDmo.getMainDesc());
        queryMyPartakeCaseDetailResponse.setNowIllness(discussCaseDmo.getNowIllness());
        queryMyPartakeCaseDetailResponse.setHistoryIllness(discussCaseDmo.getHisIllness());
        queryMyPartakeCaseDetailResponse.setHealthCheck(discussCaseDmo.getHealthCheck());
        queryMyPartakeCaseDetailResponse.setAssistCheck(discussCaseDmo.getAssistCheck());

        if (null != video) {
            queryMyPartakeCaseDetailResponse.setVideoId(String.valueOf(video.getResourceId()));
        }

        List<ImageItem> imageItemList = new ArrayList<>();

        ImageItem imageItem = null;

        if (!CollectionUtils.isEmpty(imageList)) {

            for (CaseResourceDmo image : imageList) {

                imageItem = new ImageItem();

                imageItem.setImageId(String.valueOf(image.getResourceId()));

                imageItemList.add(imageItem);

            }

            queryMyPartakeCaseDetailResponse.setImageList(imageItemList);

        }

        queryMyPartakeCaseDetailResponse.setQuestion(discussCaseDmo.getMainQuestion());
        queryMyPartakeCaseDetailResponse.setReadCount(String.valueOf(discussCaseDmo.getReadCount()));
        queryMyPartakeCaseDetailResponse.setCommentCount(String.valueOf(discussCaseDmo.getCommentCount()));
        queryMyPartakeCaseDetailResponse.setLikeCount(String.valueOf(discussCaseDmo.getLikeCount()));
        queryMyPartakeCaseDetailResponse.setCollectionCount(String.valueOf(discussCaseDmo.getCollectionCount()));

        CaseLikeDmo caseLikeDmo = new CaseLikeDmo();

        caseLikeDmo.setRecordId(recordId);
        caseLikeDmo.setUserId(userId);

        Result result = caseLikeService.hasLike(caseLikeDmo);

        if (result.isSuccess()){
            queryMyPartakeCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.YES);
        }else {
            queryMyPartakeCaseDetailResponse.setHasLike(APPResponseCodeConstants.CaseHasLike.NO);
        }

        CaseCollectionDmo caseCollectionDmo = new CaseCollectionDmo();

        caseCollectionDmo.setRecordId(recordId);
        caseCollectionDmo.setUserId(userId);

        result = discussCaseCollectService.hasCollect(caseCollectionDmo);

        if (result.isSuccess()){
            queryMyPartakeCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.YES);
        }else {
            queryMyPartakeCaseDetailResponse.setHasCollect(APPResponseCodeConstants.CaseHasCollect.NO);
        }

        response.setParameter(queryMyPartakeCaseDetailResponse);

        return response;
    }
}
