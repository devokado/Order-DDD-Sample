package com.order.sample.Presentation.Rest;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.Port.OrderInterface;
import com.order.sample.Presentation.Rest.Request.OrderReq;
import com.order.sample.Presentation.Rest.Response.OrderResponse;
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

    @GetMapping
    public List<OrderResponse> findAll() {
       List<Order> orders =  orderInterface.findAll();
       List<OrderResponse> responseList = orders
               .stream()
               .map(order -> OrderResponse.from(order))
               .collect(Collectors.toList());
        return responseList;
    }
    @PostMapping()
    public OrderResponse createOrder(@RequestBody OrderReq orderReq){
        Order order = Order.toDomainModel(orderReq);
        Order orderRes = orderInterface.save(order);
        OrderResponse response = OrderResponse.from(orderRes);
        return response;
    }
}
