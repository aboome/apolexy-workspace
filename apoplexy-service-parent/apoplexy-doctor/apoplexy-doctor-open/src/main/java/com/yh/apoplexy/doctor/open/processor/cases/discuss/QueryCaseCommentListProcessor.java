package com.yh.apoplexy.doctor.open.processor.cases.discuss;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseCommentDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.CaseCommentDto;
import com.yh.apoplexy.assist.dto.doctor.cases.discuss.ChildCaseCommentDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.discuss.service.intf.CaseCommentService;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.ChildDiscussDetailItem;
import com.yh.apoplexy.doctor.open.dto.cases.discuss.DiscussDetailItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.discuss.QueryCaseCommentListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.discuss.QueryCaseCommentListResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询病例讨论评论列表(doc-0062)处理类
 * Created by wunder on 16/9/10 19:24.
 */
public class QueryCaseCommentListProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryCaseCommentListProcessor.class);

    @Autowired
    private CaseCommentService caseCommentService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryCaseCommentListResponse queryCaseCommentListResponse = new QueryCaseCommentListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryCaseCommentListRequest queryCaseCommentListRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryCaseCommentListRequest.class);

        if (null == queryCaseCommentListRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryCaseCommentListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryCaseCommentListRequest.getRecordId());

        int pageNum = Integer.parseInt(queryCaseCommentListRequest.getPageNum());

        int pageSize = Integer.parseInt(queryCaseCommentListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);

        CaseCommentDmo con = new CaseCommentDmo();

        con.setRecordId(recordId);

        List<CaseCommentDto> caseCommentDtoList = caseCommentService.queryCommentList(con,page);

        if (CollectionUtils.isEmpty(caseCommentDtoList)){

            response.setParameter(caseCommentDtoList);
            return response;

        }

        List<DiscussDetailItem> commentItemList = new ArrayList<>();

        DiscussDetailItem commentItem = null;

        for (CaseCommentDto caseCommentDto: caseCommentDtoList){

            List<ChildDiscussDetailItem> childCommentList = new ArrayList<>();

            ChildDiscussDetailItem childCommentItem = null;

            if (!CollectionUtils.isEmpty(caseCommentDto.getChildDiscussList())){

                for (ChildCaseCommentDto childCaseCommentDto:caseCommentDto.getChildDiscussList()){

                    childCommentItem = new ChildDiscussDetailItem();

                    childCommentItem.setDiscussId(childCaseCommentDto.getDiscussId());
                    childCommentItem.setFromDoctorId(childCaseCommentDto.getFromDoctorId());
                    childCommentItem.setFromDoctor(childCaseCommentDto.getFromDoctor());
                    childCommentItem.setToDoctorId(childCaseCommentDto.getToDoctorId());
                    childCommentItem.setToDoctor(childCaseCommentDto.getToDoctor());
                    childCommentItem.setDiscussContent(childCaseCommentDto.getDiscussContent());

                    childCommentList.add(childCommentItem);
                }

            }

            commentItem = new DiscussDetailItem();

            commentItem.setDiscussId(String.valueOf(caseCommentDto.getDiscussId()));
            commentItem.setDoctorId(caseCommentDto.getDoctorId());
            commentItem.setDoctorName(caseCommentDto.getDoctorName());
            commentItem.setDoctorPhoto(caseCommentDto.getDoctorPhoto());
            commentItem.setHospital(caseCommentDto.getHospital());
            commentItem.setDiscussTime(DateUtil.format(caseCommentDto.getDiscussTime(),DateUtil.yyyyMMddHHmmss));
            commentItem.setDiscussContent(caseCommentDto.getDiscussContent());
            commentItem.setChildDiscussList(childCommentList);

            commentItemList.add(commentItem);

        }

        queryCaseCommentListResponse.setDiscussList(commentItemList);
        queryCaseCommentListResponse.setTotal(String.valueOf(page.getCount()));
        queryCaseCommentListResponse.setPageNum(String.valueOf(page.getCurrentPage()));
        queryCaseCommentListResponse.setPageSize(String.valueOf(page.getPageSize()));

        response.setParameter(queryCaseCommentListResponse);

        return response;
    }
}
