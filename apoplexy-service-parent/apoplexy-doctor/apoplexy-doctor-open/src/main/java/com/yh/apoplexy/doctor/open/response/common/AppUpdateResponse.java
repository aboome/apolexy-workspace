package com.yh.apoplexy.doctor.open.response.common;

import java.io.Serializable;

/**
 * APP版本升级接口 (common-0010)响应
 * Created by wunder on 16/9/3 13:17.
 */
public class AppUpdateResponse implements Serializable {

    private static final long serialVersionUID = 6970099876604088813L;

    /**
     * (是否需要升级:0：不需要;1：需要)
     */
    private String isNeedUpgrade;

    /**
     * 最新版本号
     */
    private String upgradeVersion;

    /**
     * 版本升级地址
     */
    private String upgradeUrl;

    public String getIsNeedUpgrade() {
        return isNeedUpgrade;
    }

    public void setIsNeedUpgrade(String isNeedUpgrade) {
        this.isNeedUpgrade = isNeedUpgrade;
    }

    public String getUpgradeVersion() {
        return upgradeVersion;
    }

    public void setUpgradeVersion(String upgradeVersion) {
        this.upgradeVersion = upgradeVersion;
    }

    public String getUpgradeUrl() {
        return upgradeUrl;
    }

    public void setUpgradeUrl(String upgradeUrl) {
        this.upgradeUrl = upgradeUrl;
    }

}
