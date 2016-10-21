package com.yh.apoplexy.doctor.open.processor.cases.discuss;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.DiscussCaseInfoDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.QueryDiscussCaseDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseService;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussCaseItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.discuss.QueryDiscussCaseListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.QueryDiscussCaseListResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询病例讨论列表(doc-0005)处理类
 * Created by wunder on 16/9/6 15:26.
 */
public class QueryDiscussCaseListProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDiscussCaseListProcessor.class);

    @Autowired
    private DiscussCaseService discussCaseService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryDiscussCaseListResponse queryDiscussCaseListResponse = new QueryDiscussCaseListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryDiscussCaseListRequest queryDiscussCaseListRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryDiscussCaseListRequest.class);

        if (null == queryDiscussCaseListRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryDiscussCaseListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryDiscussCaseListRequest.getUserId());

        String type = queryDiscussCaseListRequest.getType();

        String subType = queryDiscussCaseListRequest.getSubType();

        int currPage = Integer.parseInt(queryDiscussCaseListRequest.getCurPage());

        int pageSize = Integer.parseInt(queryDiscussCaseListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(currPage);
        page.setPageSize(pageSize);

        QueryDiscussCaseDto queryDiscussCaseDto = new QueryDiscussCaseDto();

        queryDiscussCaseDto.setUserId(userId);

        if (Constants.Range.ONE_BY_ONE_BY_ONE.equals(type)){
            queryDiscussCaseDto.setCaseRange(Constants.Range.ONE_BY_ONE_BY_ONE);
            queryDiscussCaseDto.setDoctorRange(Constants.Range.ONE_BY_ONE_BY_ONE);
        }else if (Constants.Range.ONE_BY_ONE_BY_ONE.equals(subType)){
            queryDiscussCaseDto.setCaseRange(Constants.Range.ALL);
            queryDiscussCaseDto.setDoctorRange(Constants.Range.ONE_BY_ONE_BY_ONE);
        }else{
            queryDiscussCaseDto.setCaseRange(Constants.Range.ALL);
        }

        List<DiscussCaseInfoDto> discussCaseInfoDtoList = discussCaseService.selectListByPage(queryDiscussCaseDto,page);

        List<DiscussCaseItem> discussCaseItemList = new ArrayList<>();

        DiscussCaseItem discussCaseItem = null;

        if (CollectionUtils.isEmpty(discussCaseInfoDtoList)){

            queryDiscussCaseListResponse.setList(discussCaseItemList);
            queryDiscussCaseListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
            queryDiscussCaseListResponse.setPageSize(String.valueOf(page.getPageSize()));
            queryDiscussCaseListResponse.setTotalCount(String.valueOf(page.getCount()));

            response.setParameter(queryDiscussCaseListResponse);
            return response;
        }

        for (DiscussCaseInfoDto discussCaseInfoDto : discussCaseInfoDtoList){

            discussCaseItem = new DiscussCaseItem();

            discussCaseItem.setDoctorId(String.valueOf(discussCaseInfoDto.getUserId()));
            discussCaseItem.setPhoto(discussCaseInfoDto.getAvatar());
            discussCaseItem.setDoctorName(discussCaseInfoDto.getDoctorName());
            discussCaseItem.setHospital(discussCaseInfoDto.getHospital());
            discussCaseItem.setRecordId(String.valueOf(discussCaseInfoDto.getId()));
            discussCaseItem.setRecordTime(DateUtil.format(discussCaseInfoDto.getCreateTime(),DateUtil.yyyyMMddHHmmss));
            discussCaseItem.setRecordType(discussCaseInfoDto.getCaseRange());
            discussCaseItem.setDiscussTopic(discussCaseInfoDto.getCaseType());
            discussCaseItem.setPatientAge(String.valueOf(discussCaseInfoDto.getPatientAge()));
            discussCaseItem.setPatientSex(String.valueOf(discussCaseInfoDto.getPatientSex()));
            discussCaseItem.setDescription(discussCaseInfoDto.getCaseDesc());
            discussCaseItem.setMainDesc(discussCaseInfoDto.getMainDesc());
            discussCaseItem.setNowIllness(discussCaseInfoDto.getNowIllness());
            discussCaseItem.setHistoryIllness(discussCaseInfoDto.getHisIllness());
            discussCaseItem.setHealthCheck(discussCaseInfoDto.getHealthCheck());
            discussCaseItem.setAssistCheck(discussCaseInfoDto.getAssistCheck());

            if (StringUtils.isNotBlank(discussCaseInfoDto.getImageList())){

                String [] imageList = discussCaseInfoDto.getImageList().split(",");

                List<ImageItem> imageItemList = new ArrayList<>();

                ImageItem imageItem = null;

                for (String imageId : imageList){

                    imageItem = new ImageItem();

                    imageItem.setImageId(imageId);

                    imageItemList.add(imageItem);
                }

                discussCaseItem.setImageList(imageItemList);

            }

            discussCaseItem.setVideoId(discussCaseInfoDto.getVideoId());
            discussCaseItem.setQuestion(discussCaseInfoDto.getMainQuestion());
            discussCaseItem.setReadCount(String.valueOf(discussCaseInfoDto.getReadCount()));
            discussCaseItem.setCommentCount(String.valueOf(discussCaseInfoDto.getCommentCount()));
            discussCaseItem.setLikeCount(String.valueOf(discussCaseInfoDto.getLikeCount()));
            discussCaseItem.setCollectionCount(String.valueOf(discussCaseInfoDto.getCollectionCount()));

            discussCaseItemList.add(discussCaseItem);
        }

        queryDiscussCaseListResponse.setList(discussCaseItemList);
        queryDiscussCaseListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
        queryDiscussCaseListResponse.setPageSize(String.valueOf(page.getPageSize()));
        queryDiscussCaseListResponse.setTotalCount(String.valueOf(page.getCount()));

        response.setParameter(queryDiscussCaseListResponse);

        return response;
    }
}
