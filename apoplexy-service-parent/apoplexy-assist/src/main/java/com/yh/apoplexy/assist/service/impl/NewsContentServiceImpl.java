package com.yh.apoplexy.assist.service.impl;

import com.yh.apoplexy.assist.result.ProcessNewsContentResult;
import com.yh.apoplexy.assist.result.UploadImageResult;
import com.yh.apoplexy.assist.service.intf.ImageUploadService;
import com.yh.apoplexy.assist.service.intf.NewContentService;
import com.yjh.framework.core.trace.ServiceTrace;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 资讯内容工具类
 * Created by wunder on 2016/10/11 13:47.
 */
@Service("newContentService")
@ServiceTrace
public class NewsContentServiceImpl implements NewContentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsContentServiceImpl.class);

    @Autowired
    private ImageUploadService imageUploadService;

    @Override
    public ProcessNewsContentResult processVideo(String content) {

        ProcessNewsContentResult result = new ProcessNewsContentResult();

        Document document = Jsoup.parse(content, "UTF-8");

        Elements pElements = document.getElementsByTag("p");

        for (Element p : pElements) {

            String pText = p.html();

            if (-1 != pText.indexOf("iframe")) {

                //去除a标签
                Elements aElements = p.getElementsByTag("a");

                for (Element a : aElements) {
                    a.unwrap();
                }

                //去除span标签
                Elements spanElements = p.getElementsByTag("span");

                for (Element span : spanElements) {
                    span.unwrap();
                }

                pText = p.html();

                //恢复尖括号的转义
                pText = pText.replaceAll("&lt;", "<");
                pText = pText.replaceAll("&gt;", ">");

                //恢复双引号的转义
                pText = pText.replaceAll("&quot;", "\"");

                //恢复&符号的转义
                pText = pText.replaceAll("&amp;", "&");

                p.html(pText);

                Elements iframes = p.getElementsByTag("iframe");

                for (Element iframe : iframes) {

                    iframe.attr("width", "100%");

                }

            }

        }

        result.setContent(document.body().html().toString());

        return result;

    }

    public ProcessNewsContentResult processImage(String content) {

        ProcessNewsContentResult result = new ProcessNewsContentResult();

        Document document = Jsoup.parse(content, "UTF-8");

        Elements images = document.getElementsByTag("img");

        for (Element image : images) {

            String href = image.attr("src");

            String title = image.attr("title");

            image.attr("width", "100%");
            image.attr("height", "auto");

            UploadImageResult uploadImageResult = imageUploadService.imageUpload(href, title);

            if (!uploadImageResult.isSuccess()){

                result.copy(uploadImageResult);
                return result;

            }

            image.attr("src", uploadImageResult.getUrl());

        }

        result.setContent(document.body().html().toString());

        return result;

    }

    public ProcessNewsContentResult recoveryImage(String content, String baseUrl) {

        ProcessNewsContentResult result = new ProcessNewsContentResult();

        Document document = Jsoup.parse(content, "UTF-8");

        Elements images = document.getElementsByTag("img");

        for (Element image : images) {

            String href = image.attr("src");

            image.attr("src", baseUrl + href);

        }

        result.setContent(document.body().html().toString());

        return result;

    }

}
