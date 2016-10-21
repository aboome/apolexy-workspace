package com.yh.apoplexy.doctor.open.processor.information;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.information.DoctorNewsDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.information.service.intf.DoctorInformationService;
import com.yh.apoplexy.doctor.open.dto.information.InformationItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.information.QueryKnowledgeListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.information.QueryKnowledgeListResponse;
import com.yjh.framework.lang.AppException;
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
 * 查询学术更新列表(doc-0049)处理类
 * Created by wunder on 16/9/6 18:38.
 */
public class QueryKnowledgeListProcessor extends DoctorAppBaseServiceProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryKnowledgeListProcessor.class);

    @Autowired
    private DoctorInformationService doctorInformationService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryKnowledgeListResponse queryKnowledgeListResponse = new QueryKnowledgeListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryKnowledgeListRequest queryKnowledgeListRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryKnowledgeListRequest.class);

        if (null == queryKnowledgeListRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryKnowledgeListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        int pageNum = Integer.parseInt(queryKnowledgeListRequest.getCurPage());
        int pageSize = Integer.parseInt(queryKnowledgeListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);

        DoctorNewsDmo con = new DoctorNewsDmo();

        con.setStatus(DoctorConstants.NewsStatus.NORMAL);
        con.setNewType(DoctorConstants.NewsClass.KNOWLEDGE);

        List<DoctorNewsDmo> doctorNewsDmoList = doctorInformationService.selectListByPage(con, page);

        if (CollectionUtils.isEmpty(doctorNewsDmoList)) {

            response.setParameter(queryKnowledgeListResponse);
            return response;

        }

        List<InformationItem> knowledgeItemList = new ArrayList<>();

        InformationItem knowledgeItem = null;

        for (DoctorNewsDmo doctorNewsDmo : doctorNewsDmoList) {

            knowledgeItem = new InformationItem();

            knowledgeItem.setNewId(String.valueOf(doctorNewsDmo.getId()));
            knowledgeItem.setType(doctorNewsDmo.getType());
            knowledgeItem.setTitle(doctorNewsDmo.getTitle());
            knowledgeItem.setSub(doctorNewsDmo.getSubTitle());
            knowledgeItem.setSmallImage(doctorNewsDmo.getSmallLogo());
            knowledgeItem.setDate(DateUtil.format(doctorNewsDmo.getCreateTime(), DateUtil.yyyyMMddHHmmss));

            knowledgeItemList.add(knowledgeItem);
        }

        queryKnowledgeListResponse.setList(knowledgeItemList);
        queryKnowledgeListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
        queryKnowledgeListResponse.setPageSize(String.valueOf(page.getPageSize()));
        queryKnowledgeListResponse.setTotal(String.valueOf(page.getCount()));

        response.setParameter(queryKnowledgeListResponse);

        return response;
    }

}
