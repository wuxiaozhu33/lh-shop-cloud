package org.lh.shop.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lh.shop.mybatis.inteceptor.MyBatisPrintInterceptor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuyf
 * @date 2024/6/3 14:21
 * @description mybatis自定义配置
 */
@Configuration
public class MyBatisPrintConfig {
    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct
    public void addMyBatisInterceptor() {
        MyBatisPrintInterceptor interceptor = new MyBatisPrintInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            // 添加自定义属性
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }

}

