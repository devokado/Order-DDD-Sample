package com.order.sample.Infrastructure.Jpa;


import com.order.sample.Domain.Order;
import com.order.sample.Domain.RecipientAddress;
import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Enums.OrderState;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name ="ordered_on",nullable = false)
    private  Instant orderedOn;
    @Column(name="order_currency",nullable = false)
    private  String currency;
    @Column(name="status",nullable = false)
    private Boolean status;
    @Column(name = "order_state", nullable = false)
    private  String state;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_state_changes")
    private Set<OrderStateChangeEntity> stateChangeHistory;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "shipping_name", nullable = false)),
            @AttributeOverride(name = "addressLine1", column = @Column(name = "line1", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "country", nullable = false))
    })
    private RecipientAddressEntity shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Set<OrderItemEntity> items;

    @SuppressWarnings("unused")
    public OrderEntity() {
    }

    public OrderEntity(UUID id, Instant orderedOn, String currency, String state, RecipientAddressEntity shippingAddress, Set<OrderItemEntity> items,Boolean status) {
        this.id = id;
        this.orderedOn = orderedOn;
        this.currency = currency;
        this.state = state;
        this.shippingAddress =shippingAddress;
        this.items = items;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public static OrderEntity fromOrder(Order order){
      return  new OrderEntity(UUID.fromString(order.id().toUUID()),order.getOrderedOn(),order.getCurrency().toString(),order.getState().toString(),new RecipientAddressEntity(order.getShippingAddress().name(),order.getShippingAddress().getAddressLine1(),order.getShippingAddress().getCity(),order.getShippingAddress().getCountry()),null,order.getStatus());
    }
    public Order toOrder(){
        return new Order(id, orderedOn, Currency.valueOf(currency), OrderState.valueOf(state) ,new RecipientAddress(shippingAddress.getName(),shippingAddress.getAddressLine1(),shippingAddress.getCity(),shippingAddress.getCountry()),status);
    }


}
