package org.lh.shop.order.service.impl;

import org.lh.shop.order.domain.Order;
import org.lh.shop.order.mapper.OrderMapper;
import org.lh.shop.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author wuYf
 * @since 2024-06-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    public List<Order> getOrders() {
        return this.list();
    }
}
