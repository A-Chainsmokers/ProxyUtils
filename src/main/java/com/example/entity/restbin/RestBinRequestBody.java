package com.example.entity.restbin;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Range
 * @version 1.0
 * @date 2021/8/24 13:51
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestBinRequestBody {

    private String Content;

    private Integer ContentLength;

    private String ContentType;

    private Integer Elapsed;

    private String Headers;

    private Integer RedirectsCount;

    private Integer RedirectsTime;

    private String StatusCode;

    private String StatusDescription;

    private boolean Success;

    private String Version;

    private JSONObject HeadersList;


}
