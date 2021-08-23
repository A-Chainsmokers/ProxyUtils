package com.example.entity;

import lombok.Data;


/**
 * @author Range
 * @version 1.0
 * @date 2020/12/10 15:51
 */
@Data
public class CloudFlareParam {

    public final static String POST = "POST";

    public final static String GET = "GET";

    public final static String FROM = "application/x-www-form-urlencoded; charset=UTF-8";

    public final static String JSON = "application/json; charset=UTF-8";

    public final static String COOKIE = "cookie";


    /**
     * 请求方式
     */
    private String method;

    /**
     * url
     */
    private String url;

    /**
     * 请求头
     */
    private HttpHeader headers;

    /**
     * 参数
     */
    private HttpParams body;

    /**
     * 参数方式 true表单, false json
     */
    private boolean isForm;

}
