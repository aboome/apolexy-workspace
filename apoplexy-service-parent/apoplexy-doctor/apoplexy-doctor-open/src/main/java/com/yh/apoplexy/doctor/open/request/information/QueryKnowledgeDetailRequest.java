package com.yh.apoplexy.doctor.open.request.information;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 查询学习更新详情(doc-0050)请求
 * Created by wunder on 16/9/2 15:26.
 */
public class QueryKnowledgeDetailRequest implements Serializable {

    private static final long serialVersionUID = 3928558377364763761L;

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
