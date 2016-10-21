package com.yh.apoplexy.assist.dto.admin.doctor.cases.referral;

import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.cases.referral.ReferralIntentDoctorDto;
import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * admin端转诊病例详情实体类
 * Created by wunder on 16/9/20 23:19.
 */
public class AdminReferralCaseDetailDto extends Entity {

    private static final long serialVersionUID = 2310444541620114002L;

    /**
     * 医生信息
     */
    private DoctorMemberDmo doctorMemberDmo;

    /**
     * 病例详情
     */
    private ReferralCaseDmo referralCaseDmo;
    /**
     * 视频
     */
    private ReferralResourceDmo video;

    /**
     * 图片列表
     */
    private List<ReferralResourceDmo> imageList;

    /**
     * NIHSS列表
     */
    private List<NihssDetailDmo> nihssList;

    public DoctorMemberDmo getDoctorMemberDmo() {
        return doctorMemberDmo;
    }

    public void setDoctorMemberDmo(DoctorMemberDmo doctorMemberDmo) {
        this.doctorMemberDmo = doctorMemberDmo;
    }

    public ReferralCaseDmo getReferralCaseDmo() {
        return referralCaseDmo;
    }

    public void setReferralCaseDmo(ReferralCaseDmo referralCaseDmo) {
        this.referralCaseDmo = referralCaseDmo;
    }

    public ReferralResourceDmo getVideo() {
        return video;
    }

    public void setVideo(ReferralResourceDmo video) {
        this.video = video;
    }

    public List<ReferralResourceDmo> getImageList() {
        return imageList;
    }

    public void setImageList(List<ReferralResourceDmo> imageList) {
        this.imageList = imageList;
    }

    public List<NihssDetailDmo> getNihssList() {
        return nihssList;
    }

    public void setNihssList(List<NihssDetailDmo> nihssList) {
        this.nihssList = nihssList;
    }
}
