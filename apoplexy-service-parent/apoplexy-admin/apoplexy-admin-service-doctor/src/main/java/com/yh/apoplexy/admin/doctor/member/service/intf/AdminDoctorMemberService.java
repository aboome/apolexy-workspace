package com.yh.apoplexy.admin.doctor.member.service.intf;

import com.yh.apoplexy.assist.dmo.doctor.member.DoctorLoginDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.member.DoctorMemberDto;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

import java.util.List;

/**
 * 医生会员信息服务接口类
 * Created by wunder on 16/9/4 13:10.
 */
public interface AdminDoctorMemberService {

    /**
     * 新增医生会员信息
     * @param doctorMemberDmo
     * @return
     */
    public Result insert(DoctorMemberDmo doctorMemberDmo);

    /**
     * 更新医生会员信息
     * @param doctorMemberDmo
     * @return
     */
    public Result update(DoctorMemberDmo doctorMemberDmo);

    /**
     * 删除医生会员信息
     * @param doctorMemberDmo
     * @return
     */
    public Result delete(DoctorMemberDmo doctorMemberDmo);

    /**
     * 查询医生会员信息
     * @param doctorMemberDmo
     * @return
     */
    public DoctorMemberDmo selectOne(DoctorMemberDmo doctorMemberDmo);

    /**
     * 分页查询医生会员
     * @param con
     * @param page
     * @return
     */
    public List<DoctorMemberDmo> selectListByPage(DoctorMemberDmo con, Page page);

}
