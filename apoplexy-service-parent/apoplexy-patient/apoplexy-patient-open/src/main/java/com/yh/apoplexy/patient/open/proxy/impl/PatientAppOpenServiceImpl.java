package com.yh.apoplexy.patient.open.proxy.impl;

import com.yh.apoplexy.patient.open.processor.base.PatientAppBaseServiceProcessor;
import com.yh.apoplexy.patient.open.proxy.intf.PatientAppOpenService;

import java.util.Map;

/**
 * 患者端APP开放服务实现类
 * Created by wunder on 16/9/4 14:22.
 */
public class PatientAppOpenServiceImpl implements PatientAppOpenService {

    private Map<String, PatientAppBaseServiceProcessor> processorList = null;

    @Override
    public String proxy(String requestString, String serviceKey) {

        PatientAppBaseServiceProcessor processor = processorList.get(serviceKey);

        if (null == processor) {
            return "{\"respCode\":\"0099\"}";
        }

        return processor.process(requestString);
    }

    public Map<String, PatientAppBaseServiceProcessor> getProcessorList() {
        return processorList;
    }

    public void setProcessorList(Map<String, PatientAppBaseServiceProcessor> processorList) {
        this.processorList = processorList;
    }
}
