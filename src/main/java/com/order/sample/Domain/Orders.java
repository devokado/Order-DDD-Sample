package com.order.sample.Domain;

import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Enums.OrderState;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

public class Orders {
    private UUID id;
    private Timestamp orderedOn;
    private Currency currency;
    private OrderState orderState;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "shipping_name", nullable = false)),
            @AttributeOverride(name = "addressLine1", column = @Column(name = "shipping_addr1", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "shipping_country", nullable = false))
    })
    private RecipientAddress shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Set<OrderItem> items;

    public Orders() {
    }

    public Orders(UUID id, Timestamp orderedOn, Currency currency, OrderState orderState, Set<OrderItem> items) {
        this.id = id;
        this.orderedOn = orderedOn;
        this.currency = currency;
        this.orderState = orderState;
        this.items = items;
    }
}
