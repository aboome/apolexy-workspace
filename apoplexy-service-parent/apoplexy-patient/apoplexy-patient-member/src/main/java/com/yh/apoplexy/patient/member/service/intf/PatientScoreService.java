package com.yh.apoplexy.patient.member.service.intf;

import com.yh.apoplexy.assist.dmo.patient.member.PatientScoreDetailDmo;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * 患者积分服务接口类
 * Created by wunder on 16/9/5 23:13.
 */
public interface PatientScoreService {

    /**
     * 新增患者用户积分信息
     * @param patientScoreDetailDmo
     * @return
     */
    public Result insert(PatientScoreDetailDmo patientScoreDetailDmo);

    /**
     * 更新患者用户积分信息
     * @param patientScoreDetailDmo
     * @return
     */
    public Result update(PatientScoreDetailDmo patientScoreDetailDmo);

    /**
     * 删除患者用户积分信息
     * @param patientScoreDetailDmo
     * @return
     */
    public Result delete(PatientScoreDetailDmo patientScoreDetailDmo);

    /**
     * 查询患者用户积分信息
     * @param patientScoreDetailDmo
     * @return
     */
    public PatientScoreDetailDmo find(PatientScoreDetailDmo patientScoreDetailDmo);

    /**
     * 分页查询患者用户积分信息列表
     * @param con
     * @param page
     * @return
     */
    public List<PatientScoreDetailDmo> selectListByPage(PatientScoreDetailDmo con, Page page);

    /**
     * 新增积分事件
     * @param patientScoreDetailDmo
     * @return
     */
    public Result addScoreEvent(PatientScoreDetailDmo patientScoreDetailDmo);

}
