package com.order.sample.Port;

import com.order.sample.Domain.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderOutputPort {
    Orders save(Orders orders);
    Optional<Orders> findById(Long id);
    List<Orders> findAll();
    Orders patch(Orders orders);

}
