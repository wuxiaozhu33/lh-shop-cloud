package org.lh.shop.pay.granter;

import org.lh.shop.pay.annotation.Payment;
import org.lh.shop.pay.domain.PayOrder;
import org.lh.shop.pay.domain.PayResult;
import org.springframework.stereotype.Service;

/**
 * 不支持的业务处理实现
 *
 * @author wuYf
 * @date 2024/6/26 0:08
 */
@Payment("nonsupport")
@Service("NonsupportPaymentHandleServiceImpl")
public class NonsupportPaymentHandleServiceImpl implements PaymentHandleService {
    @Override
    public PayResult pay(PayOrder payOrder) {
        PayResult result = new PayResult();
        result.setCode(-1);
        return result;
    }
}
