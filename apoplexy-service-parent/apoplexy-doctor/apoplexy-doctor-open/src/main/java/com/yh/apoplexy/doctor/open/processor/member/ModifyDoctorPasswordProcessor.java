package com.yh.apoplexy.doctor.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.member.ModifyDoctorPasswordRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.member.ModifyDoctorPasswordResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 个人中心-修改登录密码(doc-0058)处理类
 * Created by wunder on 16/9/6 19:51.
 */
public class ModifyDoctorPasswordProcessor extends DoctorAppBaseServiceProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyDoctorPasswordProcessor.class);

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        ModifyDoctorPasswordResponse modifyDoctorPasswordResponse = new ModifyDoctorPasswordResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        ModifyDoctorPasswordRequest modifyDoctorPasswordRequest = JSONObject.parseObject(requestObject.getParameter().toString(),ModifyDoctorPasswordRequest.class);

        if (null == modifyDoctorPasswordRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(modifyDoctorPasswordRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(modifyDoctorPasswordRequest.getUserId());

        String oldPassword = modifyDoctorPasswordRequest.getOldPassword();

        String newPassword = modifyDoctorPasswordRequest.getNewPassword();

        Result result = doctorMemberService.modifyPassword(userId,oldPassword,newPassword);

        if (!result.isSuccess()){
            modifyDoctorPasswordResponse.setResultcode(APPResponseCodeConstants.ModifyMemberPWD.FAILED);
            modifyDoctorPasswordResponse.setMessage("修改失败");

        }else{
            modifyDoctorPasswordResponse.setResultcode(APPResponseCodeConstants.ModifyMemberPWD.SUCCESS);
            modifyDoctorPasswordResponse.setMessage("修改成功");
        }

        response.setParameter(modifyDoctorPasswordResponse);

        return response;

    }
}
