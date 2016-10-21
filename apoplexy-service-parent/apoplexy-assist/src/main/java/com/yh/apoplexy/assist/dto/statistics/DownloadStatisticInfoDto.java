package com.yh.apoplexy.assist.dto.statistics;

import com.yjh.framework.core.entity.Entity;

import java.util.Date;

/**
 * Created by wunder on 16/9/27 19:04.
 */
public class DownloadStatisticInfoDto extends Entity {

    private static final long serialVersionUID = 1113921452491015657L;

    private Long countNum;

    private Date countDate;

    public Long getCountNum() {
        return countNum;
    }

    public void setCountNum(Long countNum) {
        this.countNum = countNum;
    }

    public Date getCountDate() {
        return countDate;
    }

    public void setCountDate(Date countDate) {
        this.countDate = countDate;
    }
}
