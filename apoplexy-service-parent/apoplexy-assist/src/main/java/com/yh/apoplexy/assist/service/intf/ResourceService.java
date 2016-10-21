/**
 * 
 */
package com.yh.apoplexy.assist.service.intf;

import com.yjh.framework.lang.Result;

/**
 * 资源操作类
 * 
 * @author CC
 * 
 */
public interface ResourceService {

	/**
	 * 新增资源
	 * 
	 * @param resourceUuid
	 * @return
	 */
	public Result addResource(String resourceUuid, String resourceName, String resourceType);
}
