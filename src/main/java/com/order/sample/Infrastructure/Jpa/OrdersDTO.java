package com.order.sample.Infrastructure.Jpa;

import com.order.sample.Domain.OrderId;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrdersDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

}
