package com.order.sample.Presentation.Rest;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.Port.OrderInterface;
import com.order.sample.Presentation.Rest.Request.OrderReq;
import com.order.sample.Presentation.Rest.Response.OrderResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderInterface orderInterface;

    public OrderController(OrderInterface orderInterface) {
        this.orderInterface = orderInterface;
    }

    @GetMapping
    public List<OrderResponse> findAll() {
     //   List<Order> orders =  orderImpl.findAll();
     // OrderResponse orderResponse=  orderImpl.findAll();
        return null;
    }
    @PostMapping()
    public OrderResponse createOrder(@RequestBody OrderReq orderReq){
        Order order = Order.toDomainModel(orderReq);
        orderInterface.save(order);
        return null;
    }
}
