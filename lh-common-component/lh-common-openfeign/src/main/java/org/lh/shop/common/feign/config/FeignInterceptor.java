package org.lh.shop.common.feign.config;

import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.stp.StpUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author wuYf
 * @date 2024/6/11 11:02
 */
@Component
public class FeignInterceptor implements RequestInterceptor {

    /**
     * 为 Feign 的 RCP调用 添加请求头Same-Token 和token
     *
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // SaSameToken 主要用于实现网关统一请求。即请求不能绕过网关去访问某个具体的服务
        requestTemplate.header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken());
        try {
            String preFix = Optional.ofNullable(StpUtil.getStpLogic().getConfigOrGlobal().getTokenPrefix()).orElse("Bearer");
            // 当存在web上下文的时候，就正常在请求里面塞进去登录的时候申请到的token
            requestTemplate.header(StpUtil.getTokenName(), preFix+" " + StpUtil.getTokenValue());
        } catch (Exception e) {
            //在无web上下文的情况下，上面try里面的获取用户token的方法StpUtil.getTokenValue()会抛出错误，
            //这里将抛出异常视为无web上下文的情况。无web上下文的时候，token的值赋值为SaSameToken的值，
            //这个情况下，token的值可以通过自己的想法去赋值，不一定与我的想法一致。
            requestTemplate.header(StpUtil.getTokenName(), SaSameUtil.getToken());
        }
    }
}