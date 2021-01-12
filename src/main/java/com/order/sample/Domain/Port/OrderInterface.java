package com.order.sample.Domain.Port;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.OrderId;

import java.util.List;

public interface OrderInterface {
    Order save(Order order);
    Order findById(OrderId id);
    List<Order> findAll();
    Order patch(Order order);
    Order startProcessing(OrderId id);
    Order finishProcessing(OrderId id);

}
