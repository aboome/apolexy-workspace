package com.yh.apoplexy.admin.base.service.impl;

import com.yh.apoplexy.admin.base.service.intf.AdminAreaCodeService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.AreaCodeDmo;
import com.yjh.framework.core.trace.ServiceTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区编码服务实现类
 * Created by wunder on 16/10/8 18:33.
 */
@Service("adminAreaCodeService")
@ServiceTrace
public class AdminAreaCodeServiceImpl implements AdminAreaCodeService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public List<AreaCodeDmo> selectList(AreaCodeDmo con) {

        return commonDao.selectList(con);
    }
}
