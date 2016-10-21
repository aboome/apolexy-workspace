package com.yh.apoplexy.admin.statis.result;

import com.yjh.framework.lang.Result;

import java.util.List;

/**
 * @author eyelake
 *
 */
public class DownloadStatisticInfoResult extends Result{

	private static final long serialVersionUID = 1571225473219940466L;

    private List<Long> countNum;

    private List<String> countDate;

    public List<String> getCountDate() {
        return countDate;
    }

    public void setCountDate(List<String> countDate) {
        this.countDate = countDate;
    }

    public List<Long> getCountNum() {
        return countNum;
    }

    public void setCountNum(List<Long> countNum) {
        this.countNum = countNum;
    }
}
