package com.yh.apoplexy.doctor.open.proxy.impl;

import com.yh.apoplexy.doctor.open.processor.base.DoctorAppBaseServiceProcessor;
import com.yh.apoplexy.doctor.open.proxy.intf.DoctorAppOpenService;

import java.util.Map;

/**
 * 医生端APP开放服务实现类
 * 
 * Created by wunder on 16/9/3 13:21.
 *
 */
public class DoctorAppOpenServiceImpl implements DoctorAppOpenService {
	
	private Map<String, DoctorAppBaseServiceProcessor> processorList = null;

    /**
     * 医生端APP统一接口处理类
     * @param requestString
     * @param serviceKey
     * @return
     */
	public String proxy(String requestString, String serviceKey) {

        DoctorAppBaseServiceProcessor processor = processorList.get(serviceKey);
		
		if (null == processor) {
			return "{\"respCode\":\"0099\"}";
		}

		return processor.process(requestString);
	}

    public Map<String, DoctorAppBaseServiceProcessor> getProcessorList() {
        return processorList;
    }

    public void setProcessorList(Map<String, DoctorAppBaseServiceProcessor> processorList) {
        this.processorList = processorList;
    }
}
