package com.yh.apoplexy.patient.knowledge.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.patient.knowledge.PatientNewsDmo;
import com.yh.apoplexy.common.constants.PatientConstants;
import com.yh.apoplexy.patient.knowledge.service.intf.PatientKnowledgeService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 患者宣教内容服务实现类
 * Created by wunder on 16/9/5 20:00.
 */
@Service("patientKnowledgeService")
@ServiceTrace
public class PatientKnowledgeServiceImpl implements PatientKnowledgeService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public PatientNewsDmo find(PatientNewsDmo con) {
        return (PatientNewsDmo)commonDao.selectOne(con);
    }

    @Override
    public List<PatientNewsDmo> selectListByPage(PatientNewsDmo con, Page page) {
        return commonDao.selectListByPage("PatientNewsMapper.countNews","PatientNewsMapper.queryNews",con,page);
    }

    @Override
    public PatientNewsDmo findLatestKnowledge(PatientNewsDmo con) {

        con.setStatus(PatientConstants.NewsStatus.NORMAL);

        return (PatientNewsDmo) commonDao.selectOne("PatientNewsMapper.findLatestNews",con);
    }
}
