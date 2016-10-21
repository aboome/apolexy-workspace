package com.yh.apoplexy.patient.knowledge.service.intf;

import com.yh.apoplexy.assist.dmo.patient.knowledge.PatientNewsDmo;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * 患者宣教内容服务接口类
 * Created by wunder on 16/9/5 19:56.
 */
public interface PatientKnowledgeService {

    /**
     * 查询患者宣教内容
     * @param con
     * @return
     */
    public PatientNewsDmo find(PatientNewsDmo con);

    /**
     * 分页查询患者宣教内容
     * @param con
     * @param page
     * @return
     */
    public List<PatientNewsDmo> selectListByPage(PatientNewsDmo con, Page page);

    /**
     * 查询最新患者宣教内容
     * @param con
     * @return
     */
    public PatientNewsDmo findLatestKnowledge(PatientNewsDmo con);

}
