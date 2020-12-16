package com.order.sample.Infrastructure.Jpa;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_item")
public class OrderItemDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
