package com.yh.apoplexy.patient.open.processor.member;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.patient.member.PatientScoreDetailDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.patient.member.service.intf.PatientScoreService;
import com.yh.apoplexy.patient.open.dto.member.ScoreDetailItem;
import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.request.base.PatientAppBaseRequest;
import com.yh.apoplexy.patient.open.request.member.QueryScoreDetailListRequest;
import com.yh.apoplexy.patient.open.response.base.PatientAppBaseResponse;
import com.yh.apoplexy.patient.open.response.member.QueryScoreDetailListResponse;
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
 * 个人中心-查询积分明细列表(pat-0018)处理类
 * Created by wunder on 16/9/5 23:32.
 */
public class QueryScoreDetailListProcessor extends PatientAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryScoreDetailListProcessor.class);

    @Autowired
    private PatientScoreService patientScoreService;

    @Override
    protected PatientAppBaseResponse doProcess(PatientAppBaseRequest requestObject) {

        QueryScoreDetailListResponse queryScoreDetailListResponse = new QueryScoreDetailListResponse();

        PatientAppBaseResponse response = new PatientAppBaseResponse();

        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryScoreDetailListRequest queryScoreDetailListRequest = JSONObject.parseObject(requestObject.getParameter().toString(),QueryScoreDetailListRequest.class);

        //参数校验
        if (null == queryScoreDetailListRequest){
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryScoreDetailListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryScoreDetailListRequest.getUserId());

        int pageNum = Integer.parseInt(queryScoreDetailListRequest.getCurPage());

        int pageSize = Integer.parseInt(queryScoreDetailListRequest.getPageSize());

        Page page = new Page();

        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);

        PatientScoreDetailDmo con = new PatientScoreDetailDmo();

        con.setUserId(userId);

        List<PatientScoreDetailDmo> patientScoreDetailDmoList = patientScoreService.selectListByPage(con,page);

        List<ScoreDetailItem> scoreDetailItemList = new ArrayList<>();

        ScoreDetailItem scoreDetailItem = null;

        if (CollectionUtils.isEmpty(patientScoreDetailDmoList)){

            queryScoreDetailListResponse.setList(scoreDetailItemList);
            queryScoreDetailListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
            queryScoreDetailListResponse.setPageSize(String.valueOf(page.getPageSize()));
            queryScoreDetailListResponse.setTotal(String.valueOf(page.getCount()));

            response.setParameter(queryScoreDetailListResponse);

            return response;

        }

        for (PatientScoreDetailDmo patientScoreDetailDmo: patientScoreDetailDmoList){

            scoreDetailItem = new ScoreDetailItem();

            scoreDetailItem.setRecordId(String.valueOf(patientScoreDetailDmo.getId()));
            scoreDetailItem.setRecordDate(DateUtil.format(patientScoreDetailDmo.getTime(),DateUtil.yyyyMMddHHmmss));
            scoreDetailItem.setRecordScore(String.valueOf(patientScoreDetailDmo.getScore()));

            scoreDetailItemList.add(scoreDetailItem);
        }

        queryScoreDetailListResponse.setList(scoreDetailItemList);
        queryScoreDetailListResponse.setCurPage(String.valueOf(page.getCurrentPage()));
        queryScoreDetailListResponse.setPageSize(String.valueOf(page.getPageSize()));
        queryScoreDetailListResponse.setTotal(String.valueOf(page.getCount()));

        response.setParameter(queryScoreDetailListResponse);

        return response;
    }
}
