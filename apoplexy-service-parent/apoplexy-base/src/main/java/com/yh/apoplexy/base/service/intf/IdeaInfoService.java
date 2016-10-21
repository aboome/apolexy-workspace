package com.yh.apoplexy.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.IdeaInfoDmo;
import com.yjh.framework.lang.Result;

/**
 * 意见反馈服务接口类
 * Created by wunder on 16/9/7 00:47.
 */
public interface IdeaInfoService {

    /**
     * 新增意见反馈信息
     * @param ideaInfoDmo
     * @return
     */
    public Result insert(IdeaInfoDmo ideaInfoDmo);
}
