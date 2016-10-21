package com.yh.apoplexy.doctor.open.processor.cases.discuss;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.discuss.DeleteDiscussCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.DeleteDiscussCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 我的-我发布的详情-删除病例讨论(doc-0014)处理类
 * Created by wunder on 16/9/8 00:37.
 */
public class DeleteDiscussCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteDiscussCaseProcessor.class);

    @Autowired
    private DiscussCaseService discussCaseService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        DeleteDiscussCaseResponse deleteDiscussCaseResponse = new DeleteDiscussCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        DeleteDiscussCaseRequest deleteDiscussCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(),DeleteDiscussCaseRequest.class);

        if (null == deleteDiscussCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(deleteDiscussCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(deleteDiscussCaseRequest.getUserId());

        Long recordId = Long.parseLong(deleteDiscussCaseRequest.getRecordId());

        Result result = discussCaseService.deleteCase(recordId,userId);

        if (!result.isSuccess()){

            deleteDiscussCaseResponse.setStatus(APPResponseCodeConstants.CaseDelete.FAILED);
            deleteDiscussCaseResponse.setMessage("提交失败");

        }else {

            deleteDiscussCaseResponse.setStatus(APPResponseCodeConstants.CaseDelete.SUCCESS);
            deleteDiscussCaseResponse.setMessage("提交成功");

        }

        response.setParameter(deleteDiscussCaseResponse);

        return response;
    }
}
