package com.order.sample.Domain.SeedWork.Base;

import org.springframework.lang.Nullable;

public interface ConcurrencySafeDomainObject extends DomainObject {
    /**
     * Returns the optimistic locking version of this domain object.
     *
     * @return the version or {@code null} if no version has been assigned yet.
     */
    @Nullable
    Long version();
}
