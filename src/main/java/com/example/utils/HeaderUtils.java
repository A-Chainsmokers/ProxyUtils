package com.example.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Map;

/**
 * @author Range
 * @version 1.0
 * @date 2021/8/24 15:32
 */
public class HeaderUtils {

    /**
     * @param headers
     * @return
     */
    public static String getCookie(Map headers) {
        if (CollectionUtil.isNotEmpty(headers)) {
            String cookie = headers.get("set-cookie").toString();
            if (StrUtil.isNotBlank(cookie)) {
                return cookie;
            }
            return headers.get("Set-Cookie").toString();
        }
        return "";
    }

}
