package org.lh.shop.pay.granter;

import lombok.extern.slf4j.Slf4j;
import org.lh.shop.pay.annotation.Payment;
import org.lh.shop.pay.domain.PayOrder;
import org.lh.shop.pay.domain.PayResult;
import org.springframework.stereotype.Service;

/**
 * 支付宝支付处理
 *
 * @author wuYf
 * @date 2024/6/26 0:12
 */
@Slf4j
@Payment("alipay")
@Service
public class AlipayPaymentHandleServiceImpl implements PaymentHandleService {
    @Override
    public PayResult pay(PayOrder payOrder) {
        PayResult result = new PayResult();
        result.setOrder("alipay_202211261234567890");
        result.setCode(1);

        log.info("支付宝支付处理 订单信息:{} 支付结果:{}", payOrder, result);

        return result;
    }
}
