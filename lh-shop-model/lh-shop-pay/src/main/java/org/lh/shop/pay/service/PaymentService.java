package org.lh.shop.pay.service;

import org.lh.shop.pay.domain.PayOrder;
import org.lh.shop.pay.domain.PayResult;

/**
 * 支付处理服务统一入口
 *
 * @author wuYf
 * @date 2024/6/25 23:55
 */
public interface PaymentService {
    /**
     * 付款
     *
     * @param payOrder 付款订单
     * @return PayResult
     */
    PayResult pay(PayOrder payOrder);
}
