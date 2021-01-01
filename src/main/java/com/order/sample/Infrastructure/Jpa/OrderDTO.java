package com.order.sample.Infrastructure.Jpa;


import com.order.sample.Domain.Order;
import com.order.sample.Domain.RecipientAddress;
import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Geo.CityName;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name ="ordered_on",nullable = false)
    private static Instant orderedOn;
    @Column(name="order_currency",nullable = false)
    private static String currency;
    @Column(name = "order_state", nullable = false)
    private String state;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "shipping_name", nullable = false)),
            @AttributeOverride(name = "addressLine1", column = @Column(name = "line1", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "country", nullable = false))
    })
    private static RecipientAddressDTO shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Set<OrderItemDTO> items;

    @SuppressWarnings("unused")
    public OrderDTO() {
    }

    public OrderDTO(String id, Instant orderedOn, String currency, String state, RecipientAddressDTO shippingAddress, Set<OrderItemDTO> items) {
        this.id = id;
        this.orderedOn = orderedOn;
        this.currency = currency;
        this.state = state;
        this.shippingAddress = shippingAddress;
        this.items = items;
    }

    public static OrderDTO fromOrder(Order order){
      return  new OrderDTO(order.id().toString(),order.orderedOn(),order.currency().toString(),order.state().toString(),new RecipientAddressDTO(order.shippingAddress().name(),order.shippingAddress().getAddressLine1(),order.shippingAddress().getCity(),order.shippingAddress().getCountry()),null);
    }
    public static Order toOrder(){
        return new Order(orderedOn,Currency.valueOf(currency),new RecipientAddress(shippingAddress.getName(),shippingAddress.getAddressLine1(),shippingAddress.getCity(),shippingAddress.getCountry()));
    }

}
