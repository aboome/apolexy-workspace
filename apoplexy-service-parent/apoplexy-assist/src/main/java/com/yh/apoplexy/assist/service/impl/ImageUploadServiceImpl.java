package com.yh.apoplexy.assist.service.impl;

import com.yh.apoplexy.assist.result.UploadImageResult;
import com.yh.apoplexy.assist.service.intf.ImageUploadService;
import com.yh.apoplexy.assist.service.intf.ResourceService;
import com.yh.apoplexy.common.constants.Constants;
import com.yh.apoplexy.common.constants.MongoDBTableConstants;
import com.yjh.framework.core.mongodb.MongoDbManager;
import com.yjh.framework.core.trace.ServiceTrace;
import com.yjh.framework.lang.AppException;
import com.yjh.framework.web.result.JsonResult;
import com.yjh.framework.web.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

/**
 * 图片上传工具类
 * Created by wunder on 2016/10/11 16:28.
 */
@Service("imageUploadService")
@ServiceTrace
public class ImageUploadServiceImpl implements ImageUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUploadServiceImpl.class);

    @Autowired
    private MongoDbManager mongoDbManager;

    @Autowired
    private ResourceService resourceService;


    private byte[] readInputStream(InputStream inStream) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[2048];

        int len = 0;

        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }

        inStream.close();
        outStream.close();

        return outStream.toByteArray();
    }

    @Override
    public UploadImageResult imageUpload(String href, String fileName) {

        UploadImageResult result = new UploadImageResult();

        try {

            URL url = new URL(href);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(3000);

            connection.connect();

            InputStream inStream = connection.getInputStream();

            byte[] bytes = readInputStream(inStream);

            HashMap<String, Object> picMap = new HashMap<String, Object>();

            String picId = UUID.randomUUID().toString().replace("-", "");

            String newUrl = "/admin/image/view/" + picId;

            picMap.put(MongoDBTableConstants.ZfzsImageTable.COLUMN_PIC_ID, picId);
            picMap.put(MongoDBTableConstants.ZfzsImageTable.COLUMN_FILENAME, fileName);
            picMap.put(MongoDBTableConstants.ZfzsImageTable.COLUMN_FILE, bytes);

            String mongoName = MongoDBTableConstants.ZFZS_IMAGE;

            boolean mongoResult = mongoDbManager.gridFSUpload(mongoName, bytes, picMap);

            if (mongoResult) {

                // 持久化信息到资源表
                resourceService.addResource(picId, fileName, Constants.ResourcesType.IMAGE);

                result.setUrl(newUrl);

                return result;

            }

        } catch (MalformedURLException e) {
            LOGGER.error(e.toString());
            throw new AppException(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.toString());
            throw new AppException(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.toString());
            throw new AppException(e.getMessage());
        }

        result.fail("ULI-0001","上传图片失败");

        return result;

    }
}

