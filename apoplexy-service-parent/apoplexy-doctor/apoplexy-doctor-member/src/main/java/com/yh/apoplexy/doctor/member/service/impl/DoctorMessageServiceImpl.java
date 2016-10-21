package com.yh.apoplexy.doctor.member.service.impl;

import com.yh.apoplexy.assist.dao.CommonDao;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMemberDmo;
import com.yh.apoplexy.assist.dmo.doctor.member.DoctorMessageDmo;
import com.yh.apoplexy.assist.dto.doctor.member.MultiDoctorMessageDto;
import com.yh.apoplexy.common.constants.MessageConstants;
import com.yh.apoplexy.common.utils.SqlAssertUtils;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMemberService;
import com.yh.apoplexy.doctor.member.service.intf.DoctorMessageService;
import com.yh.apoplexy.integration.service.intf.PushMessageService;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.Result;
import com.yjh.framework.page.Page;
import com.yjh.framework.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息中心服务实现类
 * Created by wunder on 16/9/10 18:46.
 */
@Service("doctorMessageService")
@ServiceTrace
public class DoctorMessageServiceImpl implements DoctorMessageService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private DoctorMemberService doctorMemberService;

    @Override
    public Result insert(DoctorMessageDmo doctorMessageDmo) {

        int i = commonDao.insert(doctorMessageDmo);

        return SqlAssertUtils.insertAssert(i);
    }

    @Override
    public Result batchInsert(MultiDoctorMessageDto multiDoctorMessageDto) {

        Result result = new Result();

        if (CollectionUtils.isEmpty(multiDoctorMessageDto.getDoctorMemberDmoList())){

            return result;
        }

        int i = commonDao.batchInsert("DoctorMessageMapper.batchInsert",multiDoctorMessageDto);

        if (i!= multiDoctorMessageDto.getDoctorMemberDmoList().size()){

            result.fail("BIM-0001","批量新增消息失败");

            return result;

        }

        return result;
    }

    @Override
    public List<DoctorMessageDmo> selectListByPage(DoctorMessageDmo con, Page page) {
        return commonDao.selectListByPage("DoctorMessageMapper.countMessage","DoctorMessageMapper.queryMessage",con,page);
    }

    @Override
    public Result addMessage(DoctorMessageDmo doctorMessageDmo) {

        Result result = new Result();

        if (null == doctorMessageDmo.getUserId()){
            result.fail("ADM-0000","用户信息为空");
            return result;
        }

        DoctorMemberDmo doctorMemberCon = new DoctorMemberDmo();

        doctorMemberCon.setId(doctorMessageDmo.getUserId());

        DoctorMemberDmo doctorMemberDmo = doctorMemberService.selectOne(doctorMemberCon);

        if (null == doctorMemberDmo)
        {
            result.fail("ADM-0001","用户信息不存在");
            return result;
        }

        String title = MessageConstants.MESSAGE_TYPE_TITLE_MAP.get(doctorMessageDmo.getType());

        String content = MessageConstants.MESSAGE_TYPE_CONTENT_MAP.get(doctorMessageDmo.getType());

        if(StringUtils.isBlank(doctorMessageDmo.getTitle())){
            doctorMessageDmo.setTitle("");
        }

        content = content.replaceAll("\\$\\{title\\}", doctorMessageDmo.getTitle());

        doctorMessageDmo.setTitle(title);
        doctorMessageDmo.setContent(content);
        doctorMessageDmo.setTime(DateUtil.getDate());
        doctorMessageDmo.setUserName(doctorMemberDmo.getDoctorName());
        doctorMessageDmo.setType(MessageConstants.MESSAGE_TYPE_TYPE_MAP.get(doctorMessageDmo.getType()));

        result = insert(doctorMessageDmo);

        if (!result.isSuccess()){
            return result;
        }

        if (StringUtils.isBlank(doctorMemberDmo.getClientId()))
        {
            result.fail("ADM-0002","用户客户端未绑定");
            return result;
        }

        String clientId = doctorMemberDmo.getClientId();

        String logo = "";
        String logoUrl = "";
        String url = "";

        return pushMessageService.pushSingleMessage(clientId,title,content,logo,logoUrl,url);

    }

    @Override
    public Result bathAddMessage(MultiDoctorMessageDto multiDoctorMessageDto) {

        Result result = new Result();

        String title = MessageConstants.MESSAGE_TYPE_TITLE_MAP.get(multiDoctorMessageDto.getType());

        String content = MessageConstants.MESSAGE_TYPE_CONTENT_MAP.get(multiDoctorMessageDto.getType());

        if(StringUtils.isBlank(multiDoctorMessageDto.getTitle())){
            multiDoctorMessageDto.setTitle("");
        }

        content = content.replaceAll("\\$\\{title\\}", multiDoctorMessageDto.getTitle());

        multiDoctorMessageDto.setTitle(title);
        multiDoctorMessageDto.setContent(content);
        multiDoctorMessageDto.setType(MessageConstants.MESSAGE_TYPE_TYPE_MAP.get(multiDoctorMessageDto.getType()));

        result = batchInsert(multiDoctorMessageDto);

        if (!result.isSuccess()){
            return result;
        }

        List<String> clientIdList = new ArrayList<>();

        for(DoctorMemberDmo doctorMemberDmo:multiDoctorMessageDto.getDoctorMemberDmoList()){

            String clientId = doctorMemberDmo.getClientId();

            if (StringUtils.isNotBlank(clientId)){
                clientIdList.add(clientId);
            }

        }

        String logo = "";
        String logoUrl = "";
        String url = "";

        return pushMessageService.pushMultiMessage(clientIdList,title,content,logo,logoUrl,url);

    }

}
