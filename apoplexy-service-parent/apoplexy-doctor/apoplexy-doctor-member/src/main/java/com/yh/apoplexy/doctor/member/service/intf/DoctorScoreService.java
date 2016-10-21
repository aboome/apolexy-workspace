package com.yh.apoplexy.doctor.member.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.member.DoctorScoreDetailDmo;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * 医生积分服务接口类
 * Created by wunder on 16/9/6 20:51.
 */
public interface DoctorScoreService {

    /**
     * 新增医生用户积分信息
     * @param doctorScoreDetailDmo
     * @return
     */
    public Result insert(DoctorScoreDetailDmo doctorScoreDetailDmo);

    /**
     * 更新医生用户积分信息
     * @param doctorScoreDetailDmo
     * @return
     */
    public Result update(DoctorScoreDetailDmo doctorScoreDetailDmo);

    /**
     * 删除医生用户积分信息
     * @param doctorScoreDetailDmo
     * @return
     */
    public Result delete(DoctorScoreDetailDmo doctorScoreDetailDmo);

    /**
     * 查询医生用户积分信息
     * @param doctorScoreDetailDmo
     * @return
     */
    public DoctorScoreDetailDmo find(DoctorScoreDetailDmo doctorScoreDetailDmo);

    /**
     * 分页查询医生用户积分信息列表
     * @param con
     * @param page
     * @return
     */
    public List<DoctorScoreDetailDmo> selectListByPage(DoctorScoreDetailDmo con, Page page);

    /**
     * 新增积分事件
     * @param doctorScoreDetailDmo
     * @return
     */
    public Result addScoreEvent(DoctorScoreDetailDmo doctorScoreDetailDmo);
}
