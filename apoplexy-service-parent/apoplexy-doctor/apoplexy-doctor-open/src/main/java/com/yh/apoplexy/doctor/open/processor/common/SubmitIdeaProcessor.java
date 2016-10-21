package com.yh.apoplexy.doctor.open.processor.common;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.IdeaInfoDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.base.service.intf.IdeaInfoService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.common.SubmitIdeaRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.common.SubmitIdeaResponse;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 意见反馈接口 (common-0012)处理类
 * Created by wunder on 16/9/7 00:46.
 */
public class SubmitIdeaProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitIdeaProcessor.class);

    @Autowired
    private IdeaInfoService ideaInfoService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private PatientMemberService patientMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        SubmitIdeaResponse submitIdeaResponse = new SubmitIdeaResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        SubmitIdeaRequest submitIdeaRequest = JSONObject.parseObject(requestObject.getParameter().toString(),SubmitIdeaRequest.class);

        if (null == submitIdeaRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(submitIdeaRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(submitIdeaRequest.getUserId());

        String idea = submitIdeaRequest.getIdea();

        String type = submitIdeaRequest.getType();

        IdeaInfoDmo ideaInfoDmo = new IdeaInfoDmo();

        if (AppConstants.ClientType.DOCTOR.equals(type)){

            DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

            doctorMemberCon.setId(userId);

            DoctorMemberDmo doctorMemberDmo = doctorMemberService.selectOne(doctorMemberCon);

            if (null == doctorMemberDmo){
                response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
                return response;
            }

            ideaInfoDmo.setUserName(doctorMemberDmo.getDoctorName());

        }else if (AppConstants.ClientType.PATIENT.equals(type)){

            PatientMemberDmo patientMemberCon = new PatientMemberDmo();

            patientMemberCon.setId(userId);

            PatientMemberDmo patientMemberDmo = patientMemberService.findPatientMember(patientMemberCon);

            if (null == patientMemberDmo){
                response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
                return response;
            }

            ideaInfoDmo.setUserName(patientMemberDmo.getRealName());

        }else {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        ideaInfoDmo.setUserId(userId);
        ideaInfoDmo.setIdea(idea);
        ideaInfoDmo.setCreateTime(DateUtil.getDate());
        ideaInfoDmo.setType(type);

        Result result = ideaInfoService.insert(ideaInfoDmo);

        if (!result.isSuccess()){

            submitIdeaResponse.setResultcode(APPResponseCodeConstants.IdeaCommit.FAILED);
            submitIdeaResponse.setMessage("提交失败");

        }else {
            submitIdeaResponse.setResultcode(APPResponseCodeConstants.IdeaCommit.SUCCESS);
            submitIdeaResponse.setMessage("提交成功");
        }

        response.setParameter(submitIdeaResponse);

        return response;
    }
}
