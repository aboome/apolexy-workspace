package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCommentDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCommentService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.ScoreReferralCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.ScoreReferralCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 我的-我的接诊详情-评分(doc-0032)处理类
 * Created by wunder on 16/9/10 14:03.
 */
public class ScoreReferralCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreReferralCaseProcessor.class);

    @Autowired
    private ReferralCommentService referralCommentService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        ScoreReferralCaseResponse scoreReferralCaseResponse = new ScoreReferralCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        ScoreReferralCaseRequest scoreReferralCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(),ScoreReferralCaseRequest.class);

        if (null == scoreReferralCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(scoreReferralCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(scoreReferralCaseRequest.getUserId());

        Long recordId = Long.parseLong(scoreReferralCaseRequest.getRecordId());

        Long caseIntegrity = Long.parseLong(scoreReferralCaseRequest.getCaseIntegrity());

        Long caseDetail = Long.parseLong(scoreReferralCaseRequest.getCaseDetail());

        ReferralCommentDmo con = new ReferralCommentDmo();

        con.setUserId(userId);
        con.setRecordId(recordId);
        con.setCaseIntegrity(caseIntegrity);
        con.setCaseDetail(caseDetail);

        Result result = referralCommentService.submitReferralComment(con);

        if (!result.isSuccess()){

            scoreReferralCaseResponse.setStatus(APPResponseCodeConstants.ScoreReferral.FAILED);
            scoreReferralCaseResponse.setMessage("提交失败");

        }else {

            scoreReferralCaseResponse.setStatus(APPResponseCodeConstants.ScoreReferral.SUCCESS);
            scoreReferralCaseResponse.setMessage("提交成功");

        }

        response.setParameter(scoreReferralCaseResponse);
        return response;
    }
}
