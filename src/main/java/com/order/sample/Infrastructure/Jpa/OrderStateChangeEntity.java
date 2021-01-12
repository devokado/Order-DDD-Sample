package com.order.sample.Infrastructure.Jpa;

import com.order.sample.Domain.OrderStateChange;
import com.order.sample.Domain.SeedWork.Enums.OrderState;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;

@Embeddable
public class OrderStateChangeEntity {
    @Column(name = "changed_on", nullable = false)
    private Instant changedOn;
    @Column(name = "state",nullable = false)
    private String state;

    public OrderStateChangeEntity() {
    }

    public OrderStateChangeEntity(Instant changedOn, String state) {
        this.changedOn = changedOn;
        this.state = state;
    }
    public OrderStateChange toOrderStateChange(){
        return new OrderStateChange(this.changedOn, OrderState.valueOf(state));
    }
    public OrderStateChangeEntity fromOrderStateChange(OrderStateChange orderStateChange){
        return new OrderStateChangeEntity(orderStateChange.changedOn(),orderStateChange.state().toString());
    }
}
