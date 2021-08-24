package com.example.entity.hoppscotch;


import com.example.entity.HttpHeader;
import lombok.Data;

/**
 * @author Range
 * @version 1.0
 * @date 2021/3/17 16:40
 */
@Data
public class HoppScotchRequestBody {

    private String auth = null;

    private boolean credentials = true;

    private String data = null;

    private HttpHeader headers = new HttpHeader();

    private String method = HoppScotchConfig.GET;

    private String url;

    private boolean wantsBinary = true;

}
