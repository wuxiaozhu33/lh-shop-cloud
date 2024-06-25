package org.lh.shop.pay.service.impl;

import cn.hutool.core.util.StrUtil;
import org.lh.shop.pay.annotation.Payment;
import org.lh.shop.pay.service.PaymentContextService;
import org.lh.shop.pay.granter.PaymentHandleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * desc of 对象类
 *
 * @author wuYf
 * @date 2024/6/25 23:59
 */

@Service
public class PaymentContextServiceImpl implements PaymentContextService {
    /**
     * 自动注入所有具体策略实现类
     */
    @Resource
    List<PaymentHandleService> handleServiceList;

    /**
     * 额外定义一个不支持的渠道支付方式实现类
     */
    @Resource(name = "NonsupportPaymentHandleServiceImpl")
    PaymentHandleService nonsupportService;

    @Override
    public PaymentHandleService getContext(String channel) {

        if (StrUtil.isEmpty(channel)) {
            return nonsupportService;
        }

        //策略实现类上都会打上 Payment 注解，并定义支付方式的值，用于适配订单的渠道值
        return handleServiceList.stream()
                .filter(f -> StrUtil.equalsIgnoreCase(channel, f.getClass().getAnnotation(Payment.class).value()))
                .findFirst()
                .orElse(nonsupportService);
    }
}
