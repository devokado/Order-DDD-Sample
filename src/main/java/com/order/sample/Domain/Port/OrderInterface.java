package com.order.sample.Domain.Port;

import com.order.sample.Domain.Order;
import com.order.sample.Presentation.Rest.Request.OrderReq;

import java.util.List;
import java.util.Optional;

public interface OrderInterface {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    Order patch(Order order);

}