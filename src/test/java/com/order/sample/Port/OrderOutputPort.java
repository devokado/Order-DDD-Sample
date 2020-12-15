package com.order.sample.Port;

import com.order.sample.Domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderOutputPort {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    Order patch(Order order);

}
