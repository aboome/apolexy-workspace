/**
 * 
 */
package com.yh.apoplexy.doctor.open.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.gridfs.GridFSDBFile;
import com.yh.apoplexy.common.constants.MongoDBTableConstants;
import com.yjh.framework.core.mongodb.MongoDbManager;

/**
 * 图片查看
 * @author 慧昌
 *
 */
@Controller
public class ImageViewController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageViewController.class);

	@Autowired
	private MongoDbManager mongoDbManager;

	@RequestMapping(value = "/app/image/view/{imageId}", method = RequestMethod.GET)
	public void viewIamge(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="imageId") String imageId) {
	
		LOGGER.info("image view begin");
		
        HashMap<String, Object> picMap = new HashMap<String, Object>();
        
        String mongoName = MongoDBTableConstants.ZFZS_IMAGE;
        
        picMap.put(MongoDBTableConstants.ZfzsImageTable.COLUMN_PIC_ID, imageId);
        
		GridFSDBFile dbFile = mongoDbManager.findFile(mongoName, picMap);
		
		if (null == dbFile) {
			LOGGER.error("image not exist");
			return;
		}
		
		byte[] bytes1 = (byte[]) dbFile.get("file");
		
		ServletOutputStream out = null;
		
		try {
			out = response.getOutputStream();
			out.write(bytes1,0,bytes1.length);
			out.flush();
		} catch (IOException e) {
			LOGGER.error("image view IOException", e);
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					LOGGER.error("out close IOException", e);
				}
			}
		}

	}
}
