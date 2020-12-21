package com.order.sample.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.order.sample.Domain.SeedWork.Enums.Currency;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.UUID;

public class OrderItem {
    private UUID id;
    private UUID productId;
    private UUID orderId;
    private String itemDescription;
    private Long itemPrice;
    private Currency itemPriceCurrency;
    private Integer quantity;

    public OrderItem() {
    }

    OrderItem(@NonNull UUID productId, @NonNull String itemDescription, @NonNull Long itemPrice) {
        setProductId(productId);
        setItemDescription(itemDescription);
        setItemPrice(itemPrice);
    }
    @NonNull
    @JsonProperty("productId")
    public UUID productId() {
        return productId;
    }

    private void setProductId(@NonNull UUID productId) {
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

}