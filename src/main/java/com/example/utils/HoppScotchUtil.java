package com.example.utils;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.entity.*;
import com.example.entity.cloudflare.CloudFlareResponseBody;
import com.example.entity.hoppscotch.HoppScotchConfig;
import com.example.entity.hoppscotch.HoppScotchRequestBody;
import com.example.entity.hoppscotch.HoppScotchResponseBody;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Range
 * @version 1.0
 * @date 2021/3/17 16:28
 */
@Slf4j
public class HoppScotchUtil {

    private static final String URL = "https://proxy.hoppscotch.io/";


    /**
     * get 请求
     *
     * @param url 请求地址
     * @return
     */
    public static HoppScotchResponseBody sendGet(String url) {
        return sendGet(url, null, null, StandardCharsets.UTF_8);
    }

    /**
     * get 请求
     *
     * @param url     请求地址
     * @param charset 响应字节码
     * @return
     */
    public static HoppScotchResponseBody sendGet(String url, Charset charset) {
        return sendGet(url, null, null, charset);
    }

    /**
     * get 请求
     *
     * @param url        请求地址
     * @param httpHeader header
     * @return
     */
    public static HoppScotchResponseBody sendGet(String url, HttpHeader httpHeader) {

        return sendGet(url, null, httpHeader);
    }

    /**
     * get 请求
     *
     * @param url        请求地址
     * @param httpHeader header
     * @param charset    响应字节码
     * @return
     */
    public static HoppScotchResponseBody sendGet(String url, HttpHeader httpHeader, Charset charset) {

        return sendGet(url, null, httpHeader, charset);
    }

    /**
     * get 请求
     *
     * @param url        请求地址
     * @param httpParams 请求参数 param
     * @return
     */
    public static HoppScotchResponseBody sendGet(String url, HttpParams httpParams) {

        return sendGet(url, httpParams, null, StandardCharsets.UTF_8);
    }

    /**
     * get
     *
     * @param url        请求地址
     * @param httpParams 请求参数
     * @param charset    响应字节码
     * @return
     */
    public static HoppScotchResponseBody sendGet(String url, HttpParams httpParams, Charset charset) {

        return sendGet(url, httpParams, null, charset);
    }

//---------------- param ------header

    /**
     * get 请求
     *
     * @param url        请求地址
     * @param httpParams 请求参数 param
     * @return
     */
    public static HoppScotchResponseBody sendGet(String url, HttpParams httpParams, HttpHeader httpHeader) {

        return sendGet(url, httpParams, httpHeader, StandardCharsets.UTF_8);
    }


    /**
     * get 请求
     *
     * @param url        请求地址
     * @param httpParams 请求参数
     * @param charset    响应字节码
     * @return
     */
    public static HoppScotchResponseBody sendGet(String url, HttpParams httpParams, HttpHeader httpHeader, Charset charset) {

        HoppScotchRequestBody body = new HoppScotchRequestBody();

        body.setUrl(url);

        if (CollectionUtil.isNotEmpty(httpParams)) {
            body.setUrl(String.format("%s?%s", url, HttpUtil.toParams(httpParams)));
        }

        if (CollectionUtil.isNotEmpty(httpHeader)) {
            body.setHeaders(httpHeader);
        }


        return getHttpResponseBody(body, charset);
    }

//------------------------json----param

    /**
     * 发送请求 post json
     *
     * @param url        请求地址        url
     * @param httpParams 请求参数 param
     * @return result
     */
    public static HoppScotchResponseBody sendPostJson(String url, HttpParams httpParams) {


        return sendPostJson(url, httpParams, null, StandardCharsets.UTF_8);
    }

    /**
     * 发送请求 post json
     *
     * @param url        请求地址        url
     * @param httpParams 请求参数 param
     * @param charset    响应字节码
     * @return result
     */
    public static HoppScotchResponseBody sendPostJson(String url, HttpParams httpParams, Charset charset) {

        return sendPostJson(url, httpParams, null, charset);
    }

//------------------------json-----param-----header

    /**
     * 发送请求 post json
     *
     * @param url        请求地址        url
     * @param httpParams 请求参数 param
     * @param httpHeader header
     * @return result
     */
    public static HoppScotchResponseBody sendPostJson(String url, HttpParams httpParams, HttpHeader httpHeader) {

        return sendPostJson(url, httpParams, httpHeader, StandardCharsets.UTF_8);
    }


