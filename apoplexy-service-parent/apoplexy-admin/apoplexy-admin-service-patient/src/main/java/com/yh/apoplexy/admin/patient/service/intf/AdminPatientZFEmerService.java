package com.yh.apoplexy.admin.patient.service.intf;

import java.util.List;




import com.yh.apoplexy.assist.dmo.patient.health.PatientEmergencyDmo;
import com.yh.apoplexy.assist.dto.admin.patient.emergency.PatientEmergencyDto;
import com.yh.apoplexy.assist.dto.admin.patient.emergency.PatientEmergencyInputDto;
import com.yjh.framework.page.Page;

/**
 * 
 * 中风急救管理
 * @author zhangbiao
 *
 */
public interface AdminPatientZFEmerService {
	   /**
	    * 
	    * 查询中风急救列表
	    * @param con
	    * @param page
	    * @return
	    */
       public List<PatientEmergencyDto> queryZFEmerList(PatientEmergencyInputDto con, Page page);
       
       /**
        * 
        * 查询中风急救详情
        * @param con
        * @return
        */
       public  PatientEmergencyDmo queryZFEmerDetail(PatientEmergencyDmo con);
}
