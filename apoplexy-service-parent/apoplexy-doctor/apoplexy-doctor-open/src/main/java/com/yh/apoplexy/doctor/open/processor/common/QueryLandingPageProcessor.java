package com.yh.apoplexy.doctor.open.processor.common;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.LandingPageDmo;
import com.yh.apoplexy.base.service.intf.LandingPageInfoService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.open.dto.common.LandingPageItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.common.QueryLandingPageRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.common.QueryLandingPageResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询开机动画(common-0007)处理类
 * Created by wunder on 16/9/4 11:07.
 */
public class QueryLandingPageProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryLandingPageProcessor.class);

    @Autowired
    private LandingPageInfoService landingPageInfoService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryLandingPageResponse queryLandingPageResponse = new QueryLandingPageResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryLandingPageRequest queryLandingPageRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryLandingPageRequest.class);

        //参数错误
        if (null == queryLandingPageRequest) {
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryLandingPageRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        LandingPageDmo con = new LandingPageDmo();

        con.setStatus(Constants.StartPictureStatus.NORMAL);
        con.setOwner(queryLandingPageRequest.getType());

        List<LandingPageDmo> landingPageDmoList = landingPageInfoService.selectList(con);

        if (CollectionUtils.isEmpty(landingPageDmoList)) {

            response.setParameter(queryLandingPageResponse);

            return response;

        }

        List<LandingPageItem> landingPageItemList = new ArrayList<>();

        LandingPageItem landingPageItem = null;

        for (LandingPageDmo landingPageDmo : landingPageDmoList) {

            landingPageItem = new LandingPageItem();

            landingPageItem.setImageId(landingPageDmo.getImageId());
            landingPageItem.setSort(String.valueOf(landingPageDmo.getSort()));

            landingPageItemList.add(landingPageItem);

        }

        queryLandingPageResponse.setList(landingPageItemList);

        response.setParameter(queryLandingPageResponse);

        return response;
    }


}
