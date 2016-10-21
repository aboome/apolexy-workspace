package com.yh.apoplexy.admin.base.service.result;

import com.yjh.framework.lang.Result;
/***
 * 导入医生结果
 * @author eyelake
 *
 */
public class ImportDoctorResult extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1812211486292125990L;
	
	    // 导入成功总笔数
		private long successCount;

		// 导入失败总笔数
		private long failedCount;

		// 服务器上保存的文件路径
		private String serverFileName;

		private String sourceFileName;

		public long getSuccessCount() {
			return successCount;
		}

		public void setSuccessCount(long successCount) {
			this.successCount = successCount;
		}

		public long getFailedCount() {
			return failedCount;
		}

		public void setFailedCount(long failedCount) {
			this.failedCount = failedCount;
		}

		public String getServerFileName() {
			return serverFileName;
		}

		public void setServerFileName(String serverFileName) {
			this.serverFileName = serverFileName;
		}

		public String getSourceFileName() {
			return sourceFileName;
		}

		public void setSourceFileName(String sourceFileName) {
			this.sourceFileName = sourceFileName;
		}
		
		

}
