package com.order.sample.Application;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.Port.OrderInterface;
import com.order.sample.Infrastructure.Jpa.OrderDTO;
import com.order.sample.Infrastructure.Jpa.OrderRepository;
import com.order.sample.Presentation.Rest.Request.OrderReq;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderImpl implements OrderInterface {
    private final OrderRepository orderRepository;

    public OrderImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
       // OrderDTO dto = order.

        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return null;
      //  return orderRepository.findAll()
           //     .stream()
          //      .map(OrderDTO::as);
    }


    @Override
    public Order patch(Order order) {
        return null;
    }
}