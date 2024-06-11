package org.lh.shop.order.controller;


import org.lh.shop.common.core.domin.R;
import org.lh.shop.order.domain.Order;
import org.lh.shop.order.service.IOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author wuYf
 * @since 2024-06-06
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private IOrderService orderService;

    @RequestMapping("/getOrders")
    public R<List<Order>> getOrders() {
        return R.ok(orderService.getOrders());
    }
}

