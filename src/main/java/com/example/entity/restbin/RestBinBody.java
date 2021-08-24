package com.example.entity.restbin;

import cn.hutool.core.util.IdUtil;
import lombok.Data;

/**
 * @author Range
 * @version 1.0
 * @date 2021/8/24 11:12
 */
@Data
public class RestBinBody {

    private final String deviceId = IdUtil.fastUUID();

    private final Long sessionId = System.currentTimeMillis();

    private final String id = "0";

    private final String name = "";

    private final String errors = "";

    private String json;

}
