package com.order.sample.Adapter.Infrastructure.Jpa;

import com.order.sample.Domain.Order;
import com.order.sample.Port.OrderOutputPort;

import java.util.List;
import java.util.Optional;

public class OrderAdapter implements OrderOutputPort {
    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order patch(Order order) {
        return null;
    }
}
