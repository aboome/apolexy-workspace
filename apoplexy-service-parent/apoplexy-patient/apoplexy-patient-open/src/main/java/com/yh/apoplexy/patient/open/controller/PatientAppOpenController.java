package com.yh.apoplexy.patient.open.controller;

import com.alibaba.fastjson.JSONObject;
import com.yh.apoplexy.patient.open.proxy.intf.PatientAppOpenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 患者端APP接口控制类
 * Created by wunder on 16/9/4 14:17.
 */
@Controller
public class PatientAppOpenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientAppOpenController.class);

    @Autowired
    private PatientAppOpenService patientAppOpenService;

    /**
     * 医生端APP接口统一入口
     * @param request
     * @param response
     */
    @RequestMapping(value = "/app/serviceApi")
    public void appOpenApi(HttpServletRequest request, HttpServletResponse response) {

        // 获取request输入流
        String requestString = getRequestMsg(request);

        System.out.println(requestString);
        LOGGER.info("the request is: "+requestString);

        if (StringUtils.isBlank(requestString)) {
            LOGGER.error("the msg is null");
            return;
        }

        // 获取serviceKey
        String serviceKey = getServiceKey(requestString);
        System.out.println(serviceKey);
        // 处理器执行业务
        String responseString = patientAppOpenService.proxy(requestString, serviceKey);

        LOGGER.info("the response is: "+responseString);

        // 写入response输出流
        writeResponse(response, responseString);
    }

    /**
     * 返回响应消息
     * @param response
     * @param responseString
     */
    private void writeResponse(HttpServletResponse response, String responseString) {

        response.setHeader("Content-Type", "text/html; charset=UTF-8");

        byte[] b = null;
        try {
            b = responseString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            LOGGER.error("response error" + e1.toString());
            return;
        }

        OutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(b);
            out.flush();
        } catch (IOException e) {

            LOGGER.error("response error" + e.toString());

        }finally{
            try {
                out.close();
            } catch (IOException e) {

                LOGGER.error("close error" + e.toString());

            }
        }
    }

    /**
     * 获取业务编码
     * @param requestString
     * @return
     */
    private String getServiceKey(String requestString) {

        JSONObject jsonObject = JSONObject.parseObject(requestString);

        String serviceKey = jsonObject.getString("servicekey");

        return serviceKey;
    }

    /**
     * 获取请求消息
     * @param request
     * @return
     */
    private String getRequestMsg(HttpServletRequest request) {

        InputStream is = null;

        BufferedReader in = null;

        try {

            is = request.getInputStream();

            in = new BufferedReader(new InputStreamReader(is));

            StringBuffer buffer = new StringBuffer();

            String line = "";

            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }

            String str = buffer.toString();

            if (StringUtils.isBlank(str)) {
                LOGGER.error("request msg is empty or null");
            }

            return str;

        } catch (IOException e) {
            LOGGER.error("get request msg occur io exception", e);
            return null;
        } catch (Exception ex) {
            LOGGER.error("get request msg occur exception", ex);
            return null;
        } finally {

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("is close io occur exception", e);
                }
            }

            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error("in close io occur exception", e);
                }
            }
        }
    }

}
