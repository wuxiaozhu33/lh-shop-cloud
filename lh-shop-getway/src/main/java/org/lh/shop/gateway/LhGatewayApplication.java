package org.lh.shop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * EnableWebFlux开启SpringMVC支持，如无此注解，重写WebMvcConfigurerAdapter类的方法无效
 * @author wuYf
 * @date 2024/5/31 10:04
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableWebFlux
public class LhGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LhGatewayApplication.class, args);
    }
}
