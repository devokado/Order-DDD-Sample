package com.order.sample.Domain.Port;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.OrderId;

import java.util.List;

public interface OrderInterface {
    Order save(Order order);
    Order findById(OrderId id);
    List<Order> findAll();
    Order update(OrderId id,Order order);
    void delete(OrderId id);
    Order patch(Order order);
    void startProcessing(OrderId id);
    void finishProcessing(OrderId id);
    void cancelProcessing(OrderId id);


}
