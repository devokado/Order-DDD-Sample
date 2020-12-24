package com.order.sample.Presentation.Rest.Request;

import com.order.sample.Domain.RecipientAddress;
import com.order.sample.Domain.SeedWork.Enums.Currency;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderReq implements Serializable {
    @NotNull
    private Currency currency;
    @Valid
    @NotNull
    private RecipientAddressReq recipientAddress = new RecipientAddressReq();
    @Valid
    @NotEmpty
    private List<OrderItemReq> items = new ArrayList<>();

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public RecipientAddressReq getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(RecipientAddressReq recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public List<OrderItemReq> getItems() {
        return items;
    }

}
