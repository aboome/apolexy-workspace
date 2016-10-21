package com.yh.apoplexy.doctor.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.member.ModifyDoctorJobRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.member.ModifyDoctorJobResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 个人中心-修改工作岗位(doc-0055)处理类
 * Created by wunder on 16/9/6 19:50.
 */
public class ModifyDoctorJobProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyDoctorJobProcessor.class);

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        ModifyDoctorJobResponse modifyDoctorJobResponse = new ModifyDoctorJobResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        ModifyDoctorJobRequest modifyDoctorJobRequest = JSONObject.parseObject(requestObject.getParameter().toString(),ModifyDoctorJobRequest.class);

        if (null == modifyDoctorJobRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(modifyDoctorJobRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(modifyDoctorJobRequest.getUserId());

        String post = modifyDoctorJobRequest.getPost();

        Date effectiveTime = DateUtil.parseDate(modifyDoctorJobRequest.getEffectiveTime(),DateUtil.yyyyMMddHHmmss);

        //有效期最多设置一个月
        if (DateUtil.getZeroTimeOfDay(effectiveTime).after(DateUtil.getZeroTimeOfDay(DateUtil.addMonths(DateUtil.getDate(),1)))){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        DoctorMemberDmo doctorMemberDmo = new DoctorMemberDmo();

        doctorMemberDmo.setId(userId);
        doctorMemberDmo.setJob(post);
        doctorMemberDmo.setEffectiveTime(effectiveTime);

        Result result = doctorMemberService.update(doctorMemberDmo);

        if (!result.isSuccess()){

            modifyDoctorJobResponse.setResultcode(APPResponseCodeConstants.ModifyMemberJob.FAILED);
            modifyDoctorJobResponse.setMessage("修改失败");

        }

        modifyDoctorJobResponse.setResultcode(APPResponseCodeConstants.ModifyMemberJob.SUCCESS);
        modifyDoctorJobResponse.setMessage("修改成功");

        response.setParameter(modifyDoctorJobResponse);

        return response;

    }
}
