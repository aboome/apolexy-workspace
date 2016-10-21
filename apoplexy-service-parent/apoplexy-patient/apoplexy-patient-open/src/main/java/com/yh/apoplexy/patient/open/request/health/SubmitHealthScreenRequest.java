package com.yh.apoplexy.patient.open.request.health;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;
import com.yh.apoplexy.patient.open.dto.health.OptionItem;

import java.io.Serializable;
import java.util.List;

/**
 * 提交健康筛查信息 (pat-0005)请求
 * Created by wunder on 16/9/1 09:54.
 */
public class SubmitHealthScreenRequest implements Serializable {

    private static final long serialVersionUID = 5655944186819265235L;

    /**
     * 用户id
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String userId;

    /**
     * 年龄
     */
    @StringValidator(nullable = true, pattern = RegularConstants.ID_REGULAR)
    private String age;

    /**
     * 性别：0：男   1：女
     */
    @StringValidator(nullable = false, pattern = RegularConstants.TYPE_REGULAR)
    private String sex;

    /**
     * 既往史列表
     */
    private List<OptionItem> history;

    /**
     * 筛查题目列表
     */
    private List<OptionItem> list;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<OptionItem> getHistory() {
        return history;
    }

    public void setHistory(List<OptionItem> history) {
        this.history = history;
    }

    public List<OptionItem> getList() {
        return list;
    }

    public void setList(List<OptionItem> list) {
        this.list = list;
    }
}
