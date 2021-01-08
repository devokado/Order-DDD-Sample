package com.order.sample.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.order.sample.Domain.SeedWork.Base.AbstractAggregateRoot;
import com.order.sample.Domain.SeedWork.Base.DomainObjectId;
import com.order.sample.Presentation.Rest.Request.OrderItemDTO;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.UUID;

public class OrderItem extends AbstractAggregateRoot<OrderItemId> {

    private ProductId productId;
    private String itemDescription;
    private Long itemPrice;
    private Integer quantity;

    public OrderItem() {
    }

   public OrderItem(@NonNull ProductId productId, @NonNull String itemDescription, @NonNull Long itemPrice,Integer quantity) {
        super(DomainObjectId.randomId(OrderItemId.class));
        setProductId(productId);
        setItemDescription(itemDescription);
        setItemPrice(itemPrice);
        setQuantity(quantity);
    }
    public OrderItem(UUID orderItemId, @NonNull ProductId productId, @NonNull String itemDescription, @NonNull Long itemPrice, Integer quantity){
        super(new OrderItemId(orderItemId.toString()));
        setProductId(productId);
        setItemDescription(itemDescription);
        setItemPrice(itemPrice);
        setQuantity(quantity);
    }

    @NonNull
    @JsonProperty("productId")
    public ProductId productId() {
        return productId;
    }

    private void setProductId(@NonNull ProductId productId) {
        this.productId = Objects.requireNonNull(productId, "productId must not be null");
    }
    @NonNull
    @JsonProperty("description")
    public String itemDescription() {
        return itemDescription;
    }

    private void setItemDescription(@NonNull String itemDescription) {
        this.itemDescription = Objects.requireNonNull(itemDescription, "itemDescription must not be null");
    }
    @NonNull
    @JsonProperty("price")
    public Long itemPrice() {
        return itemPrice;
    }

    private void setItemPrice(@NonNull Long itemPrice) {
        Objects.requireNonNull(itemPrice, "itemPrice must not be null");
        this.itemPrice = itemPrice;
    }
    @NonNull
    @JsonProperty("qty")
    public int quantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public static OrderItem toDomainModel(OrderItemDTO req){
        return new OrderItem(new ProductId(req.getProductId()),req.getItemDescription(),req.getPrice(),req.getQuantity());
    }

}
