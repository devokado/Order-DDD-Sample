package com.order.sample.Infrastructure.Jpa;

import com.order.sample.Domain.OrderItem;
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
    private Long itemPrice;
    @Column(name = "qty", nullable = false)
    private int quantity;

    public OrderItemEntity() {
    }

    public OrderItemEntity(UUID id, UUID productId, String itemDescription, Long itemPrice, int quantity) {
        this.id = id;
        this.productId = productId;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }
    public static OrderItemEntity  fromOrderItem(OrderItem orderItem){
        return new OrderItemEntity(UUID.fromString(orderItem.id().toUUID()),UUID.fromString(orderItem.productId().toUUID()),orderItem.itemDescription(),orderItem.itemPrice(),orderItem.quantity());
    }
    public static OrderItem toOrderItem(){
        return new OrderItem();
    }
}
