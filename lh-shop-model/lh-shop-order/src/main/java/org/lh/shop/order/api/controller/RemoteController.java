package org.lh.shop.order.api.controller;

import org.lh.shop.order.domain.Order;
import org.lh.shop.order.service.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuYf
 * @date 2024/6/4 11:21
 */
@RestController
@RequestMapping("/remote/sys")
public class RemoteController {
    @Resource
    private IOrderService orderService;
    @GetMapping("/getOrders")
    public List<Order> getUserInfo() {
        return orderService.getOrders();
    }
}
