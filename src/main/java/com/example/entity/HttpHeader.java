package com.example.entity;

import java.util.HashMap;

/**
 * @author Range
 * @version 1.0
 * @date 2021/6/16 16:07
 */
public class HttpHeader extends HashMap<String, String> {

    /**
     * value为 null
     *
     * @param key
     */
    public void setValueNull(String key) {
        this.put(key, null);
    }

    /**
     * value为空字符串
     *
     * @param key
     */
    public void setValueBlank(String key) {
        this.put(key, "");
    }


}
