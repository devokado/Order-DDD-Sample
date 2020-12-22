package com.order.sample.Presentation.Rest;

import com.order.sample.Application.OrderImpl;
import com.order.sample.Domain.Order;
import com.order.sample.Presentation.Rest.Response.OrderResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderImpl orderImpl;

    public OrderController(OrderImpl orderImpl) {
        this.orderImpl = orderImpl;
    }

    @GetMapping
    public List<OrderResponse> findAll() {
        List<Order> orders =  orderImpl.findAll();
     // OrderResponse orderResponse=  orderImpl.findAll();
        return null;
    }
}
