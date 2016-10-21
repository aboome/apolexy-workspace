package com.yh.apoplexy.admin.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.RegisterProtocolDmo;
import com.yjh.framework.lang.Result;

public interface AdminRegisterProtocolService {

    /**
     * 查询注册协议
     *
     * @param registerProtocolDmo
     * @return
     */
    public RegisterProtocolDmo selectOne(RegisterProtocolDmo registerProtocolDmo);

    /**
     * 更新注册协议
     *
     * @param registerProtocolDmo
     * @return
     */
    public Result update(RegisterProtocolDmo registerProtocolDmo);
}
