package com.yh.apoplexy.doctor.open.processor.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.common.BannerInfoDmo;
import com.yh.apoplexy.base.service.intf.BannerInfoService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.open.dto.common.BannerItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.common.QueryBannerRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.common.QueryBannerResponse;
import com.yjh.framework.lang.Result;
import com.yjh.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页推荐位查询(common-0008)处理类
 * Created by wunder on 16/9/4 09:42.
 */
public class QueryBannerProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryBannerProcessor.class);

    @Autowired
    private BannerInfoService bannerInfoService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryBannerResponse queryBannerResponse = new QueryBannerResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(),DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryBannerRequest queryBannerRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryBannerRequest.class);

        //参数错误
        if (null == queryBannerRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryBannerRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        BannerInfoDmo con = new BannerInfoDmo();

        con.setOwner(queryBannerRequest.getType());
        con.setStatus(Constants.BannerStatus.NORMAL);

        List<BannerInfoDmo> bannerInfoDmoList = bannerInfoService.selectList(con);

        if (CollectionUtils.isEmpty(bannerInfoDmoList)){

            response.setParameter(queryBannerResponse);

            return response;

        }

        List<BannerItem> bannerItemList = new ArrayList<>();

        BannerItem bannerItem = null;

        for (BannerInfoDmo bannerInfoDmo : bannerInfoDmoList){

            bannerItem = new BannerItem();

            bannerItem.setBannerId(String.valueOf(bannerInfoDmo.getId()));
            bannerItem.setTitle(bannerInfoDmo.getTitle());
            bannerItem.setImageId(bannerInfoDmo.getImageId());
            bannerItem.setUrl(bannerInfoDmo.getUrl());
            bannerItem.setSort(String.valueOf(bannerInfoDmo.getSort()));

            bannerItemList.add(bannerItem);

        }

        queryBannerResponse.setList(bannerItemList);

        response.setParameter(queryBannerResponse);

        return response;
    }
}
