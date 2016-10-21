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
 * 图片上传类
 * 
 * @author CC
 * 
 */
@Controller
public class AdminPdfUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminPdfUploadController.class);

	@Autowired
	private MongoDbManager mongoDbManager;
	
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/admin/pdfUpload", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult pdfUpload(HttpServletRequest request, Model model) {

		LOGGER.info("pdf upload begin.");

		JsonResult jsonResult = new JsonResult();

		String filePath = request.getParameter("filePath");

		MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartHttpservletRequest.getFile(filePath);

		if (null == multipartFile || multipartFile.isEmpty()) {
			jsonResult.fail("上传PDF为空.");
			return jsonResult;
		}

		InputStream in = null;
		ByteArrayOutputStream bos = null;

		try {
			in = multipartFile.getInputStream();

			String fileName = multipartFile.getOriginalFilename();

			bos = new ByteArrayOutputStream();

			BufferedImage image = ImageIO.read(in);

			if (multipartFile.getOriginalFilename().endsWith(".pdf")) {
				ImageIO.write(image, "pdf", bos);
			} else {
				jsonResult.fail("上传PDF格式错误.");
				return jsonResult;
			}

			byte[] bytes = bos.toByteArray();
			HashMap<String, Object> picMap = new HashMap<String, Object>();

			String pdfId = UUID.randomUUID().toString().replace("-", "");

			picMap.put(MongoDBTableConstants.ZfzsPdfTable.COLUMN_PIC_ID, pdfId);
			picMap.put(MongoDBTableConstants.ZfzsPdfTable.COLUMN_FILENAME, fileName);
			picMap.put(MongoDBTableConstants.ZfzsPdfTable.COLUMN_FILE, bytes);

			String mongoName = MongoDBTableConstants.ZFZS_PDF;

			boolean mongoresult = mongoDbManager.gridFSUpload(mongoName, bytes, picMap);

			if (mongoresult) {
				jsonResult.setData(pdfId);
				jsonResult.setSuccess(true);
				
				// 持久化信息到资源表 
				resourceService.addResource(pdfId, fileName, Constants.ResourcesType.PDF);
			} else {
				jsonResult.fail("pdf upload error.");
			}
		} catch (IOException e) {
			jsonResult.fail("system error.");
			LOGGER.error("pdf upload IO exception", e);
		} catch (Exception ex) {
			jsonResult.fail("system error.");
			LOGGER.error("pdf upload exception", ex);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					jsonResult.fail("system error.");
					LOGGER.error("in close IO exception", e);
				}
			}
			
			if (null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
					jsonResult.fail("system error.");
					LOGGER.error("bos close IO exception", e);
				}
			}
		}

		LOGGER.info("pdf upload end.");
		
		return jsonResult;

	}
}
