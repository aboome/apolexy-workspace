/**
 * 
 */
package com.yh.apoplexy.assist.service.intf;

import java.util.List;

import com.yh.apoplexy.assist.dmo.common.OperateLogDmo;
import com.yh.apoplexy.assist.dto.admin.log.SystemLogInputDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

/**
 * 操作日志
 * 
 * @author CC
 * 
 */
public interface OperateLogService {

	/**
	 * 新增操作日志
	 * 
	 * @param model
	 * @param operateEnum
	 * @param userId
	 * @param userName
	 * @param operateDesc
	 * @return
	 */
	public Result addOperateLog(String model, String operateEnum, Long userId, String userName, String operateDesc);

	/**
	 * 分页查询系统操作日志
	 * 
	 * @param con
	 * @param page
	 * @return
	 */
	public List<OperateLogDmo> queryOperateLogList(SystemLogInputDto con, Page page);
}
