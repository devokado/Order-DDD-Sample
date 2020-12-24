package com.order.sample.Infrastructure.Jpa;

import com.order.sample.Domain.Order;
import com.order.sample.Domain.OrderItem;
import com.order.sample.Domain.RecipientAddress;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name ="ordered_on",nullable = false)
    private Timestamp orderedOn;
    @Column(name="order_currency",nullable = false)
    private String currency;
    @Column(name = "order_state", nullable = false)
    private String state;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "shipping_name", nullable = false)),
            @AttributeOverride(name = "addressLine1", column = @Column(name = "line1", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "country", nullable = false))
    })
    private RecipientAddressDTO shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Set<OrderItemDTO> items;





}
