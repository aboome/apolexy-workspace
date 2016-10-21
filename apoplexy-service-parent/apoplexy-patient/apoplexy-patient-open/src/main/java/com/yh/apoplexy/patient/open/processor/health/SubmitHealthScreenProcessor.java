package com.yh.apoplexy.patient.open.processor.health;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDetailDmo;
import com.yh.apoplexy.assist.dmo.patient.health.PatientScreenDmo;
import com.yh.apoplexy.assist.dmo.patient.member.PatientMemberDmo;
import com.yh.apoplexy.assist.dto.patient.health.SubmitHealthScreenDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.PatientConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.health.service.intf.HealthScreenService;
import com.yh.apoplexy.patient.member.service.intf.PatientMemberService;
import com.yh.apoplexy.patient.open.dto.health.OptionItem;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.health.SubmitHealthScreenRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.health.SubmitHealthScreenResponse;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 提交健康筛查信息 (pat-0005)处理类
 * Created by wunder on 16/9/5 09:48.
 */
public class SubmitHealthScreenProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitHealthScreenProcessor.class);

    @Autowired
    private HealthScreenService healthScreenService;

    @Autowired
    private PatientMemberService patientMemberService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        SubmitHealthScreenResponse submitHealthScreenResponse = new SubmitHealthScreenResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        SubmitHealthScreenRequest submitHealthScreenRequest = JSONObject.parseObject(requestObject.getParameter().toString(), SubmitHealthScreenRequest.class);

        //参数校验
        if (null == submitHealthScreenRequest) {
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(submitHealthScreenRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        List<OptionItem> historyList = submitHealthScreenRequest.getHistory();

        List<OptionItem> screenList = submitHealthScreenRequest.getList();

        Long userId = Long.parseLong(submitHealthScreenRequest.getUserId());

        //查询患者用户信息
        PatientMemberDmo patientMemberCon = new PatientMemberDmo();

        patientMemberCon.setId(userId);
        patientMemberCon.setStatus(Constants.MemberStatus.NORMAL);

        PatientMemberDmo patientMemberDmo = patientMemberService.findPatientMember(patientMemberCon);

        if (null == patientMemberDmo) {
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        boolean incidence = false;

        Long score = 0L;
        //计算发病率
        for (OptionItem optionItem : screenList) {

            if (Constants.QuestionAnswer.YES.equals(optionItem.getAnswer())) {
                score++;
            }

        }

        PatientScreenDmo patientScreenDmo = new PatientScreenDmo();

        patientScreenDmo.setUserId(userId);
        patientScreenDmo.setUserName(patientMemberDmo.getRealName());
        patientScreenDmo.setSex(submitHealthScreenRequest.getSex());
        if (StringUtils.isNotBlank(submitHealthScreenRequest.getAge())){
            patientScreenDmo.setAge(Long.parseLong(submitHealthScreenRequest.getAge()));
        }
        patientScreenDmo.setCreateTime(DateUtil.getDate());
        patientScreenDmo.setIncidence(score);

        List<PatientScreenDetailDmo> historyDetailList = new ArrayList<>();

        PatientScreenDetailDmo patientScreenDetailDmo = null;

        for (OptionItem optionItem : historyList) {

            patientScreenDetailDmo = new PatientScreenDetailDmo();

            patientScreenDetailDmo.setDetailIndex(Long.parseLong(optionItem.getIndex()));
            patientScreenDetailDmo.setType(PatientConstants.ScreenType.HISTORY);
            patientScreenDetailDmo.setResult(optionItem.getAnswer());

            historyDetailList.add(patientScreenDetailDmo);

            if (Constants.QuestionAnswer.YES.equals(optionItem.getAnswer())) {
                incidence = true;
            }

        }

        List<PatientScreenDetailDmo> screenDetailList = new ArrayList<>();

        for (OptionItem optionItem : screenList) {

            patientScreenDetailDmo = new PatientScreenDetailDmo();

            patientScreenDetailDmo.setDetailIndex(Long.parseLong(optionItem.getIndex()));
            patientScreenDetailDmo.setType(PatientConstants.ScreenType.FIRST_SCREEN);
            patientScreenDetailDmo.setResult(optionItem.getAnswer());

            screenDetailList.add(patientScreenDetailDmo);

        }

        if (score >= 3) {
            incidence = true;
        }

        SubmitHealthScreenDto submitHealthScreenDto = new SubmitHealthScreenDto();

        submitHealthScreenDto.setPatientScreenDmo(patientScreenDmo);
        submitHealthScreenDto.setHistoryList(historyDetailList);
        submitHealthScreenDto.setIncidence(incidence);
        submitHealthScreenDto.setScreenList(screenDetailList);

        Result result = healthScreenService.submitHealthScreenInfo(submitHealthScreenDto);

        if (incidence){
            submitHealthScreenResponse.setIncidence(Constants.QuestionAnswer.YES);
        }else {
            submitHealthScreenResponse.setIncidence(Constants.QuestionAnswer.NO);
        }

        if (!result.isSuccess()) {

            submitHealthScreenResponse.setResultcode(APPResponseCodeConstants.HealthTest.FAILED);
            submitHealthScreenResponse.setMessage("提交失败");

            response.setParameter(submitHealthScreenResponse);

            return response;
        }

        submitHealthScreenResponse.setResultcode(APPResponseCodeConstants.HealthTest.SUCCESS);
        submitHealthScreenResponse.setMessage("提交成功");

        response.setParameter(submitHealthScreenResponse);

        return response;

    }
}
