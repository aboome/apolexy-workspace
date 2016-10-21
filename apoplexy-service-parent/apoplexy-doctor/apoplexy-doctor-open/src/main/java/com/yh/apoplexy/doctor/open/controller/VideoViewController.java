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
 * 视频查看
 * @author 慧昌
 *
 */
@Controller
public class VideoViewController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VideoViewController.class);

	@Autowired
	private MongoDbManager mongoDbManager;

	@RequestMapping(value = "/app/video/view/{videoId}", method = RequestMethod.GET)
	public void viewVideo(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="videoId") String videoId) {
	
		LOGGER.info("video view begin");
		
        HashMap<String, Object> videoMap = new HashMap<String, Object>();
        
        String mongoName = MongoDBTableConstants.ZFZS_VIDEO;
        
        videoMap.put(MongoDBTableConstants.ZfzsVideoTable.COLUMN_PIC_ID, videoId);
        
		GridFSDBFile dbFile = mongoDbManager.findFile(mongoName, videoMap);
		
		if (null == dbFile) {
			LOGGER.error("video not exist");
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
