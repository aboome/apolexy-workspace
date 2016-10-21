package com.yh.apoplexy.doctor.open.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.yh.apoplexy.doctor.open.response.common.UploadVideoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.yh.apoplexy.assist.service.intf.ResourceService;
import com.yh.apoplexy.common.constants.APPResponseCodeConstants;
import com.yh.apoplexy.common.constants.AppServiceKeyEnum;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.MongoDBTableConstants;
import com.yh.apoplexy.common.utils.EncryptUtil;
import com.yh.apoplexy.doctor.open.response.base.DoctorAppBaseResponse;
import com.yh.apoplexy.doctor.open.response.common.UploadImageResponse;
import com.yjh.framework.core.mongodb.MongoDbManager;
import com.yjh.framework.utils.DateUtil;

/**
 * 视频上传类
 * 
 * @author CC
 * 
 */
@Controller
public class VideoUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VideoUploadController.class);

	@Autowired
	private MongoDbManager mongoDbManager;
	
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/app/videoUpload", method = RequestMethod.POST)
	public @ResponseBody
	DoctorAppBaseResponse videoUpload(HttpServletRequest request, Model model) {

		LOGGER.info("video upload begin.");

		DoctorAppBaseResponse doctorAppBaseResponse = new DoctorAppBaseResponse();

		String filePath = request.getParameter("filePath");

		MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartHttpservletRequest.getFile(filePath);

		if (null == multipartFile || multipartFile.isEmpty()) {
			return generateFailedResponse("参数非法");
		}

		InputStream in = null;
		ByteArrayOutputStream bos = null;

		try {
			in = multipartFile.getInputStream();

			String fileName = multipartFile.getOriginalFilename();

			bos = new ByteArrayOutputStream();

            int c;

            byte buffer[] = new byte[1024];

            while ((c = in.read(buffer)) != -1) {
                for (int i = 0; i < c; i++)
                    bos.write(buffer[i]);
            }

            in.close();

            bos.close();

			byte[] bytes = bos.toByteArray();
			HashMap<String, Object> picMap = new HashMap<String, Object>();

			String videoId = UUID.randomUUID().toString().replace("-", "");

			picMap.put(MongoDBTableConstants.ZfzsVideoTable.COLUMN_PIC_ID, videoId);
			picMap.put(MongoDBTableConstants.ZfzsVideoTable.COLUMN_FILENAME, fileName);
			picMap.put(MongoDBTableConstants.ZfzsVideoTable.COLUMN_FILE, bytes);

			String mongoName = MongoDBTableConstants.ZFZS_VIDEO;

			boolean mongoresult = mongoDbManager.gridFSUpload(mongoName, bytes, picMap);

			if (mongoresult) {
				doctorAppBaseResponse = generateSuccessResponse(videoId);
				
				// 持久化信息到资源表
				resourceService.addResource(videoId, fileName, Constants.ResourcesType.PDF);
			} else {
				return generateFailedResponse("上传视频失败");
			}
		} catch (IOException e) {
			LOGGER.error("video upload IO exception", e);
			return generateFailedResponse("上传视频异常");
		} catch (Exception ex) {
			LOGGER.error("video upload exception", ex);
			return generateFailedResponse("上传视频异常");
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					LOGGER.error("in close IO exception", e);
				}
			}
			
			if (null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
					LOGGER.error("bos close IO exception", e);
				}
			}
		}

		LOGGER.info("video upload end.");
		
		return doctorAppBaseResponse;

	}
	
	private DoctorAppBaseResponse generateSuccessResponse(String videoId) {

		DoctorAppBaseResponse doctorAppBaseResponse = new DoctorAppBaseResponse();

        UploadVideoResponse uploadVideoResponse = new UploadVideoResponse();

        uploadVideoResponse.setVideoId(videoId);
        uploadVideoResponse.setResultcode(APPResponseCodeConstants.VideoUpload.SUCCESS);
		
		doctorAppBaseResponse.setParameter(uploadVideoResponse);
		doctorAppBaseResponse.setServicekey(AppServiceKeyEnum.VIDEO_UPLOAD.getServiceKey());
		doctorAppBaseResponse.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
		doctorAppBaseResponse.setUid(UUID.randomUUID().toString().replace("-", ""));
		
		doctorAppBaseResponse = generateSign(doctorAppBaseResponse);
		
		return doctorAppBaseResponse;
	}

	private DoctorAppBaseResponse generateFailedResponse(String errorDesc) {

		DoctorAppBaseResponse doctorAppBaseResponse = new DoctorAppBaseResponse();

		UploadVideoResponse uploadVideoResponse = new UploadVideoResponse();

        uploadVideoResponse.setResultcode(APPResponseCodeConstants.VideoUpload.FAILED);
        uploadVideoResponse.setMessage(errorDesc);
		
		doctorAppBaseResponse.setParameter(uploadVideoResponse);
		doctorAppBaseResponse.setServicekey(AppServiceKeyEnum.VIDEO_UPLOAD.getServiceKey());
		doctorAppBaseResponse.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
		doctorAppBaseResponse.setUid(UUID.randomUUID().toString().replace("-", ""));
		
		doctorAppBaseResponse = generateSign(doctorAppBaseResponse);

		return doctorAppBaseResponse;
	}
	
	private DoctorAppBaseResponse generateSign(DoctorAppBaseResponse resultObject) {

        resultObject.setSign(null);

        String signInputString = JSON.toJSONString(resultObject).replace(" ", "").replace("\r", "").replace("\n", "");

        String signString = EncryptUtil.md5Encrypt(signInputString);

        resultObject.setSign(signString);

        return resultObject;
    }
}
