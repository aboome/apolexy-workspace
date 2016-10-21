package com.yh.apoplexy.doctor.open.processor.cases.discuss;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.DiscussCaseInfoDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.QueryDiscussCaseDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.cases.discuss.service.intf.DiscussCaseService;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussCaseItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ImageItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.discuss.QueryMyPartakeCaseListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.QueryMyPartakeCaseListResponse;
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
 * 我的-我参与的列表(doc-0015)处理类
 * Created by wunder on 16/9/8 00:42.
 */
public class QueryMyPartakeCaseListProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMyPartakeCaseListProcessor.class);

    @Autowired
    private DiscussCaseService discussCaseService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMyPartakeCaseListResponse queryMyPartakeCaseListResponse = new QueryMyPartakeCaseListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMyPartakeCaseListRequest queryMyPartakeCaseListRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryMyPartakeCaseListRequest.class);

        if (null == queryMyPartakeCaseListRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryMyPartakeCaseListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryMyPartakeCaseListRequest.getUserId());

        int currPage = Integer.parseInt(queryMyPartakeCaseListRequest.getCurPage());

        int pageSize = Integer.parseInt(queryMyPartakeCaseListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(currPage);
        page.setPageSize(pageSize);

        QueryDiscussCaseDto queryDiscussCaseDto = new QueryDiscussCaseDto();

        queryDiscussCaseDto.setUserId(userId);

        List<DiscussCaseInfoDto> myPostCaseInfoDtoList = discussCaseService.queryMyPartakeListByPage(queryDiscussCaseDto,page);

        List<DiscussCaseItem> myPostCaseItemList = new ArrayList<>();

        DiscussCaseItem discussCaseItem = null;

        if (CollectionUtils.isEmpty(myPostCaseInfoDtoList)){

            queryMyPartakeCaseListResponse.setList(myPostCaseItemList);
            queryMyPartakeCaseListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
            queryMyPartakeCaseListResponse.setPageSize(String.valueOf(page.getPageSize()));
            queryMyPartakeCaseListResponse.setTotalCount(String.valueOf(page.getCount()));

            response.setParameter(queryMyPartakeCaseListResponse);
            return response;
        }

        for (DiscussCaseInfoDto discussCaseInfoDto : myPostCaseInfoDtoList){

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

            myPostCaseItemList.add(discussCaseItem);
        }

        queryMyPartakeCaseListResponse.setList(myPostCaseItemList);
        queryMyPartakeCaseListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
        queryMyPartakeCaseListResponse.setPageSize(String.valueOf(page.getPageSize()));
        queryMyPartakeCaseListResponse.setTotalCount(String.valueOf(page.getCount()));

        response.setParameter(queryMyPartakeCaseListResponse);

        return response;

    }
}