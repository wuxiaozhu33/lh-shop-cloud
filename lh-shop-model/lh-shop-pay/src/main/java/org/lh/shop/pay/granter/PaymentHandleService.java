package org.lh.shop.pay.granter;

import org.lh.shop.pay.domain.PayOrder;
import org.lh.shop.pay.domain.PayResult;

/**
 * 支付处理服务
 *
 * @author wuYf
 * @date 2024/6/25 23:56
 */
public interface PaymentHandleService {
    /**
     * 付款
     *
     * @param payOrder 付款订单
     * @return PayResult
     */
    PayResult pay(PayOrder payOrder);
}
