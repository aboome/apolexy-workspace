package com.yh.apoplexy.admin.base.service.intf;

import com.yh.apoplexy.assist.dmo.common.AreaCodeDmo;

import java.util.List;

/**
 * 地区编码服务接口类
 * Created by wunder on 16/10/8 18:32.
 */
public interface AdminAreaCodeService {

    /**
     * 查询地区编码信息列表
     * @param con
     * @return
     */
    public List<AreaCodeDmo> selectList(AreaCodeDmo con);
}
