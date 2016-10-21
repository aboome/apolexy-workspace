package com.yh.apoplexy.patient.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.integration.service.intf.ValidateCodeService;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.member.ModifyBaseInfoRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.member.ModifyBaseInfoResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 个人中心-修改基本资料(pat-0016)处理类
 * Created by wunder on 16/9/5 22:45.
 */
public class ModifyBaseInfoProcessor extends PatientAppBaseServiceProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyBaseInfoProcessor.class);

    @Autowired
    private PatientMemberService patientMemberService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        ModifyBaseInfoResponse modifyBaseInfoResponse = new ModifyBaseInfoResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        ModifyBaseInfoRequest modifyBaseInfoRequest = JSONObject.parseObject(requestObject.getParameter().toString(),ModifyBaseInfoRequest.class);

        //参数校验
        if (null == modifyBaseInfoRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(modifyBaseInfoRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(modifyBaseInfoRequest.getUserId());

        String sex = modifyBaseInfoRequest.getSex();

        PatientMemberDmo patientMemberDmo = new PatientMemberDmo();

        patientMemberDmo.setId(userId);

        String phone = modifyBaseInfoRequest.getPhone();

        String validateCode = modifyBaseInfoRequest.getValidateCode();

        patientMemberDmo.setPhone(phone);

        if (StringUtils.isNotBlank(modifyBaseInfoRequest.getBirthday())){
            patientMemberDmo.setBirthday(DateUtil.parseDate(modifyBaseInfoRequest.getBirthday(),DateUtil.yyyyMMddHHmmss));
        }

        patientMemberDmo.setSex(sex);

        Result result = patientMemberService.modifyMemberInfo(patientMemberDmo,validateCode);

        if (!result.isSuccess()){

            if("MMI-0004".equals(result.getErrorCode())){
                modifyBaseInfoResponse.setResultcode(APPResponseCodeConstants.ModifyMemberInfo.FAILED_VALIDATE_CODE_ERROR);
                modifyBaseInfoResponse.setMessage("手机验证码不正确");
            }else {
                modifyBaseInfoResponse.setResultcode(APPResponseCodeConstants.ModifyMemberInfo.FAILED);
                modifyBaseInfoResponse.setMessage("修改失败");
            }

        }else{

            modifyBaseInfoResponse.setResultcode(APPResponseCodeConstants.ModifyMemberInfo.SUCCESS);
            modifyBaseInfoResponse.setMessage("修改成功");

        }

        response.setParameter(modifyBaseInfoResponse);

        return response;
    }
}
