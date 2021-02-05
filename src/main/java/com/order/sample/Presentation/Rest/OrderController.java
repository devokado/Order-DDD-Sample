package com.order.sample.Presentation.Rest;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.OrderId;
import com.order.sample.Domain.Port.OrderInterface;
import com.order.sample.Domain.SeedWork.Enums.OrderState;
import com.order.sample.Domain.Validators.OrderValidator;
import com.order.sample.Presentation.Rest.Request.OrderDTO;
import com.order.sample.Presentation.Rest.Response.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderInterface orderInterface;
    private final OrderValidator orderValidator;

    public OrderController(OrderInterface orderInterface, OrderValidator orderValidator) {
        this.orderInterface = orderInterface;
        this.orderValidator = orderValidator;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO, BindingResult result){
       // orderValidator.validate(orderDTO.toDomainModel(),result);

       // Order order = orderInterface.createOrder(orderDTO.toDomainModel());
        Order order = orderDTO.toDomainModel();

        order =orderInterface.createOrder(order);
        OrderResponse response = OrderResponse.from(orderInterface.createOrder(order));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
       List<Order> orders =  orderInterface.searchForAllOrders();
       List<OrderResponse> responseList = orders
               .stream()
               .map(order -> OrderResponse.from(order))
               .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        Order order = orderInterface.searchForOrderById(new OrderId(id));
        OrderResponse response = OrderResponse.from(order);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody OrderDTO orderDTO){
        Order order = orderInterface.updateOrder(new OrderId(id),orderDTO.toDomainModel());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("{id}/startProcessing")
    public ResponseEntity<?> startProcessing(@PathVariable String id){
        orderInterface.startProcessing(new OrderId(id));

        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @PostMapping("{id}/finishProcessing")
    public ResponseEntity<?> finishProcessing(@PathVariable String id){
        orderInterface.finishProcessing(new OrderId(id));

        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @PostMapping("{id}/cancelProcessing")
    public ResponseEntity<?> cancelProcessing(@PathVariable String id){
        orderInterface.cancelProcessing(new OrderId(id));

        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        orderInterface.deleteOrder(new OrderId(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
