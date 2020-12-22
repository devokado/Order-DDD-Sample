package com.order.sample.Presentation.Rest.Response;

import com.order.sample.Domain.Order;

public class OrderResponse {
    private String orderedOn;
    private String currency;
    private String state;

    public OrderResponse(String orderedOn, String currency, String state) {
        this.orderedOn = orderedOn;
        this.currency = currency;
        this.state = state;
    }

    public String getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(String orderedOn) {
        this.orderedOn = orderedOn;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public static OrderResponse from(Order order){
        return new OrderResponse(order.orderedOn().toString(),order.currency().name(),order.state().name());
    }
}
