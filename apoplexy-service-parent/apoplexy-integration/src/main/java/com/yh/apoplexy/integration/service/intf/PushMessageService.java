/**
 * 
 */
package com.yh.apoplexy.integration.service.intf;

import java.util.List;

import com.yjh.framework.lang.Result;

/**
 * 消息推送接口
 * 
 * @author CC
 * 
 */
public interface PushMessageService {
    
	/**
	 * 推送单个用户消息
	 * 
	 * @param clientId
	 * @param title
	 * @param text
	 * @param logo
	 * @param logoUrl
	 * @param url
	 * @return
	 */
	public Result pushSingleMessage(String clientId, String title, String text, String logo, String logoUrl, String url);

	/**
	 * 推送多个用户消息
	 * 
	 * @param clientIdList
	 * @param title
	 * @param text
	 * @param logo
	 * @param logoUrl
	 * @param url
	 * @return
	 */
	public Result pushMultiMessage(List<String> clientIdList, String title, String text, String logo, String logoUrl, String url);

}
