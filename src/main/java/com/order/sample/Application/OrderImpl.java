package com.order.sample.Application;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.Port.OrderInterface;
import com.order.sample.Infrastructure.Jpa.OrderDTO;
import com.order.sample.Infrastructure.Jpa.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderImpl implements OrderInterface {
    private final OrderRepository orderRepository;

    public OrderImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }



    @Override
    public Order save(Order order) {
       OrderDTO dto = OrderDTO.fromOrder(order);
       OrderDTO fromDb = orderRepository.save(dto);
       Order orderRes = fromDb.toOrder();
        return orderRes;
    }

    @Override
    public Order findById(String id) {
        Optional<OrderDTO> dto = orderRepository.findById(UUID.fromString(id));
        Order order;
        if ((dto.isPresent())) {
            order = dto.get().toOrder();
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unable to find resource");
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll()
                .stream().map(OrderDTO::toOrder)
                .collect(Collectors.toList());
    }


    @Override
    public Order patch(Order order) {
        return null;
    }
}
