package com.yh.apoplexy.doctor.open.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

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
 * 图片上传类
 * 
 * @author CC
 * 
 */
@Controller
public class ImageUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageUploadController.class);

	@Autowired
	private MongoDbManager mongoDbManager;

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/app/imageUpload", method = RequestMethod.POST)
	public @ResponseBody
	DoctorAppBaseResponse imageUpload(HttpServletRequest request, Model model) {

		LOGGER.info("image upload begin.");

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

			BufferedImage image = ImageIO.read(in);

			if (multipartFile.getOriginalFilename().endsWith(".jpg")) {
				ImageIO.write(image, "jpg", bos);
			} else if (multipartFile.getOriginalFilename().endsWith(".png")) {
				ImageIO.write(image, "png", bos);
			} else {
				return generateFailedResponse("上传图片格式错误");
			}

			byte[] bytes = bos.toByteArray();
			HashMap<String, Object> picMap = new HashMap<String, Object>();

			String picId = UUID.randomUUID().toString().replace("-", "");

			picMap.put(MongoDBTableConstants.ZfzsImageTable.COLUMN_PIC_ID, picId);
			picMap.put(MongoDBTableConstants.ZfzsImageTable.COLUMN_FILENAME, fileName);
			picMap.put(MongoDBTableConstants.ZfzsImageTable.COLUMN_FILE, bytes);

			String mongoName = MongoDBTableConstants.ZFZS_IMAGE;

			boolean mongoresult = mongoDbManager.gridFSUpload(mongoName, bytes, picMap);

			if (mongoresult) {
				doctorAppBaseResponse = generateSuccessResponse(picId);

				// 持久化信息到资源表
				resourceService.addResource(picId, fileName, Constants.ResourcesType.IMAGE);
			} else {
				return generateFailedResponse("图片上传失败");
			}
		} catch (IOException e) {
			LOGGER.error("image upload IO exception", e);
			return generateFailedResponse("图片上传异常");
		} catch (Exception ex) {
			LOGGER.error("image upload exception", ex);
			return generateFailedResponse("图片上传异常");
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

		LOGGER.info("image upload end.");

		return doctorAppBaseResponse;

	}

	private DoctorAppBaseResponse generateSuccessResponse(String imageId) {

		DoctorAppBaseResponse doctorAppBaseResponse = new DoctorAppBaseResponse();

		UploadImageResponse uploadImageResponse = new UploadImageResponse();
		
		uploadImageResponse.setImageId(imageId);
		uploadImageResponse.setResultcode(APPResponseCodeConstants.ImageUpload.SUCCESS);
		
		doctorAppBaseResponse.setParameter(uploadImageResponse);
		doctorAppBaseResponse.setServicekey(AppServiceKeyEnum.IMAGE_UPLOAD.getServiceKey());
		doctorAppBaseResponse.setTimestamp(DateUtil.format(DateUtil.getDate(), DateUtil.yyyyMMddHHmmss));
		doctorAppBaseResponse.setUid(UUID.randomUUID().toString().replace("-", ""));
		
		doctorAppBaseResponse = generateSign(doctorAppBaseResponse);
		
		return doctorAppBaseResponse;
	}

	private DoctorAppBaseResponse generateFailedResponse(String errorDesc) {

		DoctorAppBaseResponse doctorAppBaseResponse = new DoctorAppBaseResponse();

		UploadImageResponse uploadImageResponse = new UploadImageResponse();
		
		uploadImageResponse.setResultcode(APPResponseCodeConstants.ImageUpload.FAILED);
		uploadImageResponse.setMessage(errorDesc);
		
		doctorAppBaseResponse.setParameter(uploadImageResponse);
		doctorAppBaseResponse.setServicekey(AppServiceKeyEnum.IMAGE_UPLOAD.getServiceKey());
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
