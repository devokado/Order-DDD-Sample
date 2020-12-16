package com.order.sample.Domain;

import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Enums.OrderState;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

public class Orders {
    private UUID id;
    private Timestamp orderedOn;
    private Currency currency;
    private OrderState orderState;
    private Set<OrderItem> items;

}
