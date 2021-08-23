package com.example.entity;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import java.util.HashMap;

/**
 * @author Range
 * @version 1.0
 * @date 2021/3/17 17:07
 */
public class HttpResponseBody {

    public static final int OK = 200;

    public HashMap<String, String> headers;

    public String data;

    public int status;

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCookie() {
        if (CollectionUtil.isNotEmpty(headers)) {
            String cookie = headers.get("set-cookie");
            if (StrUtil.isNotBlank(cookie)) {
                return cookie;
            }
            return headers.get("Set-Cookie");
        }
        return "";
    }

    @Override
    public String toString() {
        return "{" +
                "    headers:" + headers +
                ",     data:'" + data + '\'' +
                ",     status:" + status +
                '}';
    }
}
