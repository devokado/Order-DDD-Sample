package com.order.sample.Presentation.Rest.Request;

import com.order.sample.Domain.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItemDTO {
    @NotNull
    private Product product;
    @Min(1)
    private int quantity = 1;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
