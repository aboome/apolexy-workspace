package com.yh.apoplexy.doctor.member.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMessageDmo;
import com.yh.apoplexy.assist.dto.doctor.member.MultiDoctorMessageDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * 消息中心服务接口类
 * Created by wunder on 16/9/10 18:44.
 */
public interface DoctorMessageService {

    /**
     * 新增消息
     * @param doctorMessageDmo
     * @return
     */
    public Result insert(DoctorMessageDmo doctorMessageDmo);

    /**
     * 批量新增消息
     * @param multiDoctorMessageDto
     * @return
     */
    public Result batchInsert(MultiDoctorMessageDto multiDoctorMessageDto);

    /**
     * 分页查询消息
     * @param con
     * @param page
     * @return
     */
    public List<DoctorMessageDmo> selectListByPage(DoctorMessageDmo con, Page page);

    /**
     * 新增推送消息
     * @param doctorMessageDmo
     * @return
     */
    public Result addMessage(DoctorMessageDmo doctorMessageDmo);

    /**
     * 批量新增推送消息
     * @param multiDoctorMessageDto
     * @return
     */
    public Result bathAddMessage(MultiDoctorMessageDto multiDoctorMessageDto);
}
