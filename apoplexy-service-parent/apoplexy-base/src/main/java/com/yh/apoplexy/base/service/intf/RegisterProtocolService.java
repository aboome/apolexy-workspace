package com.yh.apoplexy.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.RegisterProtocolDmo;

/**
 * 注册协议服务接口类
 * Created by wunder on 2016/10/9 19:38.
 */
public interface RegisterProtocolService {

    /**
     * 查找注册协议信息
     * @param con
     * @return
     */
    public RegisterProtocolDmo selectOne(RegisterProtocolDmo con);
}
