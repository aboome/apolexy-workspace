package com.yh.apoplexy.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.common.constants.PropertiesConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 百度地图工具类
 * Created by wunder on 16/9/14 16:24.
 */
public class BaiDuMapUtil {

    private static final String baiduMapAk = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.BAIDU_MAP_AK);
    private static final String baiduMapSk = PropertiesUtil.getString(PropertiesConstants.APPLICATION_ENV_CONFIG, PropertiesConstants.BAIDU_MAP_SK);

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiDuMapUtil.class);

    /**
     * 根据经纬度获取区域代码
     *
     * @param lat 纬度
     * @param lon 经度
     */
    public final static String getLocationInfo(String lat, String lon) {

        // ak设置了sn校验不能直接使用必须在url最后附上sn值，get请求计算sn跟url中参数对出现顺序有关，需按序填充paramsMap
        Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("location", lat + "," + lon);
        paramsMap.put("pois", "0");
        paramsMap.put("output", "json");
        paramsMap.put("ak", baiduMapAk);

        // 调用下面的toQueryString方法，对paramsMap内所有value作utf8编码
        String paramsStr = toQueryString(paramsMap);

        // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接sk
        String wholeStr = new String("/geocoder/v2/?" + paramsStr + baiduMapSk);

        // 对上面wholeStr再作utf8编码
        String tempStr = null;
        try {
            tempStr = URLEncoder.encode(wholeStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.toString());
        }

        // 调用下面的MD5方法得到sn签名值
        String sn = EncryptUtil.md5Encrypt(tempStr);

        // 算得sn后发送get请求
        String locationString = new HttpUtil().getRequest("http://api.map.baidu.com/geocoder/v2/?" + toQueryString(paramsMap)+"&sn=" + sn);

        JSONObject resultObj = JSONObject.parseObject(locationString);

        String status = resultObj.getString("status");

        if (StringUtils.isBlank(status) || !status.equals("0")) {
            LOGGER.error(resultObj.getString("message"));
            return null;
        }

        JSONObject locationObj = resultObj.getJSONObject("result").getJSONObject("addressComponent");

        if (null == locationObj) {
            LOGGER.error("百度地图API获取位置信息失败");
            return null;
        }

        return locationObj.getString("adcode");
    }

    // 对Map内所有value作utf8编码，拼接返回结果
    private static String toQueryString(Map<?, ?> data) {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            try {
                queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8") + "&");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error(e.toString());
            }
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    public static void main(String[] args) {
        System.out.println(getLocationInfo("32","112"));
    }
}
