package org.lh.shop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lh.shop.order.domain.Order;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author wuYf
 * @since 2024-06-06
 */
public interface IOrderService extends IService<Order> {

    List<Order> getOrders();
}
