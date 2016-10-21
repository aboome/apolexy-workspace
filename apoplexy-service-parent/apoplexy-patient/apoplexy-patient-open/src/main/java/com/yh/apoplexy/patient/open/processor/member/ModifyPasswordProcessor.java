package com.yh.apoplexy.patient.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.member.ModifyPasswordRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.member.ModifyPasswordResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 个人中心-修改密码(pat-0019)处理类
 * Created by wunder on 16/9/5 23:47.
 */
public class ModifyPasswordProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyPasswordProcessor.class);

    @Autowired
    private PatientMemberService patientMemberService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        ModifyPasswordResponse modifyPasswordResponse = new ModifyPasswordResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        ModifyPasswordRequest modifyPasswordRequest = JSONObject.parseObject(requestObject.getParameter().toString(),ModifyPasswordRequest.class);

        //参数校验
        if (null == modifyPasswordRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(modifyPasswordRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(modifyPasswordRequest.getUserId());

        String oldPassword = modifyPasswordRequest.getOldPassword();

        String newPassword = modifyPasswordRequest.getNewPassword();

        Result result = patientMemberService.modifyPassword(userId,oldPassword,newPassword);

        if (!result.isSuccess()){

            modifyPasswordResponse.setResultcode(APPResponseCodeConstants.ModifyMemberPWD.FAILED);
            modifyPasswordResponse.setMessage("原密码错误，修改失败");

        }else {

            modifyPasswordResponse.setResultcode(APPResponseCodeConstants.ModifyMemberPWD.SUCCESS);
            modifyPasswordResponse.setMessage("修改成功");

        }



        response.setParameter(modifyPasswordResponse);

        return response;
    }
}
