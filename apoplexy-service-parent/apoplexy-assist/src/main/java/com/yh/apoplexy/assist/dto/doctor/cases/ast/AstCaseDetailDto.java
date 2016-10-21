package com.yh.apoplexy.assist.dto.doctor.cases.ast;

import com.yh.apoplexy.assist.dmo.doctor.cases.NihssDetailDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstCasesDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstHistoryDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstMedicationDmo;
import com.yh.apoplexy.assist.dmo.doctor.cases.ast.AstResourcesDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yjh.framework.core.entity.Entity;

import java.util.List;

/**
 * AST病例详情实体类
 * Created by wunder on 16/9/10 15:25.
 */
public class AstCaseDetailDto extends Entity {

    private static final long serialVersionUID = 7965725072452741146L;

    /**
     * 医生信息
     */
    private DoctorMemberDmo doctorMemberDmo;

    /**
     * 病例详情
     */
    private AstCasesDmo astCasesDmo;

    /**
     * NIHSS列表
     */
    private List<NihssDetailDmo> nihssList;

    /**
     * 既往史列表
     */
    private List<AstHistoryDmo> hisIllness;

    /**
     * 既往用药列表
     */
    private List<AstMedicationDmo> hisMedicaitionList;

    /**
     * CT平扫图片
     */
    private List<AstResourcesDmo> ctList;

    /**
     * CTA影像列表
     */
    private List<AstResourcesDmo> ctaList;

    /**
     * CTP影像列表
     */
    private List<AstResourcesDmo> ctpList;

    /**
     * 图片列表
     */
    private List<AstResourcesDmo> imageList;

    public DoctorMemberDmo getDoctorMemberDmo() {
        return doctorMemberDmo;
    }

    public void setDoctorMemberDmo(DoctorMemberDmo doctorMemberDmo) {
        this.doctorMemberDmo = doctorMemberDmo;
    }

    public AstCasesDmo getAstCasesDmo() {
        return astCasesDmo;
    }

    public void setAstCasesDmo(AstCasesDmo astCasesDmo) {
        this.astCasesDmo = astCasesDmo;
    }

    public List<NihssDetailDmo> getNihssList() {
        return nihssList;
    }

    public void setNihssList(List<NihssDetailDmo> nihssList) {
        this.nihssList = nihssList;
    }

    public List<AstHistoryDmo> getHisIllness() {
        return hisIllness;
    }

    public void setHisIllness(List<AstHistoryDmo> hisIllness) {
        this.hisIllness = hisIllness;
    }

    public List<AstMedicationDmo> getHisMedicaitionList() {
        return hisMedicaitionList;
    }

    public void setHisMedicaitionList(List<AstMedicationDmo> hisMedicaitionList) {
        this.hisMedicaitionList = hisMedicaitionList;
    }

    public List<AstResourcesDmo> getCtList() {
        return ctList;
    }

    public void setCtList(List<AstResourcesDmo> ctList) {
        this.ctList = ctList;
    }

    public List<AstResourcesDmo> getCtaList() {
        return ctaList;
    }

    public void setCtaList(List<AstResourcesDmo> ctaList) {
        this.ctaList = ctaList;
    }

    public List<AstResourcesDmo> getCtpList() {
        return ctpList;
    }

    public void setCtpList(List<AstResourcesDmo> ctpList) {
        this.ctpList = ctpList;
    }

    public List<AstResourcesDmo> getImageList() {
        return imageList;
    }

    public void setImageList(List<AstResourcesDmo> imageList) {
        this.imageList = imageList;
    }
}
