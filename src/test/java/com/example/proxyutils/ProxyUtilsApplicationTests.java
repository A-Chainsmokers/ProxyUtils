package com.example.proxyutils;

import com.example.entity.restbin.RestBinRequestBody;
import com.example.utils.RestBinUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProxyUtilsApplicationTests {

    @Test
    void contextLoads() {

        RestBinRequestBody restBinRequestBody = RestBinUtils.sendGet("https://lncn.org/");

        System.out.println(restBinRequestBody.getContent());

    }

}
