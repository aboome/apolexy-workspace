package com.yh.apoplexy.admin.portal.base.controller;

import com.yh.apoplexy.assist.service.intf.ResourceService;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.MongoDBTableConstants;
import com.yjh.framework.core.mongodb.MongoDbManager;
import com.yjh.framework.web.result.JsonResult;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

/**
 * 视频上传类
 * 
 * @author CC
 * 
 */
@Controller
public class AdminVideoUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminVideoUploadController.class);

	@Autowired
	private MongoDbManager mongoDbManager;
	
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/admin/videoUpload", method = RequestMethod.POST)
	public @ResponseBody
    JsonResult videoUpload(HttpServletRequest request, Model model) {

		LOGGER.info("video upload begin.");

        JsonResult jsonResult = new JsonResult();

		String filePath = request.getParameter("filePath");

		MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartHttpservletRequest.getFile(filePath);

		if (null == multipartFile || multipartFile.isEmpty()) {
            jsonResult.fail("参数非法");
            return jsonResult;
        }

		InputStream in = null;
		ByteArrayOutputStream bos = null;

		try {
			in = multipartFile.getInputStream();

			String fileName = multipartFile.getOriginalFilename();

			bos = new ByteArrayOutputStream();

			BufferedImage image = ImageIO.read(in);

			if (multipartFile.getOriginalFilename().endsWith(".mp4")) {
				ImageIO.write(image, "mp4", bos);
			} else if (multipartFile.getOriginalFilename().endsWith(".ogg")) {
				ImageIO.write(image, "ogg", bos);
			} else {
                jsonResult.fail("上传视频类型错误");
                return jsonResult;
			}

			byte[] bytes = bos.toByteArray();
			HashMap<String, Object> picMap = new HashMap<String, Object>();

			String videoId = UUID.randomUUID().toString().replace("-", "");

			picMap.put(MongoDBTableConstants.ZfzsVideoTable.COLUMN_PIC_ID, videoId);
			picMap.put(MongoDBTableConstants.ZfzsVideoTable.COLUMN_FILENAME, fileName);
			picMap.put(MongoDBTableConstants.ZfzsVideoTable.COLUMN_FILE, bytes);

			String mongoName = MongoDBTableConstants.ZFZS_VIDEO;

			boolean mongoresult = mongoDbManager.gridFSUpload(mongoName, bytes, picMap);

			if (mongoresult) {
				jsonResult.setData(videoId);
				// 持久化信息到资源表
				resourceService.addResource(videoId, fileName, Constants.ResourcesType.PDF);
			} else {
                jsonResult.fail("上传视频失败");
                return jsonResult;
			}
		} catch (IOException e) {
			LOGGER.error("video upload IO exception", e);
            jsonResult.fail("上传视频异常");
            return jsonResult;
		} catch (Exception ex) {
			LOGGER.error("video upload exception", ex);
            jsonResult.fail("上传视频异常");
            return jsonResult;
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
		
		return jsonResult;

	}
}
