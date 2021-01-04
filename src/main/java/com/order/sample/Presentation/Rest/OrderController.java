package com.order.sample.Presentation.Rest;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.Port.OrderInterface;
import com.order.sample.Presentation.Rest.Request.OrderReq;
import com.order.sample.Presentation.Rest.Response.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderInterface orderInterface;

    public OrderController(OrderInterface orderInterface) {
        this.orderInterface = orderInterface;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderReq orderReq){
        Order order = orderInterface.save(Order.toDomainModel(orderReq));
        OrderResponse response = OrderResponse.from(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
       List<Order> orders =  orderInterface.findAll();
       List<OrderResponse> responseList = orders
               .stream()
               .map(order -> OrderResponse.from(order))
               .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        Order order = orderInterface.findById(id);
        OrderResponse response = OrderResponse.from(order);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
