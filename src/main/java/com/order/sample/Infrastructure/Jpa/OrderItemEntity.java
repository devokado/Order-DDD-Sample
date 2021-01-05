package com.order.sample.Infrastructure.Jpa;

import com.order.sample.Domain.SeedWork.Enums.Currency;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_item", uniqueConstraints = @UniqueConstraint(columnNames = {"order_id"}))
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="product_id", nullable = false)
    private UUID productId;
    @Column(name = "item_description", nullable = false)
    private String itemDescription;
    @Column(name = "item_price", nullable = false)
    private int itemPrice;
    @Column(name = "item_currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency itemPriceCurrency;
    @Column(name = "qty", nullable = false)
    private int quantity;
}
