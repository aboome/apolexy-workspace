package com.yh.apoplexy.integration.service.intf;

import com.yh.apoplexy.assist.dmo.common.ValidateCodeDmo;
import com.yjh.framework.lang.Result;

/**
 * 短信验证码服务接口类
 * Created by wunder on 16/9/3 16:25.
 */
public interface ValidateCodeService {

    /**
     * 发送短信验证码
     *
     * @param validateCodeDmo
     * @param type
     * @return
     */
    public Result sendValidateCode(ValidateCodeDmo validateCodeDmo, String type);

    /**
     * 验证短信验证码
     *
     * @param phone
     * @param code
     * @param invalid
     * @return
     */
    public Result verifyValidateCode(String phone, String code, String invalid);

}
