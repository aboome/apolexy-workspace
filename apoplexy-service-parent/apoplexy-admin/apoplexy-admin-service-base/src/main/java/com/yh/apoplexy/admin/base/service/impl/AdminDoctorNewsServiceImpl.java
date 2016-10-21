package com.yh.apoplexy.admin.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.apoplexy.admin.doctor.member.service.intf.AdminDoctorMemberService;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dto.doctor.member.MultiDoctorMessageDto;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.MessageConstants;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.apoplexy.admin.base.service.intf.AdminDoctorNewService;
import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.information.DoctorNewsDmo;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;

@Service("adminDoctorNewService")
@ServiceTrace
public class AdminDoctorNewsServiceImpl implements AdminDoctorNewService {
	
	@Autowired
	private CommonDao commonDao;

    @Autowired
    private AdminDoctorMemberService adminDoctorMemberService;

    @Autowired
    private DoctorMessageService doctorMessageService;

	@Override
	public List<DoctorNewsDmo> selectListByPage(
            DoctorNewsDmo doctorNewsDmo, Page page) {
         
		return commonDao.selectListByPage("AdminDoctorNewsMapper.doctorNewsCount", "AdminDoctorNewsMapper.doctorNewsList", doctorNewsDmo, page);
	}

	@Override
	public DoctorNewsDmo selectDoctorNewsDetails(
			DoctorNewsDmo doctorNewsDmo) {
		
		return (DoctorNewsDmo) commonDao.selectOne(doctorNewsDmo);
	}

	@Override
	public Result addDoctorNews(DoctorNewsDmo doctorNewsDmo) {
		int i = commonDao.insert(doctorNewsDmo);
		return SqlAssertUtils.insertAssert(i);
	}

	@Override
	public Result updateDoctorNewsDetails(DoctorNewsDmo doctorNewsDmo) {
		int i = commonDao.update(doctorNewsDmo);
		return SqlAssertUtils.updateAssert(i);
	}

	@Override
	public Result deleteDoctorNewsDetails(DoctorNewsDmo doctorNewsDmo) {
		int i = commonDao.delete(doctorNewsDmo);
		return SqlAssertUtils.deleteAssert(i);
	}

    @Override
    public Result pushMessage(DoctorNewsDmo doctorNewsDmo) {

        Result result = new Result();

        MultiDoctorMessageDto multiDoctorMessageDto = new MultiDoctorMessageDto();

        multiDoctorMessageDto.setContent(doctorNewsDmo.getContent());
        multiDoctorMessageDto.setTitle(doctorNewsDmo.getTitle());
        multiDoctorMessageDto.setType(MessageConstants.MESSAGE_INFORMATION_KNOWLEDGE);

        int pageSize = 100;

        Page page = new Page();

        page.setPageSize(pageSize);
        page.setCurrentPage(1);

        DoctorMemberDmo con = new DoctorMemberDmo();

        con.setStatus(Constants.DoctorStatus.NORMAL);

        List<DoctorMemberDmo> doctorMemberList = adminDoctorMemberService.selectListByPage(con,page);

        List<DoctorMemberDmo> memberList = new ArrayList<>();

        memberList.addAll(doctorMemberList);

        long count = page.getCount();

        for (int i = 2;i< 1+count/pageSize;i++){

            page.setCurrentPage(i);

            doctorMemberList = adminDoctorMemberService.selectListByPage(con,page);

            memberList.addAll(doctorMemberList);

            if (0 == i%10){

                multiDoctorMessageDto.setDoctorMemberDmoList(memberList);
                doctorMessageService.bathAddMessage(multiDoctorMessageDto);
                memberList.clear();

            }

        }

        multiDoctorMessageDto.setDoctorMemberDmoList(memberList);
        doctorMessageService.bathAddMessage(multiDoctorMessageDto);

        return result;
    }

}
