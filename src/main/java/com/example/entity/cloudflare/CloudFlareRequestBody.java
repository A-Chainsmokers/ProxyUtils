package com.example.entity.cloudflare;

import com.example.entity.HttpHeader;
import com.example.entity.HttpParams;
import lombok.Data;


/**
 * @author Range
 * @version 1.0
 * @date 2020/12/10 15:51
 */
@Data
public class CloudFlareRequestBody {


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
