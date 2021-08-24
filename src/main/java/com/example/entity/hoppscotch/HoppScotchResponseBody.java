package com.example.entity.hoppscotch;

import lombok.Data;

import java.util.HashMap;

/**
 * @author Range
 * @version 1.0
 * @date 2021/3/17 17:07
 */
@Data
public class HoppScotchResponseBody {

    public static final int OK = 200;

    public HashMap<String, String> headers;

    public String data;

    public Integer status;

    @Override
    public String toString() {
        return "{" +
                "    headers:" + headers +
                ",     data:'" + data + '\'' +
                ",     status:" + status +
                '}';
    }
}
