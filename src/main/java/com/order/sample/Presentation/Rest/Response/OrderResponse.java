package com.order.sample.Presentation.Rest.Response;

import com.order.sample.Domain.Order;

public class OrderResponse {
    private String id;
    private String orderedOn;
    private String currency;
    private String state;

    public OrderResponse(String id,String orderedOn, String currency, String state) {
        this.id = id;
        this.orderedOn = orderedOn;
        this.currency = currency;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return new OrderResponse(order.id().toUUID(),order.getOrderedOn().toString(),order.getCurrency().name(),order.getState().name());
    }
}
