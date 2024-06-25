package org.lh.shop.pay.service;

import org.lh.shop.pay.granter.PaymentHandleService;

/**
 * 支付处理上下文
 *
 * @author wuYf
 * @date 2024/6/25 23:57
 */
public interface PaymentContextService {
    /**
     * 获取处理上下文
     *
     * @param channel
     * @return
     */
    PaymentHandleService getContext(String channel);
}
