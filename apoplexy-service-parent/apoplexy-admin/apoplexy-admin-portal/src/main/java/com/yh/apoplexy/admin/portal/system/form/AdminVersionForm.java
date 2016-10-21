package com.yh.apoplexy.admin.portal.system.form;
/**
 * 系统版本管理
 * @author zhangbiao
 *
 */
public class AdminVersionForm {
    private String id;
    //版本号
    private String version;

    private String upgradeUrl;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the upgradeUrl
	 */
	public String getUpgradeUrl() {
		return upgradeUrl;
	}

	/**
	 * @param upgradeUrl the upgradeUrl to set
	 */
	public void setUpgradeUrl(String upgradeUrl) {
		this.upgradeUrl = upgradeUrl;
	}
    
    
}
