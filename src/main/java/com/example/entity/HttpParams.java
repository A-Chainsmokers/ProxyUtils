package com.example.entity;

import java.util.HashMap;

/**
 * @author Range
 * @version 1.0
 * @date 2021/6/16 16:06
 */
public class HttpParams<T> extends HashMap<String, T> {

    /**
     * value为 null
     *
     * @param key
     */
    public void setValueNull(String key) {
        this.put(key, null);
    }

}
