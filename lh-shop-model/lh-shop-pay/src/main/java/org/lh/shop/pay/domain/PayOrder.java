package org.lh.shop.pay.domain;

import lombok.Data;

/**
 * 订单支付
 *
 * @author wuYf
 * @date 2024/6/25 23:52
 */
@Data
public class PayOrder {
    /**
     * 支付金额，单位元
     */
    private String mete;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 支付渠道
     * ALIPAY：支付宝
     * WECHAT：微信
     * CARD：银行卡
     */
    private String channel;
}
