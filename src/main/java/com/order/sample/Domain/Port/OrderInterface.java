package com.order.sample.Domain.Port;

import com.order.sample.Domain.Order;

import java.util.List;

public interface OrderInterface {
    Order save(Order order);
    Order findById(String id);
    List<Order> findAll();
    Order patch(Order order);

}