    /**
     * 发送请求 post json
     *
     * @param url        请求地址        url
     * @param httpParams 请求参数 param
     * @param httpHeader header
     * @param charset    响应字节码
     * @return result
     */
    public static HoppScotchResponseBody sendPostJson(String url, HttpParams httpParams, HttpHeader httpHeader, Charset charset) {

        HoppScotchRequestBody body = new HoppScotchRequestBody();

        body.setUrl(url);

        body.setMethod(HoppScotchConfig.POST);

        if (CollectionUtil.isNotEmpty(httpParams)) {
            body.setData(JSON.toJSONString(httpParams));
        }

        if (CollectionUtil.isEmpty(httpHeader)) {
            httpHeader = new HttpHeader();
        }

        httpHeader.put("Content-Type", HoppScotchConfig.JSON);

        body.setHeaders(httpHeader);

        return getHttpResponseBody(body, charset);
    }

//-------------------------url

    /**
     * 发送请求 post form
     *
     * @param url 请求地址
     * @return result
     */
    public static HoppScotchResponseBody sendPost(String url) {
        return sendPostForm(url, null, null, StandardCharsets.UTF_8);
    }

    /**
     * 发送请求 post form
     *
     * @param url     请求地址
     * @param charset 响应字节码 charset
     * @param charset 响应字节码
     * @return result
     */
    public static HoppScotchResponseBody sendPost(String url, Charset charset) {
        return sendPostForm(url, null, null, charset);
    }

//-------------------------header

    /**
     * 发送请求 post form
     *
     * @param url        请求地址
     * @param httpHeader header
     * @return result
     */
    public static HoppScotchResponseBody sendPostForm(String url, HttpHeader httpHeader) {
        return sendPostForm(url, null, httpHeader);
    }

    /**
     * 发送请求 post form
     *
     * @param url        请求地址
     * @param httpHeader header
     * @param charset    响应字节码
     * @return result
     */
    public static HoppScotchResponseBody sendPostForm(String url, HttpHeader httpHeader, Charset charset) {
        return sendPostForm(url, null, httpHeader, charset);
    }

//------------------------param

    /**
     * 发送请求 post form
     *
     * @param url        请求地址
     * @param httpParams 请求参数 param
     * @return result
     */
    public static HoppScotchResponseBody sendPostForm(String url, HttpParams httpParams) {
        return sendPostForm(url, httpParams, StandardCharsets.UTF_8);
    }

    /**
     * 发送请求 post form
     *
     * @param url        请求地址
     * @param httpParams 请求参数 param
     * @param charset    响应字节码
     * @return result
     */
    public static HoppScotchResponseBody sendPostForm(String url, HttpParams httpParams, Charset charset) {
        return sendPostForm(url, httpParams, null, charset);
    }

//----------------------param-----header

    /**
     * 发送请求 post form
     *
     * @param url        请求地址
     * @param httpParams 请求参数 param
     * @param httpHeader header
     * @return result
     */
    public static HoppScotchResponseBody sendPostForm(String url, HttpParams httpParams, HttpHeader httpHeader) {

        return sendPostForm(url, httpParams, httpHeader, StandardCharsets.UTF_8);
    }

    /**
     * 发送请求 post form
     *
     * @param url        请求地址
     * @param httpParams 请求参数 param
     * @param httpHeader header
     * @param charset    响应字节码
     * @return result
     */
    public static HoppScotchResponseBody sendPostForm(String url, HttpParams httpParams, HttpHeader httpHeader, Charset charset) {

        HoppScotchRequestBody body = new HoppScotchRequestBody();

        body.setUrl(url);

        body.setMethod(HoppScotchConfig.POST);

        if (CollectionUtil.isEmpty(httpHeader)) {
            httpHeader = new HttpHeader();
        }

        httpHeader.put("Content-Type", HoppScotchConfig.FORM);

        body.setHeaders(httpHeader);

        if (CollectionUtil.isNotEmpty(httpParams)) {
            body.setData(HttpUtil.toParams(httpParams));
        }

        return getHttpResponseBody(body, charset);
    }

    /**
     * 代理请求
     *
     * @param param
     * @param charset 响应字节码
     * @return
     */
    private static HoppScotchResponseBody getHttpResponseBody(HoppScotchRequestBody param, Charset charset) {

        log.info("hoppscotch参数: {}", param);

        HttpHeader header = new HttpHeader() {{
            put("origin", "https://hoppscotch.io");
            put("referer", "https://hoppscotch.io/");
        }};


        String json = JSONObject.toJSONString(param, SerializerFeature.WriteMapNullValue);

        CloudFlareResponseBody httpEntity = CloudFlareUtils.sendPostJson(URL, JSON.parseObject(json, HttpParams.class), header);

        HoppScotchResponseBody hoppScotchResponseBody = JSONObject.parseObject(httpEntity.getEntity(), HoppScotchResponseBody.class);

        String data = hoppScotchResponseBody.getData();

        if (StrUtil.isNotBlank(data)) {
            log.info("Base64加密结果: {}", data);
            hoppScotchResponseBody.setData(Base64Decoder.decodeStr(data, charset));
        }

        return hoppScotchResponseBody;
    }


}
