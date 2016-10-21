package com.yh.apoplexy.patient.open.proxy.intf;

/**
 * 患者端APP开放服务接口
 * Created by wunder on 16/9/4 14:20.
 */
public interface PatientAppOpenService {

    /**
     * 患者端APP统一接口处理类
     * @param requestString
     * @param serviceKey
     * @return
     */
    public String proxy(String requestString, String serviceKey);
}
