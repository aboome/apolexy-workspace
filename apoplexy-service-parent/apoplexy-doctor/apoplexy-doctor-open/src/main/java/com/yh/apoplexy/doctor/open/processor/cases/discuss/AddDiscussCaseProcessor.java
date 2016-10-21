package com.yh.apoplexy.doctor.open.processor.cases.discuss;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.DiscussCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.discuss.AddDiscussCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.AddDiscussCaseResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 新增病例讨论(doc-0006)处理类
 * Created by wunder on 16/9/7 11:07.
 */
public class AddDiscussCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddDiscussCaseProcessor.class);

    @Autowired
    private DiscussCaseService discussCaseService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        AddDiscussCaseResponse addDiscussCaseResponse = new AddDiscussCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        AddDiscussCaseRequest addDiscussCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(),AddDiscussCaseRequest.class);

        if (null == addDiscussCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(addDiscussCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(addDiscussCaseRequest.getUserId());

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(userId);
        doctorMemberCon.setStatus(Constants.MemberStatus.NORMAL);

        DoctorMemberDto doctorMemberDto = doctorMemberService.findDoctorBaseInfo(doctorMemberCon);

        if (null == doctorMemberDto){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        DoctorMemberDmo doctorMemberDmo = new DoctorMemberDmo();
        doctorMemberDmo.setDoctorName(doctorMemberDto.getDoctorName());

        DiscussCaseDmo discussCaseDmo = new DiscussCaseDmo();

        discussCaseDmo.setUserId(userId);
        discussCaseDmo.setType(addDiscussCaseRequest.getDiscussTopic());
        if(StringUtils.isNotBlank(addDiscussCaseRequest.getPatientAge())){
            discussCaseDmo.setPatientAge(Long.parseLong(addDiscussCaseRequest.getPatientAge()));
        }
        discussCaseDmo.setPatientSex(addDiscussCaseRequest.getPatientSex());
        discussCaseDmo.setDescription(addDiscussCaseRequest.getDescription());
        discussCaseDmo.setMainDesc(addDiscussCaseRequest.getMainDesc());
        discussCaseDmo.setNowIllness(addDiscussCaseRequest.getNowIllness());
        discussCaseDmo.setHisIllness(addDiscussCaseRequest.getHistoryIllness());
        discussCaseDmo.setHealthCheck(addDiscussCaseRequest.getHealthCheck());
        discussCaseDmo.setAssistCheck(addDiscussCaseRequest.getAssistCheck());
        discussCaseDmo.setMainQuestion(addDiscussCaseRequest.getQuestion());
        discussCaseDmo.setCaseRange(addDiscussCaseRequest.getRecordType());
        if (doctorMemberDto.getRootHospitalId().equals(0L)){
            discussCaseDmo.setRootHospitalId(doctorMemberDto.getHospitalId());
        }else {
            discussCaseDmo.setRootHospitalId(doctorMemberDto.getRootHospitalId());
        }

        List<CaseResourceDmo> imageList = new ArrayList<>();

        CaseResourceDmo imageDmo = null;

        if (!CollectionUtils.isEmpty(addDiscussCaseRequest.getImageList())){

            for (ImageItem imageItem : addDiscussCaseRequest.getImageList()){

                imageDmo = new CaseResourceDmo();

                imageDmo.setUserId(userId);
                imageDmo.setResourceId(imageItem.getImageId());
                imageDmo.setType(Constants.ResourcesType.IMAGE);

                imageList.add(imageDmo);

            }
        }

        CaseResourceDmo videoDmo = new CaseResourceDmo();

        videoDmo.setUserId(userId);
        videoDmo.setResourceId(addDiscussCaseRequest.getVideoId());
        videoDmo.setType(Constants.ResourcesType.VIDEO);

        DiscussCaseDetailDto discussCaseDetailDto = new DiscussCaseDetailDto();

        discussCaseDetailDto.setDoctorMemberDmo(doctorMemberDmo);
        discussCaseDetailDto.setDiscussCaseDmo(discussCaseDmo);
        discussCaseDetailDto.setImageList(imageList);
        discussCaseDetailDto.setVideo(videoDmo);

        Result result = discussCaseService.addDiscussCase(discussCaseDetailDto);

        if (!result.isSuccess()){

            addDiscussCaseResponse.setStatus(APPResponseCodeConstants.AddCase.FAILED);
            addDiscussCaseResponse.setMessage("提交失败");

        }else {

            addDiscussCaseResponse.setStatus(APPResponseCodeConstants.AddCase.SUCCESS);
            addDiscussCaseResponse.setMessage("提交成功");

        }

        response.setParameter(addDiscussCaseResponse);
        return response;

    }
}
