package org.lh.shop.pay.service.impl;

import org.lh.shop.pay.domain.PayOrder;
import org.lh.shop.pay.domain.PayResult;
import org.lh.shop.pay.service.PaymentContextService;
import org.lh.shop.pay.granter.PaymentHandleService;
import org.lh.shop.pay.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * desc of 对象类
 *
 * @author wuYf
 * @date 2024/6/25 23:58
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    PaymentContextService paymentContextService;
    @Override
    public PayResult pay(PayOrder payOrder) {
        //获取订单中的渠道
        String channel = payOrder.getChannel();
        //根据渠道，具体选择所使用的支付处理类
        PaymentHandleService handleService = paymentContextService.getContext(channel);
        //调用该支付处理类的支付方法
        return handleService.pay(payOrder);
    }
}
