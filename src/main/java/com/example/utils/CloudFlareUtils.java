package com.example.utils;


import cn.hutool.aop.ProxyUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Range
 * @version 1.0
 * @date 2020/12/10 15:40
 */
@Slf4j
@Component
public class CloudFlareUtils {


    private static ProxyUrlConfig proxyUrlConfig;

    @Autowired
    public void setProxyUrlConfig(ProxyUrlConfig proxyUrlConfig) {
        CloudFlareUtils.proxyUrlConfig = proxyUrlConfig;
    }


    /**
     * post form 请求
     *
     * @param url
     * @param params
     * @return
     */
    public static HttpRequestEntity sendPostForm(String url, HttpParams params) {
        return creation(CloudFlareParam.POST, url, params, null, true);
    }

    /**
     * post form 请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static HttpRequestEntity sendPostForm(String url, HttpParams params, HttpHeader headers) {
        return creation(CloudFlareParam.POST, url, params, headers, true);
    }

    /**
     * post json 请求
     *
     * @param url
     * @param body
     * @return
     */
    public static HttpRequestEntity sendPostJson(String url, HttpParams body) {
        return creation(CloudFlareParam.POST, url, body, null, false);
    }

    /**
     * post json 请求
     *
     * @param url
     * @param body
     * @param headers
     * @return
     */
    public static HttpRequestEntity sendPostJson(String url, HttpParams body, HttpHeader headers) {
        return creation(CloudFlareParam.POST, url, body, headers, false);
    }

//---------------------------------------------------get----------------------------------------------------

    /**
     * @param url
     * @return
     */
    public static HttpRequestEntity sendGet(String url) {
        return creation(CloudFlareParam.GET, url, null, null, true);
    }

    /**
     * get请求
     *
     * @param url
     * @param header
     * @return
     */
    public static HttpRequestEntity sendGet(String url, HttpHeader header) {
        return creation(CloudFlareParam.GET, url, null, header, true);
    }


    /**
     * get form
     *
     * @param url
     * @param param
     * @return
     */
    public static HttpRequestEntity sendGetForm(String url, HttpParams param) {
        return creation(CloudFlareParam.GET, url, param, null, true);
    }

    /**
     * get form
     *
     * @param url
     * @param param
     * @param header
     * @return
     */
    public static HttpRequestEntity sendGetForm(String url, HttpParams param, HttpHeader header) {
        return creation(CloudFlareParam.GET, url, param, header, true);
    }


    /**
     * 创建封装请求
     *
     * @param method  请求方式
     * @param url     请求地址
     * @param params  参数
     * @param headers 请求头
     * @param isForm  是否是form请求
     * @return HttpRequestEntity
     */
    public static HttpRequestEntity creation(String method, String url, HttpParams params, HttpHeader headers, boolean isForm) {

        log.info("请求地址:{}", url);
        log.info("请求参数:{}", JSONObject.toJSONString(params));
        log.info("请求响应:{}", JSONObject.toJSONString(headers));

        CloudFlareParam cloudFlare = new CloudFlareParam() {{
            setMethod(method);
            setUrl(url);
            setBody(params);
            setForm(isForm);
        }};
        if (CollectionUtils.isEmpty(headers)) {
            headers = new HttpHeader();
        }
        if (!isForm) {
            headers.put("content-type", CloudFlareParam.JSON);
        }
        // TODO: 2021/8/23 设置 User-Agent

//        headers.put("User-Agent", "User-Agent");
        cloudFlare.setHeaders(headers);

        String req = getLocalhostUrl() + URLUtil.encode(JSONObject.toJSONString(cloudFlare, SerializerFeature.WriteMapNullValue), StandardCharsets.UTF_8);


        log.info("封装请求地址:{}", req);

        HttpResponse creation = send(req);

        HttpRequestEntity httpRequestEntity = new HttpRequestEntity();

        try {
            httpRequestEntity = JSONObject.parseObject(creation.body(), HttpRequestEntity.class);
        } catch (HttpException e) {
            e.printStackTrace();
        }

        return httpRequestEntity;

    }

    /**
     * 发送请求
     * CloudFlare 没有有用的响应头  直接打印带过
     *
     * @param url 封装的请求地址
     * @return
     */
    private static HttpResponse send(String url) {

        HttpResponse response = HttpUtil.createGet(url).execute();

        log.info("状态码:{}", response.getStatus());

        for (Map.Entry<String, List<String>> stringListEntry : response.headers().entrySet()) {
            log.info(stringListEntry.getKey() + ":{}", stringListEntry.getValue());
        }

        return response;
    }


    public static String getLocalhostUrl() {
        return proxyUrlConfig.getUrls().get(new Random().nextInt(proxyUrlConfig.getUrls().size() - 1));
    }
}
