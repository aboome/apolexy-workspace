package com.yh.apoplexy.assist.dto.doctor.cases.referral;

import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralCaseDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralReceiveDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralResourceDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.referral.ReferralScreenDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * 转诊病例详情实体类
 * Created by wunder on 16/9/8 10:26.
 */
public class ReferralCaseDetailDto extends Entity {

    private static final long serialVersionUID = 1755648435603775496L;

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

    /**
     * 转诊筛查列表
     */
    private List<ReferralScreenDetailDmo> screenList;

    /**
     * 意向接诊医生列表
     */
    private List<ReferralIntentDoctorDto> receiveList;

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

    public List<ReferralIntentDoctorDto> getReceiveList() {
        return receiveList;
    }

    public void setReceiveList(List<ReferralIntentDoctorDto> receiveList) {
        this.receiveList = receiveList;
    }

    public List<ReferralScreenDetailDmo> getScreenList() {
        return screenList;
    }

    public void setScreenList(List<ReferralScreenDetailDmo> screenList) {
        this.screenList = screenList;
    }
}
