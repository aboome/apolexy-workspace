package com.yh.apoplexy.assist.dto.admin.doctor.cases.discuss;

import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.CaseResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.discuss.DiscussCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * Admin讨论病例详情实体类
 * Created by wunder on 16/9/20 20:00.
 */
public class AdminDiscussCaseDetailDto extends Entity{

    private static final long serialVersionUID = -1597408862501048729L;

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
