package org.lh.shop.system.api;


import org.lh.shop.system.api.domain.OrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 用户服务
 *
 * @author Lion Li
 */
@FeignClient(name = "lh-shop-order")
public interface RemoteOrderService {

    /**
     * 获取订单信息
     * @return 结果
     */
    @GetMapping("/remote/order/getOrders")
    List<OrderVo> getOrders();


}
