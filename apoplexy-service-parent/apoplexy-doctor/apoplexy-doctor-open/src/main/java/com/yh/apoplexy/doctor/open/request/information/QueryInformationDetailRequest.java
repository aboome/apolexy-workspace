package com.yh.apoplexy.doctor.open.request.information;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 查询最新资讯详情(doc-0052)请求
 * Created by wunder on 16/9/2 15:16.
 */
public class QueryInformationDetailRequest implements Serializable {

    private static final long serialVersionUID = 6413886136584065661L;

    /**
     * 健康宣教ID
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String newId;

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }
}
