package com.order.sample.Domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.order.sample.Domain.SeedWork.Base.DomainObjectId;

public class ProductId extends DomainObjectId {
    @JsonCreator
    public ProductId(String uuid) {
        super(uuid);
    }
}
