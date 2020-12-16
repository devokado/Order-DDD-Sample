package com.order.sample.Infrastructure.Jpa;

import com.order.sample.Domain.Orders;
import com.order.sample.Port.OrderOutputPort;

import java.util.List;
import java.util.Optional;

public class OrderAdapter implements OrderOutputPort {
    @Override
    public Orders save(Orders orders) {
        return null;
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }

    @Override
    public Orders patch(Orders orders) {
        return null;
    }
}
