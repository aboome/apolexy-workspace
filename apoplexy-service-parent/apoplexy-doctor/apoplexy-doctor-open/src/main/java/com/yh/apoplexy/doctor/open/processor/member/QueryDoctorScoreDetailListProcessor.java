package com.yh.apoplexy.doctor.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorScoreDetailDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorScoreService;
import com.yh.apoplexy.doctor.open.dto.member.ScoreDetailItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.member.QueryDoctorScoreDetailListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.member.QueryDoctorScoreDetailListResponse;
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
 * 个人中心-积分明细列表(doc-0057)处理类
 * Created by wunder on 16/9/6 19:51.
 */
public class QueryDoctorScoreDetailListProcessor extends DoctorAppBaseServiceProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDoctorScoreDetailListProcessor.class);

    @Autowired
    private DoctorScoreService doctorScoreService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryDoctorScoreDetailListResponse queryDoctorScoreDetailListResponse = new QueryDoctorScoreDetailListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryDoctorScoreDetailListRequest queryDoctorScoreDetailListRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryDoctorScoreDetailListRequest.class);

        if (null == queryDoctorScoreDetailListRequest){

            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;

        }

        Result validateResult = CommonValidator.validate(queryDoctorScoreDetailListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryDoctorScoreDetailListRequest.getUserId());

        int pageNum = Integer.parseInt(queryDoctorScoreDetailListRequest.getCurPage());

        int pageSize = Integer.parseInt(queryDoctorScoreDetailListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);

        DoctorScoreDetailDmo con = new DoctorScoreDetailDmo();

        con.setUserId(userId);

        List<DoctorScoreDetailDmo> doctorScoreDetailDmoList = doctorScoreService.selectListByPage(con,page);

        List<ScoreDetailItem> scoreDetailItemList = new ArrayList<>();

        ScoreDetailItem scoreDetailItem = null;

        if (CollectionUtils.isEmpty(doctorScoreDetailDmoList)){

            queryDoctorScoreDetailListResponse.setList(scoreDetailItemList);
            queryDoctorScoreDetailListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
            queryDoctorScoreDetailListResponse.setPageSize(String.valueOf(page.getPageSize()));
            queryDoctorScoreDetailListResponse.setTotal(String.valueOf(page.getCount()));

            response.setParameter(queryDoctorScoreDetailListResponse);

            return response;

        }

        for (DoctorScoreDetailDmo doctorScoreDetailDmo : doctorScoreDetailDmoList){

            scoreDetailItem = new ScoreDetailItem();

            scoreDetailItem.setRecordId(String.valueOf(doctorScoreDetailDmo.getId()));
            scoreDetailItem.setRecordDate(DateUtil.format(doctorScoreDetailDmo.getTime(),DateUtil.yyyyMMddHHmmss));
            scoreDetailItem.setRecordScore(String.valueOf(doctorScoreDetailDmo.getScore()));

            scoreDetailItemList.add(scoreDetailItem);
        }

        queryDoctorScoreDetailListResponse.setList(scoreDetailItemList);
        queryDoctorScoreDetailListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
        queryDoctorScoreDetailListResponse.setPageSize(String.valueOf(page.getPageSize()));
        queryDoctorScoreDetailListResponse.setTotal(String.valueOf(page.getCount()));

        response.setParameter(queryDoctorScoreDetailListResponse);

        return response;

    }
}
