package org.lh.shop.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wuYf
 * @date 2024/6/5 11:44
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LhShopSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(LhShopSysApplication.class, args);
    }
}
