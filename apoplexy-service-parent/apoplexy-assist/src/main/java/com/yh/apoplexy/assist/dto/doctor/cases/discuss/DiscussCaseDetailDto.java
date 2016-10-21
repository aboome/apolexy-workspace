package com.yh.apoplexy.assist.dto.doctor.cases.discuss;

import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yjh.framework.core.entity.Entity;

import java.util.Date;
import java.util.List;

/**
 * 讨论病例详情实体类
 * Created by wunder on 16/9/6 16:59.
 */
public class DiscussCaseDetailDto extends Entity {

    private static final long serialVersionUID = -5999373952358374134L;

    /**
     * 医生信息
     */
    private DoctorMemberDmo doctorMemberDmo;

    /**
     * 病例详情
     */
    private DiscussCaseDmo discussCaseDmo;
    /**
     * 视频
     */
    private CaseResourceDmo video;

    /**
     * 图片列表
     */
    private List<CaseResourceDmo> imageList;

    public DoctorMemberDmo getDoctorMemberDmo() {
        return doctorMemberDmo;
    }

    public void setDoctorMemberDmo(DoctorMemberDmo doctorMemberDmo) {
        this.doctorMemberDmo = doctorMemberDmo;
    }

    public DiscussCaseDmo getDiscussCaseDmo() {
        return discussCaseDmo;
    }

    public void setDiscussCaseDmo(DiscussCaseDmo discussCaseDmo) {
        this.discussCaseDmo = discussCaseDmo;
    }

    public CaseResourceDmo getVideo() {
        return video;
    }

    public void setVideo(CaseResourceDmo video) {
        this.video = video;
    }

    public List<CaseResourceDmo> getImageList() {
        return imageList;
    }

    public void setImageList(List<CaseResourceDmo> imageList) {
        this.imageList = imageList;
    }

}
