package org.lh.shop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author wuYf
 * @date 2024/5/31 10:04
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LhGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LhGatewayApplication.class, args);
    }
}
