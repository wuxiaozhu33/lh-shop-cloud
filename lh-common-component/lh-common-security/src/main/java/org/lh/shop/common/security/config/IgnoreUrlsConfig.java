package org.lh.shop.common.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuYf
 * @date 2024/6/3 11:17
 */
@Configuration
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    private List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return this.urls;
    }
}
