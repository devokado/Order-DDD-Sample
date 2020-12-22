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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Set<OrderItemDTO> items;

//    Order asResponse(){
//       return new Order(orderedOn,currency,new RecipientAddress("",new CityName("tehran"),));
//    }



}
