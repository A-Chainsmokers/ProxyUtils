package com.example.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Range
 * @version 1.0
 * @date 2021/8/23 15:52
 */
@Data
@ToString
@ConfigurationProperties(prefix = "proxy")
public class ProxyUrlConfig {

    private List<String> urls;
}
