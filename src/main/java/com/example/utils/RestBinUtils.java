package com.example.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.*;
import com.example.entity.cloudflare.CloudFlareResponseBody;
import com.example.entity.restbin.RestBinBody;
import com.example.entity.restbin.RestBinConfig;
import com.example.entity.restbin.RestBinJson;
import com.example.entity.restbin.RestBinRequestBody;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Range
 * @version 1.0
 * @date 2021/8/24 9:52
 */
@Slf4j
public class RestBinUtils {

    private static final String URL = "https://apide.reqbin.com/api/v1/requests";

//    ---------------------get

    /**
     * get
     *
     * @param url
     * @return
     */
    public static RestBinRequestBody sendGet(String url) {

        return sendGet(url, null);
    }

    /**
     * get
     *
     * @param url
     * @param httpHeader
     * @return
     */
    public static RestBinRequestBody sendGet(String url, HttpHeader httpHeader) {

        return request(url, RestBinConfig.METHOD_GET, null, httpHeader, false);
    }


    /**
     * form
     *
     * @param url
     * @param httpParams
     * @return
     */
    public static RestBinRequestBody sendGetForm(String url, HttpParams httpParams) {

        return sendGetForm(url, httpParams, null);
    }

    /**
     * form
     *
     * @param url
     * @param httpParams
     * @param httpHeader
     * @return
     */
    public static RestBinRequestBody sendGetForm(String url, HttpParams httpParams, HttpHeader httpHeader) {

        return request(url, RestBinConfig.METHOD_GET, httpParams, httpHeader, true);
    }


    /**
     * json
     *
     * @param url
     * @param httpParams
     * @return
     */
    public static RestBinRequestBody sendGetJson(String url, HttpParams httpParams) {

        return sendGetJson(url, httpParams, null);
    }


    /**
     * json
     *
     * @param url
     * @param httpParams
     * @param httpHeader
     * @return
     */
    public static RestBinRequestBody sendGetJson(String url, HttpParams httpParams, HttpHeader httpHeader) {

        return request(url, RestBinConfig.METHOD_GET, httpParams, httpHeader, false);
    }


//    ------------------------------------post

    /**
     * form
     *
     * @param url
     * @param httpParams
     * @return
     */
    public static RestBinRequestBody sendPostForm(String url, HttpParams httpParams) {

        return sendPostForm(url, httpParams, null);
    }

    /**
     * form
     *
     * @param url
     * @param httpParams
     * @param httpHeader
     * @return
     */
    public static RestBinRequestBody sendPostForm(String url, HttpParams httpParams, HttpHeader httpHeader) {

        return request(url, RestBinConfig.METHOD_POST, httpParams, httpHeader, true);
    }


    /**
     * json
     *
     * @param url
     * @param httpParams
     * @return
     */
    public static RestBinRequestBody sendPostJson(String url, HttpParams httpParams) {

        return sendPostJson(url, httpParams, null);
    }


    /**
     * json
     *
     * @param url
     * @param httpParams
     * @param httpHeader
     * @return
     */
    public static RestBinRequestBody sendPostJson(String url, HttpParams httpParams, HttpHeader httpHeader) {

        return request(url, RestBinConfig.METHOD_POST, httpParams, httpHeader, false);
    }

//    -----------------------------------core

    /**
     * 请求方法
     *
     * @param url        url
     * @param method     请求方法  RestBinConfig
     * @param httpParams 请求参数
     * @param httpHeader 请求头
     * @param isForm     是否是表单
     * @return
     */
    public static RestBinRequestBody request(String url, String method, HttpParams httpParams, HttpHeader httpHeader, Boolean isForm) {

        HttpParams<Object> req = creation(url, method, httpParams, httpHeader, isForm);

        RestBinRequestBody restBinRequestBody = send(req);

        setHeadersList(restBinRequestBody);

        log.info("RestBin响应: {}", JSONObject.toJSONString(httpHeader));

        return restBinRequestBody;
    }


    /**
     * 发送请求
     *
     * @param req
     * @return
     */
    private static RestBinRequestBody send(HttpParams<Object> req) {

        CloudFlareResponseBody cloudFlareResponseBody = CloudFlareUtils.sendPostJson(URL, req);

        return JSONObject.parseObject(cloudFlareResponseBody.getEntity(), RestBinRequestBody.class);

    }

    /**
     * 封装参数
     *
     * @param url
     * @param method
     * @param httpParams
     * @param httpHeader
     * @param isForm
     * @return
     */
    private static HttpParams<Object> creation(String url, String method, HttpParams httpParams, HttpHeader httpHeader, Boolean isForm) {

        log.info("RestBin参数: {}", JSONObject.toJSONString(httpParams));

        RestBinBody restBinBody = new RestBinBody();

        RestBinJson restBinJson = new RestBinJson() {{
            setMethod(method);
            setIdnUrl(url);
            setUrl(url);
        }};

        restBinBody.setJson(JSONObject.toJSONString(restBinJson));

        //如果参数为空  请求为query
        if (ObjectUtil.isNotNull(httpParams)) {
            if (isForm) {
                restBinJson.setContentType(RestBinConfig.FORM);
                restBinJson.setContent(HttpUtil.toParams(httpParams));
            } else {
                restBinJson.setContentType(RestBinConfig.JSON);
                restBinJson.setContent(JSONObject.toJSONString(httpParams));
            }
        }

        if (ObjectUtil.isNotNull(httpHeader)) {
            StringBuilder sb = new StringBuilder();

            for (Map.Entry<String, String> sse : httpHeader.entrySet()) {
                sb.append(sse.getKey()).append(":").append(sse.getValue()).append("/");
            }

            restBinJson.setHeaders(sb.toString());
        }

        HttpParams<Object> req = new HttpParams<>();

        BeanUtil.copyProperties(restBinBody, req);

        return req;
    }

    /**
     * 处理响应头
     *
     * @param body
     */
    private static void setHeadersList(RestBinRequestBody body) {

        String headers = body.getHeaders();

        if (StrUtil.isNotBlank(headers)) {
            //拼接header
            String[] split = headers.split("\r\n");

            JSONObject jsonObject = new JSONObject();

            for (String s : split) {

                String[] item = s.split(": ");

                jsonObject.put(item[0], item[1]);
            }

            body.setHeadersList(jsonObject);
        }
    }


}
