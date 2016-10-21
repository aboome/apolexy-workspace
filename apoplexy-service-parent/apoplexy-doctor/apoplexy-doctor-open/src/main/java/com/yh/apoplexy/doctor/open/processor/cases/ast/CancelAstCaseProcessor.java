package com.yh.apoplexy.doctor.open.processor.cases.ast;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseService;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.CancelAstCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.CancelAstCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 我的-我发布的详情-撤销(doc-0044)处理类
 * Created by wunder on 16/9/11 21:41.
 */
public class CancelAstCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelAstCaseProcessor.class);

    @Autowired
    private AstCaseService astCaseService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        CancelAstCaseResponse cancelAstCaseResponse = new CancelAstCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        CancelAstCaseRequest cancelAstCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(),CancelAstCaseRequest.class);

        if (null == cancelAstCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(cancelAstCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(cancelAstCaseRequest.getUserId());

        Long recordId = Long.parseLong(cancelAstCaseRequest.getRecordId());

        Result result = astCaseService.deleteCase(recordId,userId);

        if (!result.isSuccess()){

            cancelAstCaseResponse.setStatus(APPResponseCodeConstants.CancelMyAst.FAILED);
            cancelAstCaseResponse.setMessage("提交失败");

        }else {

            cancelAstCaseResponse.setStatus(APPResponseCodeConstants.CancelMyAst.SUCCESS);
            cancelAstCaseResponse.setMessage("提交成功");

        }

        response.setParameter(cancelAstCaseResponse);

        return response;
    }
}
