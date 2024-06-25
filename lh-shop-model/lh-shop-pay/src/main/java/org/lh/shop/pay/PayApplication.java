package org.lh.shop.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * desc of 对象类
 *
 * @author wuYf
 * @date 2024/6/26 0:14
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
