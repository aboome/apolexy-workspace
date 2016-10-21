package com.yh.apoplexy.assist.utils;

import com.yh.apoplexy.assist.service.intf.OperateLogService;
import com.yjh.framework.lang.Result;
import com.yjh.framework.web.util.SpringUtil;

/**
 * 操作日志工具类
 * Created by wunder on 2016/10/9 22:00.
 */
public class OperateLogUtil {

    /**
     * 新增操作日志
     * @param model
     * @param operateEnum
     * @param userId
     * @param userName
     * @param operateDesc
     */
    public static Result addOperateLog(String model, String operateEnum, Long userId, String userName, String operateDesc){

        OperateLogService operateLogService = (OperateLogService)SpringUtil.getBean("operateLogService");

        return operateLogService.addOperateLog(model,operateEnum,userId,userName,operateDesc);

    }
}
