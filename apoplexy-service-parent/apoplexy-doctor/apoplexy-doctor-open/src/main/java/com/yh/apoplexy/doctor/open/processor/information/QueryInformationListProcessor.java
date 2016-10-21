package com.yh.apoplexy.doctor.open.processor.information;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.information.DoctorNewsDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.DoctorConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.information.service.intf.DoctorInformationService;
import com.yh.apoplexy.doctor.open.dto.information.InformationItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.information.QueryInformationListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.information.QueryInformationListResponse;
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
 * 查询最新资讯列表(doc-0051)处理类
 * Created by wunder on 16/9/6 18:37.
 */
public class QueryInformationListProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryInformationListProcessor.class);

    @Autowired
    private DoctorInformationService doctorInformationService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryInformationListResponse queryInformationListResponse = new QueryInformationListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryInformationListRequest queryInformationListRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryInformationListRequest.class);

        if (null == queryInformationListRequest) {

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryInformationListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        int pageNum = Integer.parseInt(queryInformationListRequest.getCurPage());
        int pageSize = Integer.parseInt(queryInformationListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);

        DoctorNewsDmo con = new DoctorNewsDmo();

        con.setStatus(DoctorConstants.NewsStatus.NORMAL);
        con.setNewType(DoctorConstants.NewsClass.NEWS);

        List<DoctorNewsDmo> doctorNewsDmoList = doctorInformationService.selectListByPage(con,page);

        if (CollectionUtils.isEmpty(doctorNewsDmoList)){

            response.setParameter(queryInformationListResponse);
            return response;
        }

        List<InformationItem> informationItemList = new ArrayList<>();

        InformationItem informationItem = null;

        for (DoctorNewsDmo doctorNewsDmo : doctorNewsDmoList){

            informationItem = new InformationItem();

            informationItem.setNewId(String.valueOf(doctorNewsDmo.getId()));
            informationItem.setType(doctorNewsDmo.getType());
            informationItem.setTitle(doctorNewsDmo.getTitle());
            informationItem.setSub(doctorNewsDmo.getSubTitle());
            informationItem.setSmallImage(doctorNewsDmo.getSmallLogo());
            informationItem.setDate(DateUtil.format(doctorNewsDmo.getCreateTime(),DateUtil.yyyyMMddHHmmss));

            informationItemList.add(informationItem);
        }

        queryInformationListResponse.setList(informationItemList);
        queryInformationListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
        queryInformationListResponse.setPageSize(String.valueOf(page.getPageSize()));
        queryInformationListResponse.setTotal(String.valueOf(page.getCount()));

        response.setParameter(queryInformationListResponse);

        return response;
    }
}
