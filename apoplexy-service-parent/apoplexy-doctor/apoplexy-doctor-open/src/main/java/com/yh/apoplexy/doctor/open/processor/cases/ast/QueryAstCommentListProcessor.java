package com.yh.apoplexy.doctor.open.processor.cases.ast;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesCommentDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.AstCommentDto;
import com.yh.apoplexy.assist.dto.doctor.cases.ast.ChildAstCommentDto;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.discuss.service.intf.AstCommentService;
import com.yh.apoplexy.doctor.open.dto.cases.ast.AstCommentItem;
import com.yh.apoplexy.doctor.open.dto.cases.ast.ChildAstCommentItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.cases.ast.QueryAstCommentListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.cases.ast.QueryAstCommentListResponse;
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
 * 查询AST病例评论列表(doc-0063)处理咧
 * Created by wunder on 16/9/11 10:21.
 */
public class QueryAstCommentListProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryAstCommentListProcessor.class);

    @Autowired
    private AstCommentService astCommentService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryAstCommentListResponse queryAstCommentListResponse = new QueryAstCommentListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryAstCommentListRequest queryAstCommentListRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryAstCommentListRequest.class);

        if (null == queryAstCommentListRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryAstCommentListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long recordId = Long.parseLong(queryAstCommentListRequest.getRecordId());

        int pageNum = Integer.parseInt(queryAstCommentListRequest.getPageNum());

        int pageSize = Integer.parseInt(queryAstCommentListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);

        AstCasesCommentDmo con = new AstCasesCommentDmo();

        con.setRecordId(recordId);

        List<AstCommentDto> astCommentDtoList = astCommentService.queryCommentList(con,page);

        if (CollectionUtils.isEmpty(astCommentDtoList)){

            response.setParameter(astCommentDtoList);
            return response;

        }

        List<AstCommentItem> commentItemList = new ArrayList<>();

        AstCommentItem commentItem = null;

        for (AstCommentDto astCommentDto: astCommentDtoList){

            List<ChildAstCommentItem> childCommentList = new ArrayList<>();

            ChildAstCommentItem childCommentItem = null;

            if (!CollectionUtils.isEmpty(astCommentDto.getChildDiscussList())){

                for (ChildAstCommentDto childAstCommentDto:astCommentDto.getChildDiscussList()){

                    childCommentItem = new ChildAstCommentItem();

                    childCommentItem.setDiscussId(childAstCommentDto.getDiscussId());
                    childCommentItem.setFromDoctorId(childAstCommentDto.getFromDoctorId());
                    childCommentItem.setFromDoctor(childAstCommentDto.getFromDoctor());
                    childCommentItem.setToDoctorId(childAstCommentDto.getToDoctorId());
                    childCommentItem.setToDoctor(childAstCommentDto.getToDoctor());
                    childCommentItem.setDiscussContent(childAstCommentDto.getDiscussContent());

                    childCommentList.add(childCommentItem);
                }

            }

            commentItem = new AstCommentItem();

            commentItem.setDiscussId(String.valueOf(astCommentDto.getDiscussId()));
            commentItem.setDoctorId(astCommentDto.getDoctorId());
            commentItem.setDoctorName(astCommentDto.getDoctorName());
            commentItem.setDoctorPhoto(astCommentDto.getDoctorPhoto());
            commentItem.setHospital(astCommentDto.getHospital());
            commentItem.setDiscussTime(DateUtil.format(astCommentDto.getDiscussTime(),DateUtil.yyyyMMddHHmmss));
            commentItem.setDiscussContent(astCommentDto.getDiscussContent());
            commentItem.setChildCommentList(childCommentList);

            commentItemList.add(commentItem);

        }

        queryAstCommentListResponse.setCommentList(commentItemList);
        queryAstCommentListResponse.setTotal(String.valueOf(page.getCount()));
        queryAstCommentListResponse.setPageNum(String.valueOf(page.getCurrentPage()));
        queryAstCommentListResponse.setPageSize(String.valueOf(page.getPageSize()));

        response.setParameter(queryAstCommentListResponse);

        return response;
    }
}
