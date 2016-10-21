package com.yh.apoplexy.base.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.RegisterProtocolDmo;
import com.yh.apoplexy.base.service.intf.RegisterProtocolService;
import com.yjh.framework.core.trace.ServiceTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注册协议服务实现类
 * Created by wunder on 2016/10/9 19:39.
 */
@Service("registerProtocolService")
@ServiceTrace
public class RegisterProtocolServiceImpl implements RegisterProtocolService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public RegisterProtocolDmo selectOne(RegisterProtocolDmo con) {
        return (RegisterProtocolDmo)commonDao.selectOne(con);
    }
}
