package com.example.entity.restbin;

import lombok.Data;

import java.util.HashMap;

/**
 * @author Range
 * @version 1.0
 * @date 2021/8/24 9:59
 */
@Data
public class RestBinJson {


    private final HashMap<String, Object> auth = new HashMap<String, Object>() {{
        put("auth", "noAuth");
        put("bearerToken", "");
        put("basicUsername", "");
        put("basicPassword", "");
        put("customHeader", "");
        put("encrypted", "");
    }};


    private String method;

    private String url;

    private String apiNode = RestBinConfig.NODE_US;

    private String contentType = "";

    private String content = "";

    private String headers = "";

    private String errors = "";

    private String curlCmd = "";

    private boolean compare = false;

    private String idnUrl;


}
