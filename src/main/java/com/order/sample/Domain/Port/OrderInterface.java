package com.order.sample.Domain.Port;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.OrderId;

import java.util.List;

public interface OrderInterface {
    Order createOrder(Order order);
    Order searchForOrderById(OrderId id);
    List<Order> searchForAllOrders();
    Order updateOrder(OrderId id, Order order);
    void deleteOrder(OrderId id);
    Order patchOrder(Order order);
    void startProcessing(OrderId id);
    void finishProcessing(OrderId id);
    void cancelProcessing(OrderId id);


}
