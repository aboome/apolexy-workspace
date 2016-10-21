package com.yh.apoplexy.admin.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.yh.apoplexy.assist.dmo.common.DoctorInfoDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.admin.base.UpdateDoctorInfoDto;
import com.yh.apoplexy.assist.dto.admin.base.UpdateDoctorMemberDto;
import com.yh.apoplexy.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yh.apoplexy.admin.base.service.intf.AdminHospitalService;
import com.yh.apoplexy.admin.base.service.result.ImportHospitalResult;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.common.HospitalInfoDmo;
import com.yh.apoplexy.assist.dto.admin.base.AdminHospitalDto;
import com.yh.apoplexy.assist.dto.admin.base.AdminImportHospitalListDto;
import com.yh.apoplexy.common.constants.ImportConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;

@Service("adminHospitalService")
@ServiceTrace
public class AdminHospitalServiceImpl implements AdminHospitalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminHospitalServiceImpl.class);

    @Autowired
    private CommonDao commonDao;

    @Override
    public List<AdminHospitalDto> selectListByPage(HospitalInfoDmo hospitalInfoDmo, Page page) {

        return commonDao.selectListByPage("AdminHospitalMapper.hospitalCount", "AdminHospitalMapper.hospitalList", hospitalInfoDmo, page);
    }

    @Override
    public List<HospitalInfoDmo> selectList(HospitalInfoDmo con) {
        return commonDao.selectList(con);
    }

    @Override
    public HospitalInfoDmo selectHospitalDetails(HospitalInfoDmo hospitalInfoDmo) {

        return (HospitalInfoDmo) commonDao.selectOne(hospitalInfoDmo);
    }

    @Override
    @Transactional
    public Result addHospitalDetails(AdminHospitalDto adminHospitalDto) {

        String parentHospitalId = adminHospitalDto.getParentHospitalId();

        HospitalInfoDmo hospitalInfoDmo = new HospitalInfoDmo();

        HospitalInfoDmo infoDmo = new HospitalInfoDmo();

        Result result = new Result();

        int i;

        HospitalInfoDmo hospitalInfo = new HospitalInfoDmo();

        hospitalInfo.setHospitalName(adminHospitalDto.getHospitalName());

        Long l = commonDao.selectCount("AdminHospitalMapper.selectInferiorHospitalName", hospitalInfo);

        if (l > 0) {
            result.fail("新增医院名称已存在请重新输入");
            throw new AppException(result);
        }

        if (StringUtils.isBlank(parentHospitalId)) {

            infoDmo.setParentHospitalId(0L);
            infoDmo.setRootHospitalId(0L);

        } else {

            hospitalInfoDmo.setId(Long.parseLong(parentHospitalId));

            HospitalInfoDmo dmo = (HospitalInfoDmo) commonDao.selectOne(hospitalInfoDmo);

            if (dmo == null) {
                result.fail("未查找到上级医院,新增医院失败");
                throw new AppException(result);
            }

            infoDmo.setParentHospitalId(dmo.getId());

            Long rootHospitalId = dmo.getRootHospitalId();

            if (null == rootHospitalId || rootHospitalId.equals(0L)) {
                infoDmo.setRootHospitalId(dmo.getId());
            } else {
                infoDmo.setRootHospitalId(rootHospitalId);
            }

        }

        infoDmo.setHospitalName(adminHospitalDto.getHospitalName());
        infoDmo.setHospitalAddr(adminHospitalDto.getHospitalAddr());
        infoDmo.setHospitalDesc(adminHospitalDto.getHospitalDesc());
        infoDmo.setImageId(adminHospitalDto.getImageId());
        infoDmo.setLevel(adminHospitalDto.getLevel());
        infoDmo.setCreateTime(DateUtil.getDate());
        infoDmo.setLastUpdateTime(DateUtil.getDate());
        if (StringUtils.isNotBlank(adminHospitalDto.getLon())) {
            infoDmo.setLon(Double.valueOf(adminHospitalDto.getLon()));
        }
        if (StringUtils.isNotBlank(adminHospitalDto.getLat())) {
            infoDmo.setLat(Double.valueOf(adminHospitalDto.getLat()));
        }
        infoDmo.setAreaCode(adminHospitalDto.getAreaCode());
        infoDmo.setStatus(Constants.HospitalStatus.NORMAL);
        infoDmo.setHospitalUnion(adminHospitalDto.getHospitalUnion());

        i = commonDao.insert(infoDmo);

        if (i < 0) {
            result.fail("新增医院失败");
            throw new AppException(result);
        }

        return result;
    }

    @Override
    @Transactional
    public Result updateHospitalDetails(AdminHospitalDto adminHospitalDto) {

        Result result = new Result();

        HospitalInfoDmo hospitalInfoCon = new HospitalInfoDmo();

        hospitalInfoCon.setId(Long.valueOf(adminHospitalDto.getId()));

        HospitalInfoDmo existHospital = selectHospitalDetails(hospitalInfoCon);

        if (null == existHospital){
            result.fail("UHD-0002","查询医院信息失败");
            return result;
        }

        String parentHospitalId = adminHospitalDto.getParentHospitalId();

        HospitalInfoDmo hospitalInfoDmo = new HospitalInfoDmo();

        int i;

        if (StringUtils.isBlank(parentHospitalId)) {
            hospitalInfoDmo.setParentHospitalId(0L);
            hospitalInfoDmo.setRootHospitalId(0L);
        } else {

            HospitalInfoDmo con = new HospitalInfoDmo();

            con.setId(Long.parseLong(parentHospitalId));

            HospitalInfoDmo parentHospital = (HospitalInfoDmo) commonDao.selectOne(con);

            if (parentHospital == null) {
                result.fail("未查找到上级医院,修改医院失败");
                throw new AppException(result);
            }

            hospitalInfoDmo.setParentHospitalId(parentHospital.getId());

            Long rootHospitalId = parentHospital.getRootHospitalId();

            if (null == rootHospitalId || rootHospitalId.equals(0L)) {
                hospitalInfoDmo.setRootHospitalId(parentHospital.getId());
            } else {
                hospitalInfoDmo.setRootHospitalId(rootHospitalId);
            }

        }

        hospitalInfoDmo.setHospitalName(adminHospitalDto.getHospitalName());
        hospitalInfoDmo.setHospitalAddr(adminHospitalDto.getHospitalAddr());
        hospitalInfoDmo.setHospitalDesc(adminHospitalDto.getHospitalDesc());
        if (StringUtils.isNotBlank(adminHospitalDto.getLon())){
            hospitalInfoDmo.setLon(Double.valueOf(adminHospitalDto.getLon()));
        }
        if (StringUtils.isNotBlank(adminHospitalDto.getLat())){
            hospitalInfoDmo.setLat(Double.valueOf(adminHospitalDto.getLat()));
        }
        hospitalInfoDmo.setLastUpdateTime(DateUtil.getDate());
        hospitalInfoDmo.setLevel(adminHospitalDto.getLevel());
        hospitalInfoDmo.setImageId(adminHospitalDto.getImageId());
        hospitalInfoDmo.setAreaCode(adminHospitalDto.getAreaCode());
        hospitalInfoDmo.setId(Long.valueOf(adminHospitalDto.getId()));
        hospitalInfoDmo.setHospitalUnion(adminHospitalDto.getHospitalUnion());

        i = commonDao.update(hospitalInfoDmo);

        if (i < 0) {
            result.fail("UHD-0001","更新医院信息失败");
            throw new AppException(result);
        }

        DoctorInfoDmo doctorInfoCon = new DoctorInfoDmo();

        doctorInfoCon.setHospital(existHospital.getHospitalName());

        //更新医生信息列表
        result = updateDoctorInfoList(doctorInfoCon,adminHospitalDto.getHospitalName());

        if (!result.isSuccess()){
            throw new AppException(result);
        }


        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setHospital(existHospital.getHospitalName());

        //更新医生会员列表
        result = updateDoctorMemberList(doctorMemberCon,adminHospitalDto.getHospitalName());

        if (!result.isSuccess()){
            throw new AppException(result);
        }

        return result;
    }

    @Override
    @Transactional
    public Result deleteHospitalDetails(HospitalInfoDmo hospitalInfoDmo) {

        Result result = new Result();
        HospitalInfoDmo dmo = new HospitalInfoDmo();

        dmo.setParentHospitalId(hospitalInfoDmo.getId());
        Long i = commonDao.selectCount("AdminHospitalMapper.selectInferiorHospital", dmo);
        if (i > 0) {

            result.fail("下级医院未被删除无法直接删除上级医院");

            throw new AppException(result);

        } else {
            int t = commonDao.delete(hospitalInfoDmo);

            if (t < 0) {
                result.fail("删除医院失败");
                throw new AppException(result);
            }
        }

        return result;
    }

    @Override
    public Long selectInferiorHospital(HospitalInfoDmo hospitalInfoDmo) {
        HashMap<String, String> conMap = new HashMap<>();
        conMap.put("parentHospitalId", String.valueOf(hospitalInfoDmo.getParentHospitalId()));
        Long i = commonDao.selectCount("AdminHospitalMapper.selectInferiorHospital", conMap);
        return i;
    }

    /**
     * 导入医院，记录总成功数据，总失败数
     *
     * @param excelData
     * @return
     */
    public ImportHospitalResult parseImportExcel(List<LinkedHashMap<String, Object>> excelData) {

        // 限制导入的excel数据list长度小于1000
        if (excelData.size() >= 10000) {
            throw new AppException("the import count > 10000");
        }

        // 已导入的医院名称
        Map<String, Long> allTransOrderMap = new HashMap<String, Long>();

        ImportHospitalResult result = new ImportHospitalResult();

        Result resul = new Result();

        // 统计excel文件中成功总记录数、失败总记录数
        long successCount = 0L;
        long failedCount = 0L;

        List<HospitalInfoDmo> hospitalList = new ArrayList<HospitalInfoDmo>();
        HospitalInfoDmo hospitalInfoDmo = null;

        try {
            for (LinkedHashMap<String, Object> data : excelData) {

                hospitalInfoDmo = new HospitalInfoDmo();

                String hospitalName = getDataString(data, ImportConstants.Hospital.HOSPITAL_NAME);//data.get(ImportConstants.Hospital.HOSPITAL_NAME).toString();
                String hospitalDesc = getDataString(data, ImportConstants.Hospital.HOSPITAL_DESC);//data.get(ImportConstants.Hospital.HOSPITAL_DESC).toString();
                String hospitalAddr = getDataString(data, ImportConstants.Hospital.HOSPITAL_ADDR);//data.get(ImportConstants.Hospital.HOSPITAL_ADDR).toString();
                String hospitalImage = getDataString(data, ImportConstants.Hospital.HOSPITAL_IMAGE);//data.get(ImportConstants.Hospital.HOSPITAL_IMAGE).toString();
                String hospitalLevel = getDataString(data, ImportConstants.Hospital.HOSPITAL_LEVEL);//data.get(ImportConstants.Hospital.HOSPITAL_LEVEL).toString();
                String parentHospitalName = getDataString(data, ImportConstants.Hospital.PARENT_HOSPITAL_NAME);//data.get(ImportConstants.Hospital.PARENT_HOSPITAL_NAME).toString();
                String hospitalLon = getDataString(data, ImportConstants.Hospital.HOSPITAL_LON);//data.get(ImportConstants.Hospital.HOSPITAL_LON).toString();
                String hospitalLat = getDataString(data, ImportConstants.Hospital.HOSPITAL_LAT);//data.get(ImportConstants.Hospital.HOSPITAL_LAT).toString();
                String hospitalAreacode = getDataString(data, ImportConstants.Hospital.HOSPITAL_AREACODE);//data.get(ImportConstants.Hospital.HOSPITAL_AREACODE).toString();
                String hospitalUnion = getDataString(data, ImportConstants.Hospital.HOSPITAL_UNION);//data.get(ImportConstants.Hospital.HOSPITAL_UNION).toString();

                if (checkHospitalParam(hospitalName, hospitalDesc, hospitalAddr, hospitalImage, hospitalLevel,
                        parentHospitalName, hospitalLon, hospitalLat, hospitalAreacode)) {
                    successCount++;
                    hospitalInfoDmo.setHospitalName(hospitalName);
                    hospitalInfoDmo.setHospitalDesc(hospitalDesc);
                    hospitalInfoDmo.setHospitalAddr(hospitalAddr);
                    hospitalInfoDmo.setImageId(hospitalImage);
                    hospitalInfoDmo.setLevel(hospitalLevel);
                    if (StringUtils.isBlank(parentHospitalName)) {
                        hospitalInfoDmo.setParentHospitalId(0L);
                        hospitalInfoDmo.setRootHospitalId(0L);
                    } else {
                        HospitalInfoDmo dmo = new HospitalInfoDmo();
                        dmo.setHospitalName(parentHospitalName);
                        HospitalInfoDmo infoDmo = (HospitalInfoDmo) commonDao.selectOne(dmo);

                        hospitalInfoDmo.setParentHospitalId(infoDmo.getId());

                        String rootHospitalId = String.valueOf(infoDmo.getRootHospitalId());
                        if (StringUtils.isNotBlank(rootHospitalId) || rootHospitalId.equals("0")) {
                            hospitalInfoDmo.setRootHospitalId(infoDmo.getId());
                        } else {
                            infoDmo.setRootHospitalId(Long.valueOf(rootHospitalId));
                        }

                    }
                    hospitalInfoDmo.setLon(Double.parseDouble(hospitalLon));
                    hospitalInfoDmo.setLat(Double.parseDouble(hospitalLat));
                    hospitalInfoDmo.setAreaCode(hospitalAreacode);
                    hospitalInfoDmo.setHospitalUnion(hospitalUnion);
                    hospitalInfoDmo.setStatus(Constants.HospitalStatus.NORMAL);
                    hospitalList.add(hospitalInfoDmo);
                } else {
                    failedCount++;
                    continue;
                }

            }
        } catch (Exception e) {
            LOGGER.error("exist error row data", e);
            throw new AppException("parse excel hospital data exception");
        }

        if (CollectionUtils.isEmpty(hospitalList)) {
            result.setSuccessCount(successCount);
            result.setFailedCount(failedCount);
            return result;
        }

        AdminImportHospitalListDto adminImportHospitalListDto = new AdminImportHospitalListDto();

        adminImportHospitalListDto.setHospitalList(hospitalList);

        // 插入医院列表
        int i = commonDao.batchInsert("AdminHospitalMapper.addHospitalInsert", adminImportHospitalListDto);

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

    private boolean checkHospitalParam(String hospitalName, String hospitalDesc, String hospitalAddr,
                                       String hospitalImage, String hospitalLevel, String parentHospitalName, String hospitalLon,
                                       String hospitalLat, String hospitalAreacode) {

        // 检查各字段合法性,长度，是否必填
        if (StringUtils.isBlank(hospitalName) || StringUtils.isBlank(hospitalLevel)) {
            return false;
        }

        // 校验医院名称在系统中不存在
        HospitalInfoDmo dmo = new HospitalInfoDmo();
        dmo.setHospitalName(hospitalName);
        HospitalInfoDmo existHospitalDmo = (HospitalInfoDmo)commonDao.selectOne(dmo);

        if (null != existHospitalDmo&& Constants.HospitalStatus.NORMAL.equals(existHospitalDmo.getStatus())) {
            return false;
        }

        if (null != existHospitalDmo&& Constants.HospitalStatus.DELETE.equals(existHospitalDmo.getStatus())){

            int i = commonDao.delete(existHospitalDmo);

            if (i<0){
                return false;
            }

            return true;
        }

        // 校验医院等级合法，并且对应的父级医院存在,并且等级正确
        if (!hospitalLevel.equals("1") && !hospitalLevel.equals("2") && !hospitalLevel.equals("3") && !hospitalLevel.equals("4")) {
            return false;
        }

        if (StringUtils.isNotBlank(parentHospitalName)) {
            HospitalInfoDmo infoDmo = new HospitalInfoDmo();
            infoDmo.setHospitalName(parentHospitalName);
            Long t = commonDao.selectCount("AdminHospitalMapper.selectInferiorHospitalName", infoDmo);
            if (t == 0) {
                return true;
            }
        }

        return true;
    }

    /**
     * 更新医生信息列表
     * @return
     */
    private Result updateDoctorInfoList(DoctorInfoDmo doctorInfoCon,String hospitalName){

        Result result = new Result();

        List<DoctorInfoDmo> existDoctorInfo = commonDao.selectList(doctorInfoCon);

        UpdateDoctorInfoDto updateDoctorInfoDto = new UpdateDoctorInfoDto();

        if (null != existDoctorInfo&&!CollectionUtils.isEmpty(existDoctorInfo)){

            updateDoctorInfoDto.setList(existDoctorInfo);
            updateDoctorInfoDto.setHospitalName(hospitalName);

            int j = commonDao.update("AdminHospitalMapper.updateDoctorInfoList",updateDoctorInfoDto);

            if (j<0){

                result.fail("UHD-0003","更新医生信息失败");
                return result;
            }
        }

        return result;

    }

    /**
     * 更新医生信息列表
     * @return
     */
    private Result updateDoctorMemberList(DoctorMemberDmo doctorMemberCon,String hospitalName){

        Result result = new Result();

        List<DoctorMemberDmo> existDoctorMember = commonDao.selectList(doctorMemberCon);

        UpdateDoctorMemberDto updateDoctorMemberDto = new UpdateDoctorMemberDto();

        if (null != existDoctorMember&&!CollectionUtils.isEmpty(existDoctorMember)){

            updateDoctorMemberDto.setList(existDoctorMember);
            updateDoctorMemberDto.setHospitalName(hospitalName);

            int j = commonDao.update("AdminHospitalMapper.updateDoctorMemberList",updateDoctorMemberDto);

            if (j<0){
                result.fail("UHD-0003","更新医生会员失败");
                return result;
            }
        }

        return result;

    }

}
