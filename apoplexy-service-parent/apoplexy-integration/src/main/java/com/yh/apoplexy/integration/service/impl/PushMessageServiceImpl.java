/**
 * 
 */
package com.yh.apoplexy.integration.service.impl;

import java.util.List;

import com.yjh.framework.push.jpush.dto.MultiJPushMessageDto;
import com.yjh.framework.push.jpush.dto.SingleJPushMessageDto;
import com.yjh.framework.push.jpush.utils.JPushMessageUtils;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.common.constants.PropertiesConstants;
import com.yh.apoplexy.common.utils.PropertiesUtil;
import com.yh.apoplexy.integration.service.intf.PushMessageService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;

/**
 * 消息推送服务
 * 
 * @author CC
 * 
 */
@Service("pushMessageService")
@ServiceTrace
public class PushMessageServiceImpl implements PushMessageService {

	@Override
	public Result pushSingleMessage(String clientId, String title, String text, String logo, String logoUrl, String url) {

		Result result = new Result();

		SingleJPushMessageDto messageDto = new SingleJPushMessageDto();

        messageDto.setMasterSecret(PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.YH_JPUSH_MASTERSECRET));
        messageDto.setAppKey(PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.YH_JPUSH_APPKEY));
        messageDto.setAlert(title);
        messageDto.setRegistrationId(clientId);
		
		boolean pushResult = JPushMessageUtils.sendSingleMessage(messageDto);

		result.setSuccess(pushResult);
		
		return result;
	}

	@Override
	public Result pushMultiMessage(List<String> clientIdList, String title, String text, String logo, String logoUrl, String url) {

        Result result = new Result();

		MultiJPushMessageDto messageDto = new MultiJPushMessageDto();

        messageDto.setMasterSecret(PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.YH_JPUSH_MASTERSECRET));
        messageDto.setAppKey(PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.YH_JPUSH_APPKEY));
        messageDto.setAlert(title);
        messageDto.setRegistrationIdList(clientIdList);
		
		boolean pushResult = JPushMessageUtils.sendMultiMessage(messageDto);

		result.setSuccess(pushResult);
		
		return result;
	}

}
