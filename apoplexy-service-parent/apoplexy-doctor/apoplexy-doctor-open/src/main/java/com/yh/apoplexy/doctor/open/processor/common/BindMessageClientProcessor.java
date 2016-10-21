package com.yh.apoplexy.doctor.open.processor.common;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.common.BindMessageClientRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.common.BindMessageClientResponse;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 绑定消息推送客户端ID(common-0009)处理类
 * Created by wunder on 16/9/4 13:19.
 */
public class BindMessageClientProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BindMessageClientProcessor.class);

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Autowired
    private PatientMemberService patientMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        BindMessageClientResponse bindMessageClientResponse = new BindMessageClientResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        BindMessageClientRequest bindMessageClientRequest = JSONObject.parseObject(requestObject.getParameter().toString(), BindMessageClientRequest.class);

        //参数错误
        if (null == bindMessageClientRequest) {
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(bindMessageClientRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result result = new Result();

        if (AppConstants.ClientType.DOCTOR.equals(bindMessageClientRequest.getType())){

            DoctorMemberDmo doctorMemberDmo = new DoctorMemberDmo();

            doctorMemberDmo.setId(Long.parseLong(bindMessageClientRequest.getUserId()));
            doctorMemberDmo.setClientId(bindMessageClientRequest.getClientId());
            doctorMemberDmo.setLastUpdateTime(DateUtil.getDate());

            result = doctorMemberService.update(doctorMemberDmo);

        }else if (AppConstants.ClientType.PATIENT.equals(bindMessageClientRequest.getType())){

            PatientMemberDmo patientMemberDmo = new PatientMemberDmo();

            patientMemberDmo.setId(Long.parseLong(bindMessageClientRequest.getUserId()));
            patientMemberDmo.setClientId(bindMessageClientRequest.getClientId());
            patientMemberDmo.setLastUpdateTime(DateUtil.getDate());

            result = patientMemberService.update(patientMemberDmo);

        }else {
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        if (result.isSuccess()){
            bindMessageClientResponse.setResultcode(APPResponseCodeConstants.BindClientId.SUCCESS);
            bindMessageClientResponse.setMessage("绑定消息推送客户端ID成功");
        }else {
            bindMessageClientResponse.setResultcode(APPResponseCodeConstants.BindClientId.FAILED);
            bindMessageClientResponse.setMessage("绑定消息推送客户端ID失败");
        }

        response.setParameter(bindMessageClientResponse);

        return response;

    }
}
