/**
 * 
 */
package com.yh.apoplexy.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yjh.framework.utils.DateUtil;

/**
 * 导入文件工具类
 * 
 * @author CC
 *
 */
public class ImportFileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ImportFileUtil.class);

    public static Date getFomartDate(String date, boolean isBeginDay) {
        Date d = null;
        if (StringUtils.isEmpty(date)) {
            // if (isBeginDay) {
            // d = DateUtil.getZeroTimeOfDay(DateUtil.getDate());
            // } else {
            // d = DateUtil.getDate();
            // }
        } else {
            d = DateUtil.parseDate(date, DateUtil.yyyy_MM_dd_HH_mm_ss);
        }
        return d;
    }

    /**
     * @param in
     */
    public static boolean saveFile(InputStream in, String desFilePath) {
        try {
            File file = new File(desFilePath);
            if (!file.exists())
                file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            int c;
            byte buffer[] = new byte[1024];
            while ((c = in.read(buffer)) != -1) {
                for (int i = 0; i < c; i++)
                    out.write(buffer[i]);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            logger.info("FileNotFoundException,file path:{},exception:{}", desFilePath, e.toString());
            return false;
        } catch (IOException e) {
            logger.info("IOException,{}", e.toString());
            return false;
        } catch (Exception e) {
            logger.info("Exception,{}", e);
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        saveFile(null, "E:/20141017113642.xlsx");
    }
}
