package com.order.sample.Presentation.Rest.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItemDTO {
    @NotNull
    private String productId;
    @NotNull
    private String productName;
    @Min(1)
    private int quantity = 1;
    private String itemDescription;
    private Long price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
