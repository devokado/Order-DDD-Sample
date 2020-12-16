package com.order.sample.Infrastructure.Jpa;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrdersDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name ="ordered_on",nullable = false)
    private Timestamp orderedOn;
    @Column(name="order_currency",nullable = false)
    private String currency;



}
