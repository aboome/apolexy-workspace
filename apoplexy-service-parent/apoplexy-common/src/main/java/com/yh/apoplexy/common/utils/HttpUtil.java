package com.yh.apoplexy.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Http工具类
 * Created by wunder on 16/9/15 12:55.
 */
public class HttpUtil {

    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 3000;
    //读取超时时间
    private static final int READ_TIMEOUT = 5000;
    // 参数编码
    private static final String ENCODE_CHARSET = "utf-8";

    /**
     * 发送HTTP_GET请求
     *
     * @param httpUrl 请求地址
     * @return String
     */
    public final static String getRequest(String httpUrl) {

        StringBuilder resultData = new StringBuilder();

        URL url = null;
        try {

            url = new URL(httpUrl);

        } catch (MalformedURLException e) {

            e.printStackTrace();

        }

        HttpURLConnection urlConn = null;

        InputStreamReader in = null;

        BufferedReader buffer = null;

        String inputLine = null;

        if (url != null) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("GET");
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
                in = new InputStreamReader(urlConn.getInputStream(), HttpUtil.ENCODE_CHARSET);
                buffer = new BufferedReader(in);
                if (urlConn.getResponseCode() == 200) {
                    while ((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (buffer != null) {
                        buffer.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultData.toString();
    }

}
