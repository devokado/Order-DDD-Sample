package com.order.sample.Domain;

import com.order.sample.Domain.SeedWork.Enums.Currency;

import java.util.UUID;

public class OrderItem {
    private UUID id;
    private UUID productId;
    private UUID orderId;
    private String itemDescription;
    private Integer itemPrice;
    private Currency itemPriceCurrency;
    private Integer quantity;
}
