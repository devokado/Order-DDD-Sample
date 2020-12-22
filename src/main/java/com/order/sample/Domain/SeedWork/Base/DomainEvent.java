package com.order.sample.Domain.SeedWork.Base;

import org.springframework.lang.NonNull;

import java.time.Instant;

public interface DomainEvent extends DomainObject {
    /**
     * Returns the time and date on which the event occurred.
     */
    @NonNull
    Instant occurredOn();
}
