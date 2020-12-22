package com.order.sample.Domain.SeedWork.Base;

public interface DeletableDomainObject extends DomainObject {
    /**
     * Returns whether this domain object has been marked as deleted.
     */
    boolean isDeleted();

    /**
     * Marks this domain object as deleted.
     */
    void delete();

}
