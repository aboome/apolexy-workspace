package com.yh.apoplexy.doctor.open.proxy.intf;

/**
 * 医生端APP开放接口服务
 * Created by wunder on 16/9/3 13:21.
 */
public interface DoctorAppOpenService {

	/**
	 * 医生端APP统一接口处理类
	 * @param requestString
	 * @param serviceKey
	 * @return
	 */
	public String proxy(String requestString, String serviceKey);
	
}
