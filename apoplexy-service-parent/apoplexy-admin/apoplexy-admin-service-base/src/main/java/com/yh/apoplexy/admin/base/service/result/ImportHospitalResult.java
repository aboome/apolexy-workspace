/**
 * 
 */
package com.yh.apoplexy.admin.base.service.result;

import com.yjh.framework.lang.Result;

/**
 * 导入医院结果
 * 
 * @author CC
 * 
 */
public class ImportHospitalResult extends Result {

	private static final long serialVersionUID = -8840606856720536754L;

	// 导入成功总笔数
	private long successCount;

	// 导入失败总笔数
	private long failedCount;

	// 服务器上保存的文件路径
	private String serverFileName;

	private String sourceFileName;

	/**
	 * @return the successCount
	 */
	public long getSuccessCount() {
		return successCount;
	}

	/**
	 * @param successCount the successCount to set
	 */
	public void setSuccessCount(long successCount) {
		this.successCount = successCount;
	}

	/**
	 * @return the failedCount
	 */
	public long getFailedCount() {
		return failedCount;
	}

	/**
	 * @param failedCount the failedCount to set
	 */
	public void setFailedCount(long failedCount) {
		this.failedCount = failedCount;
	}

	/**
	 * @return the serverFileName
	 */
	public String getServerFileName() {
		return serverFileName;
	}

	/**
	 * @param serverFileName the serverFileName to set
	 */
	public void setServerFileName(String serverFileName) {
		this.serverFileName = serverFileName;
	}

	/**
	 * @return the sourceFileName
	 */
	public String getSourceFileName() {
		return sourceFileName;
	}

	/**
	 * @param sourceFileName the sourceFileName to set
	 */
	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	
	
}
