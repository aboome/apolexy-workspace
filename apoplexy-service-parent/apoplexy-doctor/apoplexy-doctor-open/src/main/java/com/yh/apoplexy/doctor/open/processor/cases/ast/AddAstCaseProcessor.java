package com.yh.apoplexy.doctor.open.processor.cases.ast;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstHistoryDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstMedicationDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstResourcesDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCaseDetailDto;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.ast.service.intf.AstCaseService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.open.dto.cases.ast.HisIllnessItem;
import com.yh.apoplexy.doctor.open.dto.cases.ast.HisMedicineItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.dto.cases.referral.NihssInfoItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.AddAstCaseRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.AddAstCaseResponse;
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
 * 新增AST病例(doc-0035)处理类
 * Created by wunder on 16/9/11 21:42.
 */
public class AddAstCaseProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddAstCaseProcessor.class);

    @Autowired
    private AstCaseService astCaseService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        AddAstCaseResponse addAstCaseResponse = new AddAstCaseResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        AddAstCaseRequest addAstCaseRequest = JSONObject.parseObject(requestObject.getParameter().toString(),AddAstCaseRequest.class);

        if (null == addAstCaseRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(addAstCaseRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(addAstCaseRequest.getUserId());

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

        AstCasesDmo astCasesDmo = new AstCasesDmo();

        astCasesDmo.setUserId(Long.parseLong(addAstCaseRequest.getUserId()));
//        astCasesDmo.setArriveTime();
        astCasesDmo.setPatientName(addAstCaseRequest.getPatientName());
        if(StringUtils.isNotBlank(addAstCaseRequest.getPatientAge())){
            astCasesDmo.setPatientAge(Long.parseLong(addAstCaseRequest.getPatientAge()));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getPatientBirthday())){
            astCasesDmo.setPatientBirthday(DateUtil.parseDate(addAstCaseRequest.getPatientBirthday(),DateUtil.yyyyMMddHHmmss));
        }
        astCasesDmo.setPatientSex(addAstCaseRequest.getPatientSex());
        if (StringUtils.isNotBlank(addAstCaseRequest.getOnsetTime())){
            astCasesDmo.setOnsetTime(DateUtil.parseDate(addAstCaseRequest.getOnsetTime(),DateUtil.yyyyMMddHHmmss));
        }
        astCasesDmo.setMrs(astCasesDmo.getMrs());
        if (StringUtils.isNotBlank(addAstCaseRequest.getNihssFee())){
            astCasesDmo.setNihssTotalFee(Long.parseLong(addAstCaseRequest.getNihssFee()));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getWeight())){
            astCasesDmo.setWeight(Double.parseDouble(addAstCaseRequest.getWeight()));
        }
        astCasesDmo.setSmock(addAstCaseRequest.getSmock());
        astCasesDmo.setPregnancy(addAstCaseRequest.getPregnancy());
        if (StringUtils.isNotBlank(addAstCaseRequest.getLowPressure())){
            astCasesDmo.setLowPressure(Double.parseDouble(addAstCaseRequest.getLowPressure()));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getBloodSugar())){
            astCasesDmo.setBloodSugar(Double.parseDouble(addAstCaseRequest.getBloodSugar()));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getPlatelet())){
            astCasesDmo.setPlatelet(Double.parseDouble(addAstCaseRequest.getPlatelet()));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getInr())){
            astCasesDmo.setInr(Double.parseDouble(addAstCaseRequest.getInr()));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getPt())){
            astCasesDmo.setPt(Double.parseDouble(addAstCaseRequest.getPt()));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getAptt())){
            astCasesDmo.setAptt(Double.parseDouble(addAstCaseRequest.getAptt()));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getEct())){
            astCasesDmo.setEct(Double.parseDouble(addAstCaseRequest.getEct()));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getDtt())){
            astCasesDmo.setDtt(Double.parseDouble(addAstCaseRequest.getDtt()));
        }

        List<NihssDetailDmo> nihssDetailDmoList = new ArrayList<>();

        NihssDetailDmo nihssDetailDmo = null;

        if (!CollectionUtils.isEmpty(addAstCaseRequest.getNihssList())){

            for (NihssInfoItem nihssItem : addAstCaseRequest.getNihssList()){

                nihssDetailDmo = new NihssDetailDmo();

                nihssDetailDmo.setDetailIndex(Long.parseLong(nihssItem.getIndex()));
                nihssDetailDmo.setType(DoctorConstants.NihssTestSrcType.AST);
                nihssDetailDmo.setResult(Long.parseLong(nihssItem.getFee()));

                nihssDetailDmoList.add(nihssDetailDmo);
            }

        }

        List<AstHistoryDmo> astHistoryDmoList = new ArrayList<>();

        AstHistoryDmo astHistoryDmo = null;

        if (!CollectionUtils.isEmpty(addAstCaseRequest.getHisIllness())){

            for (HisIllnessItem hisIllnessItem: addAstCaseRequest.getHisIllness()){

                astHistoryDmo = new AstHistoryDmo();

                astHistoryDmo.setDetailIndex(Long.parseLong(hisIllnessItem.getIndex()));
                astHistoryDmo.setResult(hisIllnessItem.getAnswer());
                astHistoryDmo.setDescOne(hisIllnessItem.getTime());
                astHistoryDmo.setDescTwo(hisIllnessItem.getDesc());

                astHistoryDmoList.add(astHistoryDmo);

            }

        }

        List<AstMedicationDmo> astMedicationDmoList = new ArrayList<>();

        AstMedicationDmo astMedicationDmo = null;

        if (!CollectionUtils.isEmpty(addAstCaseRequest.getHisMedicaitionList())){

            for (HisMedicineItem hisMedicineItem : addAstCaseRequest.getHisMedicaitionList()){

                astMedicationDmo = new AstMedicationDmo();

                astMedicationDmo.setDetailIndex(Long.parseLong(hisMedicineItem.getIndex()));
                astMedicationDmo.setResult(hisMedicineItem.getUsed());
                astMedicationDmo.setDescription(hisMedicineItem.getMedicaitionName());

                astMedicationDmoList.add(astMedicationDmo);
            }

        }

        astCasesDmo.setEmergencyIndex(addAstCaseRequest.getEmergencyIndex());
        astCasesDmo.setEmergencyDesc(addAstCaseRequest.getEmergencyDesc());
        astCasesDmo.setVeinThrombosis(addAstCaseRequest.getVeinThrombosis());
        if (StringUtils.isNotBlank(addAstCaseRequest.getArriveHospitalTime())){
            astCasesDmo.setArriveHospitalTime(DateUtil.parseDate(addAstCaseRequest.getArriveHospitalTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getCallTime())){
            astCasesDmo.setCallTime(DateUtil.parseDate(addAstCaseRequest.getCallTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getThrombolysisArriveTime())){
            astCasesDmo.setThrombolysisArriveTime(DateUtil.parseDate(addAstCaseRequest.getThrombolysisArriveTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getThrombolysisBeginTime())){
            astCasesDmo.setThrombolysisBeginTime(DateUtil.parseDate(addAstCaseRequest.getThrombolysisBeginTime(),DateUtil.yyyyMMddHHmmss));
        }
        if (StringUtils.isNotBlank(addAstCaseRequest.getDnt())){
            astCasesDmo.setDnt(Double.parseDouble(addAstCaseRequest.getDnt()));
        }
        astCasesDmo.setNegativeReason(addAstCaseRequest.getNegativeReason());
        astCasesDmo.setNegativeReasonDesc(addAstCaseRequest.getNegativeReasonDesc());
        astCasesDmo.setMultiImage(addAstCaseRequest.getMultiImage());

        List<AstResourcesDmo> ctDmoList = new ArrayList<>();

        AstResourcesDmo ctDmo = null;

        if (!CollectionUtils.isEmpty(addAstCaseRequest.getCtList())){

            for (ImageItem ctItem : addAstCaseRequest.getCtList()){

                ctDmo = new AstResourcesDmo();

                ctDmo.setImageId(ctItem.getImageId());
                ctDmo.setType(DoctorConstants.AstResourcesType.CT);

                ctDmoList.add(ctDmo);
            }

        }

        List<AstResourcesDmo> ctaDmoList = new ArrayList<>();

        AstResourcesDmo ctaDmo = null;

        if (!CollectionUtils.isEmpty(addAstCaseRequest.getCtaList())){

            for (ImageItem ctaItem : addAstCaseRequest.getCtaList()){

                ctaDmo = new AstResourcesDmo();

                ctaDmo.setImageId(ctaItem.getImageId());
                ctaDmo.setType(DoctorConstants.AstResourcesType.CTA);

                ctaDmoList.add(ctaDmo);
            }

        }


        List<AstResourcesDmo> ctpDmoList = new ArrayList<>();

        AstResourcesDmo ctpDmo = null;

        if (!CollectionUtils.isEmpty(addAstCaseRequest.getCtpList())){

            for (ImageItem ctpItem : addAstCaseRequest.getCtpList()){

                ctpDmo = new AstResourcesDmo();

                ctpDmo.setImageId(ctpItem.getImageId());
                ctpDmo.setType(DoctorConstants.AstResourcesType.CTP);

                ctpDmoList.add(ctpDmo);
            }

        }

        astCasesDmo.setMultiImageNegativeDesc(addAstCaseRequest.getMultiImageNegaticeDesc());
        astCasesDmo.setMacroangiopathy(addAstCaseRequest.getMacroangiopathy());
        astCasesDmo.setMacroangiopathyDesc(addAstCaseRequest.getMacroangiopathyDesc());
        astCasesDmo.setMacroangiopathyReason(addAstCaseRequest.getMacroangiopathyReason());
        astCasesDmo.setPhoneOne(addAstCaseRequest.getPhoneOne());
        astCasesDmo.setPhoneTwo(addAstCaseRequest.getPhoneTwo());
        astCasesDmo.setAmbulanceGo(addAstCaseRequest.getAmbulanceGo());
        astCasesDmo.setFollowGo(addAstCaseRequest.getFollowGo());
        astCasesDmo.setInpatientNo(addAstCaseRequest.getInpatientNo());
        astCasesDmo.setFollowGoWhere(addAstCaseRequest.getFollowGoWhere());

        AstCaseDetailDto astCaseDetailDto = new AstCaseDetailDto();

        astCaseDetailDto.setAstCasesDmo(astCasesDmo);
        astCaseDetailDto.setCtaList(ctaDmoList);
        astCaseDetailDto.setCtList(ctDmoList);
        astCaseDetailDto.setCtpList(ctpDmoList);
        astCaseDetailDto.setHisIllness(astHistoryDmoList);
        astCaseDetailDto.setHisMedicaitionList(astMedicationDmoList);
        astCaseDetailDto.setNihssList(nihssDetailDmoList);
        astCaseDetailDto.setDoctorMemberDmo(doctorMemberDmo);

        Result result = astCaseService.addCase(astCaseDetailDto);

        if (!result.isSuccess()){

            addAstCaseResponse.setStatus(APPResponseCodeConstants.AddAst.FAILED);
            addAstCaseResponse.setMessage("提交失败");

        }else {

            addAstCaseResponse.setStatus(APPResponseCodeConstants.AddAst.SUCCESS);
            addAstCaseResponse.setMessage("提交成功");

        }

        response.setParameter(addAstCaseResponse);
        return response;

    }
}
