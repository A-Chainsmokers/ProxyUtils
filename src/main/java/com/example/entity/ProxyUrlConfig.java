package com.example.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Range
 * @version 1.0
 * @date 2021/8/23 15:52
 */
@ConfigurationProperties(prefix = "proxy")
public class ProxyUrlConfig {

    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
