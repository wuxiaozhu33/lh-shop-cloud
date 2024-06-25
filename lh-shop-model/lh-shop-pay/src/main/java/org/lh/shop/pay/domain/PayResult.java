package org.lh.shop.pay.domain;

import lombok.Data;

/**
 * 支付结果
 *
 * @author wuYf
 * @date 2024/6/25 23:53
 */
@Data
public class PayResult {
    /**
     * 订单号
     */
    private String order;

    /**
     * 支付结果
     * 1：成功
     */
    private Integer code;
}
