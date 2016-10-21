package com.yh.apoplexy.admin.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yh.apoplexy.admin.base.service.intf.AdminDoctorService;
import com.yh.apoplexy.admin.base.service.result.ImportDoctorResult;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dto.admin.base.AdminImportDoctorListDto;
import com.yh.apoplexy.assist.dto.admin.common.AdminDoctorInfoDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.ImportConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;

@Service("adminDoctorService")
@ServiceTrace
public class AdminDoctorServicelmpl implements AdminDoctorService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(AdminHospitalServiceImpl.class);

    @Autowired
    private CommonDao commonDao;

    @Override
    public List<AdminDoctorInfoDto> selectListByPage(DoctorInfoDmo doctorInfoDmo,
                                                     Page page) {

        return commonDao.selectListByPage("AdminDoctorMapper.doctorCount",
                "AdminDoctorMapper.doctorList", doctorInfoDmo, page);
    }

    @Override
    public List<DoctorInfoDmo> selectList(DoctorInfoDmo con) {
        return commonDao.selectList(con);
    }

    @Override
    public DoctorInfoDmo selectDoctorDetails(DoctorInfoDmo doctorInfoDmo) {

        return (DoctorInfoDmo) commonDao.selectOne(doctorInfoDmo);
    }

    @Override
    public Result addDoctorDetails(DoctorInfoDmo doctorInfoDmo) {
        int i = commonDao.insert(doctorInfoDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    @Transactional
    public Result updateDoctorDetails(DoctorInfoDmo doctorInfoDmo) {

        Result result = new Result();

        DoctorInfoDmo doctorInfoCon = new DoctorInfoDmo();

        doctorInfoCon.setId(doctorInfoDmo.getId());

        DoctorInfoDmo existDoctorInfo = (DoctorInfoDmo)commonDao.selectOne(doctorInfoCon);

        if (null == existDoctorInfo){

            result.fail("UDD-0001","");
            return result;
        }

        int i = commonDao.update(doctorInfoDmo);
        result = SqlAssertUtils.updateAssert(i);

        if (!result.isSuccess()){

            throw new AppException(result);
        }

        DoctorMemberDmo con = new DoctorMemberDmo();

        con.setPhone(existDoctorInfo.getPhone());

        DoctorMemberDmo existDoctorMember = (DoctorMemberDmo)commonDao.selectOne(con);

        if (null != existDoctorMember){

            existDoctorMember.setAvatar(doctorInfoDmo.getAvatar());
            existDoctorMember.setHospital(doctorInfoDmo.getHospital());
            existDoctorMember.setLastUpdateTime(DateUtil.getDate());
            existDoctorMember.setPhone(doctorInfoDmo.getPhone());
            existDoctorMember.setDepartment(doctorInfoDmo.getDepartment());
            existDoctorMember.setDoctorName(doctorInfoDmo.getDoctorName());
            existDoctorMember.setEffectiveTime(doctorInfoDmo.getEffectiveTime());
            existDoctorMember.setEmail(doctorInfoDmo.getEmail());
            existDoctorMember.setJob(doctorInfoDmo.getJob());
            existDoctorMember.setSex(doctorInfoDmo.getSex());
            existDoctorMember.setTitle(doctorInfoDmo.getTitle());

            int j = commonDao.update(existDoctorMember);

            result = SqlAssertUtils.updateAssert(j);

            if (!result.isSuccess()){

                throw new AppException(result);
            }

        }

        return result;

    }

    @Override
    public Result deleteDoctorDetails(DoctorInfoDmo doctorInfoDmo) {
        doctorInfoDmo.setStatus(Constants.DoctorStatus.DELETE);
        int i = commonDao.update(doctorInfoDmo);
        return SqlAssertUtils.updateAssert(i);
    }

    @Override
    public ImportDoctorResult parseImportExcel(
            List<LinkedHashMap<String, Object>> excelData) {

        // 限制导入的excel数据list长度小于1000
        if (excelData.size() >= 10000) {
            throw new AppException("the import count > 10000");
        }

        // 已导入的医院名称
        Map<String, Long> allTransOrderMap = new HashMap<String, Long>();

        ImportDoctorResult result = new ImportDoctorResult();

        Result resul = new Result();

        // 统计excel文件中成功总记录数、失败总记录数
        long successCount = 0L;
        long failedCount = 0L;

        List<DoctorInfoDmo> doctorList = new ArrayList<DoctorInfoDmo>();
        DoctorInfoDmo doctorInfoDmo = null;

        try {
            for (LinkedHashMap<String, Object> data : excelData) {

                doctorInfoDmo = new DoctorInfoDmo();

                String doctorName = getDataString(data,
                        ImportConstants.Doctor.DOCTOR_NAME);
                String sex = getDataString(data,
                        ImportConstants.Doctor.DOCTOR_SEX);

                String doctorHospital = getDataString(data,
                        ImportConstants.Doctor.DOCTOR_HOSPITAL);
                String department = getDataString(data,
                        ImportConstants.Doctor.DOCTOR_DEPARTMENT);
                String title = getDataString(data,
                        ImportConstants.Doctor.DOCTOR_TITLE);
                String job = getDataString(data,
                        ImportConstants.Doctor.DOCTOR_JOB);
                String effectiveTime = getDataString(data,
                        ImportConstants.Doctor.DOCTOR_EFFECTIVETIME);
                String phone = getDataString(data,
                        ImportConstants.Doctor.DOCTOR_PHONE);

                if (checkHospitalParam(doctorName, sex, doctorHospital,
                        department, title, job, effectiveTime, phone)) {
                    successCount++;
                    doctorInfoDmo.setDoctorName(doctorName);
                    doctorInfoDmo.setHospital(doctorHospital);
                    doctorInfoDmo.setDepartment(department);
                    doctorInfoDmo.setTitle(title);
                    if (sex.equals("男")) {
                        doctorInfoDmo.setSex(Constants.Sex.MAN);
                    }
                    if (sex.equals("女")) {
                        doctorInfoDmo.setSex(Constants.Sex.WOMAN);
                    }
                    doctorInfoDmo.setJob(job);
                    doctorInfoDmo.setPhone(phone);
                    doctorInfoDmo.setEffectiveTime(DateUtil.parseDate(
                            effectiveTime, DateUtil.yyyy_MM_dd));
                    doctorInfoDmo.setCreateTime(DateUtil.getDate());
                    doctorInfoDmo.setLastUpdateTime(DateUtil.getDate());
                    doctorInfoDmo.setStatus(Constants.DoctorStatus.NORMAL);
                    doctorList.add(doctorInfoDmo);
                } else {
                    failedCount++;
                    continue;
                }

            }
        } catch (Exception e) {
            LOGGER.error("exist error row data", e);
            throw new AppException("parse excel doctor data exception");
        }

        if (CollectionUtils.isEmpty(doctorList)) {
            result.setSuccessCount(successCount);
            result.setFailedCount(failedCount);
            return result;
        }

        AdminImportDoctorListDto adminImportDoctorListDto = new AdminImportDoctorListDto();


        adminImportDoctorListDto.setDoctorList(doctorList);

        // 插入医院列表
        int i = commonDao.batchInsert("AdminDoctorMapper.addDoctorInsert",
                adminImportDoctorListDto);

        if (i == successCount) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);

        }

        result.setSuccessCount(successCount);
        result.setFailedCount(failedCount);

        return result;
    }

    private String getDataString(LinkedHashMap<String, Object> data, String key) {

        Object obj = data.get(key);

        if (obj == null) {
            return null;
        } else {
            return obj.toString();
        }
    }

    private boolean checkHospitalParam(String doctorName, String sex,
                                       String doctorHospital, String department, String title, String job,
                                       String effectiveTime, String phone) {
        if (StringUtils.isBlank(doctorName)
                || StringUtils.isBlank(doctorHospital)
                || StringUtils.isBlank(department) || StringUtils.isBlank(sex)
                || StringUtils.isBlank(title) || StringUtils.isBlank(job)
                || StringUtils.isBlank(effectiveTime)
                || StringUtils.isBlank(phone)) {

            return false;
        }

        if (!sex.equals("男") && !sex.equals("女")) {
            return false;
        }

        HospitalInfoDmo hospitalInfoDmo = new HospitalInfoDmo();
        hospitalInfoDmo.setHospitalName(doctorHospital);
        Long i = commonDao.selectCount(
                "AdminHospitalMapper.selectInferiorHospitalName",
                hospitalInfoDmo);
        if (i == 0) {
            return false;
        }

        return true;
    }

}
