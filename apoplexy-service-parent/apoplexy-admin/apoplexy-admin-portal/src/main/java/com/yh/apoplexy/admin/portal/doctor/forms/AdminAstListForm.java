package com.yh.apoplexy.admin.portal.doctor.forms;
/**
 * 
 * 查询Ast病例列表
 * @author zhangbiao
 *
 */
public class AdminAstListForm {
	    private String id;
	    
	    private String doctorName;
	    
	    private String hospitalName;
	    
	    private String startTime;
	    
	    private String endTime;
	    
	    private String pageNum;
	    
	    private String pageSize;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDoctorName() {
			return doctorName;
		}

		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}

		public String getHospitalName() {
			return hospitalName;
		}

		public void setHospitalName(String hospitalName) {
			this.hospitalName = hospitalName;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public String getPageNum() {
			return pageNum;
		}

		public void setPageNum(String pageNum) {
			this.pageNum = pageNum;
		}

		public String getPageSize() {
			return pageSize;
		}

		public void setPageSize(String pageSize) {
			this.pageSize = pageSize;
		}
	    
	    
	    

}
