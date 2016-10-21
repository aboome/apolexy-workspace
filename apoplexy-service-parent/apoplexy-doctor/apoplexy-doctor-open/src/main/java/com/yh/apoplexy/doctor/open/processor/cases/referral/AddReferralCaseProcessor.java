package com.yh.apoplexy.doctor.open.processor.cases.referral;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralScreenDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.referral.service.intf.ReferralCaseService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.NihssInfoItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.ReferralScreenItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.referral.AddReferralCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.referral.AddReferralCaseResponse;
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
 * 新增转诊病例(doc-0020)处理类
 * Created by wunder on 16/9/9 20:07.
 */
public class AddReferralCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddReferralCaseProcessor.class);

    @Autowired
    private ReferralCaseService referralCaseService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        AddReferralCaseResponse addReferralCaseResponse = new AddReferralCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        AddReferralCaseRequest addReferralCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(),AddReferralCaseRequest.class);

        if (null == addReferralCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(addReferralCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(addReferralCaseRequest.getUserId());

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


        ReferralCaseDmo referralCaseDmo = new ReferralCaseDmo();

        referralCaseDmo.setUserId(userId);
        referralCaseDmo.setCaseRange(addReferralCaseRequest.getRecordType());
        referralCaseDmo.setType(addReferralCaseRequest.getReferralType());
        referralCaseDmo.setTitle(addReferralCaseRequest.getReferralTitle());
        if (StringUtils.isNotBlank(addReferralCaseRequest.getPatientAge())){
            referralCaseDmo.setPatientAge(Long.parseLong(addReferralCaseRequest.getPatientAge()));
        }
        referralCaseDmo.setPatientSex(addReferralCaseRequest.getPatientSex());
        if (StringUtils.isNotBlank(addReferralCaseRequest.getTemperature())){
            referralCaseDmo.setTemperature(Double.parseDouble(addReferralCaseRequest.getTemperature()));
        }
        if (StringUtils.isNotBlank(addReferralCaseRequest.getHeartRate())){
            referralCaseDmo.setHeartRate(Long.parseLong(addReferralCaseRequest.getHeartRate()));
        }
        if (StringUtils.isNotBlank(addReferralCaseRequest.getBreath())){
            referralCaseDmo.setBreath(Long.parseLong(addReferralCaseRequest.getBreath()));
        }
        referralCaseDmo.setConsciousness(addReferralCaseRequest.getConsciousness());
        if (StringUtils.isNotBlank(addReferralCaseRequest.getHighPressure())){
            referralCaseDmo.setHighPressure(Double.parseDouble(addReferralCaseRequest.getHighPressure()));
        }
        if (StringUtils.isNotBlank(addReferralCaseRequest.getLowPressure())){
            referralCaseDmo.setLowPressure(Double.parseDouble(addReferralCaseRequest.getLowPressure()));
        }
        if (StringUtils.isNotBlank(addReferralCaseRequest.getNasogastricTube())){
            referralCaseDmo.setNasogastricTube(DateUtil.parseDate(addReferralCaseRequest.getNasogastricTube(),DateUtil.yyyyMMddHHmmss));
        }
        if (StringUtils.isNotBlank(addReferralCaseRequest.getIndwellingCatheter())){
            referralCaseDmo.setIndwellingCatheter(DateUtil.parseDate(addReferralCaseRequest.getIndwellingCatheter(),DateUtil.yyyyMMddHHmmss));
        }
        if (StringUtils.isNotBlank(addReferralCaseRequest.getSuperficialVein())){
            referralCaseDmo.setSuperficialVein(DateUtil.parseDate(addReferralCaseRequest.getSuperficialVein(),DateUtil.yyyyMMddHHmmss));
        }
        if (StringUtils.isNotBlank(addReferralCaseRequest.getDeepVein())){
            referralCaseDmo.setDeepVein(DateUtil.parseDate(addReferralCaseRequest.getDeepVein(),DateUtil.yyyyMMddHHmmss));
        }
        if (StringUtils.isNotBlank(addReferralCaseRequest.getPicc())){
            referralCaseDmo.setPicc(DateUtil.parseDate(addReferralCaseRequest.getPicc(),DateUtil.yyyyMMddHHmmss));
        }
        referralCaseDmo.setSkinType(addReferralCaseRequest.getSkinType());
        referralCaseDmo.setSkinDesc(addReferralCaseRequest.getSkinDesc());

        List<NihssDetailDmo> nihssDetailDmoList = new ArrayList<>();

        NihssDetailDmo nihssDetailDmo = null;

        List<NihssInfoItem> nihssInfoItemList = addReferralCaseRequest.getNihssList();

        if (!CollectionUtils.isEmpty(nihssInfoItemList)){
            for (NihssInfoItem nihssInfoItem:nihssInfoItemList){

                nihssDetailDmo = new NihssDetailDmo();

                nihssDetailDmo.setUserId(Long.parseLong(addReferralCaseRequest.getUserId()));
                nihssDetailDmo.setType(DoctorConstants.NihssTestSrcType.REFERRAL);
                nihssDetailDmo.setDetailIndex(Long.parseLong(nihssInfoItem.getIndex()));
                nihssDetailDmo.setResult(Long.parseLong(nihssInfoItem.getFee()));

                nihssDetailDmoList.add(nihssDetailDmo);

            }
        }

        if (StringUtils.isNotBlank(addReferralCaseRequest.getNihssTotalFee())){
            referralCaseDmo.setNihssTotalFee(Long.parseLong(addReferralCaseRequest.getNihssTotalFee()));
        }

        referralCaseDmo.setPatientSub(addReferralCaseRequest.getDescription());
        referralCaseDmo.setMainDesc(addReferralCaseRequest.getMainDesc());
        referralCaseDmo.setNowIllness(addReferralCaseRequest.getNowIllness());
        referralCaseDmo.setHisIllness(addReferralCaseRequest.getHistoryIllness());
        referralCaseDmo.setHealthCheck(addReferralCaseRequest.getHealthCheck());
        referralCaseDmo.setAssistCheck(addReferralCaseRequest.getAssistCheck());

        if (doctorMemberDto.getRootHospitalId().equals(0L)){
            referralCaseDmo.setRootHospitalId(doctorMemberDto.getHospitalId());
        }else {
            referralCaseDmo.setRootHospitalId(doctorMemberDto.getRootHospitalId());
        }

        List<ReferralResourceDmo> imageList = new ArrayList<>();

        ReferralResourceDmo imageDmo = null;

        if (!CollectionUtils.isEmpty(addReferralCaseRequest.getImageList())){
            for (ImageItem imageItem : addReferralCaseRequest.getImageList()){

                imageDmo = new ReferralResourceDmo();

                imageDmo.setUserId(userId);
                imageDmo.setResourceId(imageItem.getImageId());
                imageDmo.setType(Constants.ResourcesType.IMAGE);

                imageList.add(imageDmo);

            }
        }

        List<ReferralScreenDetailDmo> referralScreenDetailDmoList = new ArrayList<>();

        ReferralScreenDetailDmo referralScreenDetailDmo = null;

        if (!CollectionUtils.isEmpty(addReferralCaseRequest.getScreenList())){
            for (ReferralScreenItem referralScreenItem: addReferralCaseRequest.getScreenList()){

                referralScreenDetailDmo = new ReferralScreenDetailDmo();

                referralScreenDetailDmo.setDetailIndex(Long.parseLong(referralScreenItem.getIndex()));
                referralScreenDetailDmo.setResult(referralScreenItem.getAnswer());

                referralScreenDetailDmoList.add(referralScreenDetailDmo);
            }
        }

        ReferralCaseDetailDto referralCaseDetailDto = new ReferralCaseDetailDto();

        referralCaseDetailDto.setDoctorMemberDmo(doctorMemberDmo);
        referralCaseDetailDto.setReferralCaseDmo(referralCaseDmo);
        referralCaseDetailDto.setImageList(imageList);
        referralCaseDetailDto.setNihssList(nihssDetailDmoList);
        referralCaseDetailDto.setScreenList(referralScreenDetailDmoList);

        Result result = referralCaseService.addCase(referralCaseDetailDto);

        if (!result.isSuccess()){

            addReferralCaseResponse.setStatus(APPResponseCodeConstants.AddReferralCase.FAILED);
            addReferralCaseResponse.setMessage("提交失败");

        }else {

            addReferralCaseResponse.setStatus(APPResponseCodeConstants.AddReferralCase.SUCCESS);
            addReferralCaseResponse.setMessage("提交成功");

        }

        response.setParameter(addReferralCaseResponse);
        return response;

    }
}
