package com.example.proxyutils;

import com.example.entity.HttpRequestEntity;
import com.example.utils.CloudFlareUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProxyUtilsApplicationTests {

    @Test
    void contextLoads() {

        HttpRequestEntity httpRequestEntity = CloudFlareUtils.sendGet("https://reqbin.com/");

        System.out.println(httpRequestEntity.getEntity());


    }

}
