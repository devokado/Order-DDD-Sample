package com.order.sample.Domain.Port;

import com.order.sample.Domain.OrderItem;

import java.util.List;

public interface OrderItemInterface {
    OrderItem save(OrderItem orderItem);
    OrderItem findById(String id);
    List<OrderItem> findAll();
    OrderItem patch(OrderItem orderItem);
}
