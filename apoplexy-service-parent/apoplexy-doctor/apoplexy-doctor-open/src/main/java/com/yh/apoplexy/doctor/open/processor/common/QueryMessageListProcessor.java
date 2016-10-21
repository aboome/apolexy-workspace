package com.yh.apoplexy.doctor.open.processor.common;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMessageDmo;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.utils.CommonValidator;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMessageService;
import com.yh.apoplexy.doctor.open.dto.common.MessageDetailItem;
import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.request.base.DoctorAppBaseRequest;
import com.yh.apoplexy.doctor.open.request.member.QueryMessageListRequest;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.member.QueryMessageListResponse;
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
 * 查询通知消息列表(doc-0059)处理类
 * Created by wunder on 16/9/10 18:33.
 */
public class QueryMessageListProcessor extends DoctorAppBaseServiceProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMessageListProcessor.class);

    @Autowired
    private DoctorMessageService doctorMessageService;

    @Override
    protected DoctorAppBaseResponse doProcess(DoctorAppBaseRequest requestObject) {

        QueryMessageListResponse queryMessageListResponse = new QueryMessageListResponse();

        DoctorAppBaseResponse response = new DoctorAppBaseResponse();
        response.setServicekey(requestObject.getServicekey());
        response.setUid(requestObject.getUid());
        response.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
        response.setResultcode(APPResponseCodeConstants.SUCCESS);

        QueryMessageListRequest queryMessageListRequest = JSONObject.parseObject(requestObject.getParameter().toString(), QueryMessageListRequest.class);

        //参数错误
        if (null == queryMessageListRequest) {
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Result validateResult = CommonValidator.validate(queryMessageListRequest);
        if (!validateResult.isSuccess()) {
            LOGGER.error("请求参数错误");
            response.setResultcode(APPResponseCodeConstants.FAILED_PARAMETER_ERROR);
            return response;
        }

        Long userId = Long.parseLong(queryMessageListRequest.getUserId());

        String type = queryMessageListRequest.getType();

        int pageNum = Integer.parseInt(queryMessageListRequest.getPageNum());

        int pageSize = Integer.parseInt(queryMessageListRequest.getPageSize());

        DoctorMessageDmo con = new DoctorMessageDmo();

        con.setUserId(userId);

        if(!Constants.DoctorMessageType.ALL.equals(type)){
            con.setType(type);
        }

        Page page = new Page();

        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);

        List<DoctorMessageDmo> messageDmoList = doctorMessageService.selectListByPage(con,page);

        if (CollectionUtils.isEmpty(messageDmoList)) {

            response.setParameter(queryMessageListResponse);

            return response;

        }

        List<MessageDetailItem> messageDetailItemList = new ArrayList<>();

        MessageDetailItem messageDetailItem = null;

        for (DoctorMessageDmo messageDmo : messageDmoList) {

            messageDetailItem = new MessageDetailItem();

            messageDetailItem.setMessageId(String.valueOf(messageDmo.getId()));
            messageDetailItem.setType(messageDmo.getType());
            messageDetailItem.setMessageTitle(messageDmo.getTitle());
            messageDetailItem.setMessageContent(messageDmo.getContent());
            messageDetailItem.setMessageTime(DateUtil.format(messageDmo.getTime(),DateUtil.yyyyMMddHHmmss));

            messageDetailItemList.add(messageDetailItem);

        }

        queryMessageListResponse.setList(messageDetailItemList);
        queryMessageListResponse.setTotal(String.valueOf(page.getCount()));
        queryMessageListResponse.setPageNum(String.valueOf(page.getCurrentPage()));
        queryMessageListResponse.setPageSize(String.valueOf(page.getPageSize()));

        response.setParameter(queryMessageListResponse);

        return response;

    }
}
