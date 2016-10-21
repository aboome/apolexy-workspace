package com.yh.apoplexy.doctor.open.dto.cases.ast;

import com.yh.apoplexy.common.annotations.validator.StringValidator;
import com.yh.apoplexy.common.constants.RegularConstants;

import java.io.Serializable;

/**
 * 既往用药实体项
 * Created by wunder on 16/9/1 20:55.
 */
public class HisMedicineItem implements Serializable {

    private static final long serialVersionUID = -3416089980593993375L;

    /**
     * 既往用药史序号
     */
    @StringValidator(nullable = false, pattern = RegularConstants.ID_REGULAR)
    private String index;

    /**
     * 是否使用(0：否;1：是)
     */
    @StringValidator(nullable = true, pattern = RegularConstants.TYPE_REGULAR)
    private String used;

    /**
     * 药物名称
     */
    @StringValidator(nullable = true, pattern = RegularConstants.SMALL_TEXT_REGULAR)
    private String medicaitionName;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getMedicaitionName() {
        return medicaitionName;
    }

    public void setMedicaitionName(String medicaitionName) {
        this.medicaitionName = medicaitionName;
    }
}
