package com.order.sample.Application;

import com.order.sample.Application.Exceptions.NotFoundException;
import com.order.sample.Domain.Order;
import com.order.sample.Domain.OrderId;
import com.order.sample.Domain.Port.OrderInterface;
import com.order.sample.Domain.SeedWork.Enums.OrderState;
import com.order.sample.Infrastructure.Jpa.OrderEntity;
import com.order.sample.Infrastructure.Jpa.Port.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashSet;
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
    public Order createOrder(Order order) {

        // add domain model validation here

        OrderEntity entity = OrderEntity.fromOrder(order);

        OrderEntity fromDb = orderRepository.save(entity);
        Order orderRes = fromDb.toOrder();
        return orderRes;
    }

    @Override
    public Order searchForOrderById(OrderId id) {
        Optional<OrderEntity> entity = orderRepository.findById(UUID.fromString(id.toUUID()));
        Order order;
        if ((entity.isPresent())) {
            order = entity.get().toOrder();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        return order;
    }

    @Override
    public List<Order> searchForAllOrders() {
        return orderRepository.findAll()
                .stream().map(OrderEntity::toOrder)
                .filter(order -> {
                    return order.getStatus().equals(true);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Order updateOrder(OrderId id, Order order) {

        return null;
    }

    @Override
    public void deleteOrder(OrderId id) {
        Optional<OrderEntity> entity = orderRepository.findById(UUID.fromString(id.toUUID()));
        if (entity.isEmpty()) {
            throw new NotFoundException("Unable to find resource", HttpStatus.NOT_FOUND);
        }
        entity.get().setStatus(false);
        orderRepository.save(entity.get());
    }


    @Override
    public Order patchOrder(Order order) {
        return null;
    }

    @Override
    public void startProcessing(OrderId id) {
        Optional<OrderEntity> dto = orderRepository.findById(UUID.fromString(id.toUUID()));
        Order order = new Order();
        if (dto.isEmpty()) {
            throw new NotFoundException("Unable to find resource", HttpStatus.NOT_FOUND);
        }

        /**
         * if ( order.isValid() && order.canStateChangeToCancel() ) {
         *                 order.startProcessing(Instant.now());
         *                 orderRepository.save(OrderEntity.fromOrder(order));
         *             }
         */
        List<String> errors = order.canChangeState(OrderState.valueOf(dto.get().getState()),OrderState.PROCESSING);
        if(!errors.isEmpty()){
            throw new IllegalStateException("Order has already been in state ");
        }
        order = dto.get().toOrder();
        order.startProcessing(Instant.now());
        orderRepository.save(OrderEntity.fromOrder(order));


        return;
    }

    @Override
    public void finishProcessing(OrderId id) {
        Optional<OrderEntity> dto = orderRepository.findById(UUID.fromString(id.toUUID()));
        Order order;
        if (dto.isEmpty()) {
            throw new NotFoundException("Unable to find resource", HttpStatus.NOT_FOUND);
        }

        order = dto.get().toOrder();
        order.finishProcessing(Instant.now());
        orderRepository.save(OrderEntity.fromOrder(order));


        return;
    }

    @Override
    public void cancelProcessing(OrderId id) {
        Optional<OrderEntity> dto = orderRepository.findById(UUID.fromString(id.toUUID()));
        Order order;
        if (dto.isEmpty()) {
            throw new NotFoundException("Unable to find resource", HttpStatus.NOT_FOUND);
        }

        order = dto.get().toOrder();
        order.cancel(Instant.now());
        orderRepository.save(OrderEntity.fromOrder(order));


        return;
    }
}
