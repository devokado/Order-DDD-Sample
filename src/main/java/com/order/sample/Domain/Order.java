package com.order.sample.Domain;

import com.order.sample.Domain.SeedWork.Base.AbstractAggregateRoot;
import com.order.sample.Domain.SeedWork.Base.ConcurrencySafeDomainObject;
import com.order.sample.Domain.SeedWork.Base.DomainObjectId;
import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Enums.OrderState;
import javax.persistence.*;
import java.time.Instant;
import java.util.*;


public class Order extends AbstractAggregateRoot<OrderId> implements ConcurrencySafeDomainObject {

    @Version
    private Long version;
    private Instant orderedOn;
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    private Set<OrderStateChange> stateChangeHistory;
    private RecipientAddress shippingAddress;
    private Boolean status;
    private Set<OrderItem> items;

    public Order() {
    }

    public Order(Instant orderedOn, Currency currency, RecipientAddress recipientAddress) {
        super(DomainObjectId.randomId(OrderId.class));
        this.stateChangeHistory = new HashSet<>();
        this.items = new HashSet<>();
        setOrderedOn(orderedOn);
        setCurrency(currency);
        setStatus(true);
        setState(OrderState.RECEIVED, orderedOn);
        setShippingAddress(recipientAddress);
    }

    public Order(UUID orderId, Instant orderedOn, Currency currency, OrderState state, RecipientAddress recipientAddress, Boolean status) {
        super(new OrderId(orderId.toString()));
        this.stateChangeHistory = new HashSet<>();
        setOrderedOn(orderedOn);
        setCurrency(currency);
        setStatus(status);
        setState(state, orderedOn);
        setShippingAddress(recipientAddress);
    }
    public List<String> canChangeState(OrderState state,OrderState newState){
        List<String> errors = new ArrayList<>();
        if(state.equals(newState)){
            errors.add("state is already in this state");
        }

        return errors;
    }


    public Currency getCurrency() {
        return currency;
    }

    private void setCurrency(Currency currency) {
        this.currency = Objects.requireNonNull(currency, "currency must not be null");
    }

    public Instant getOrderedOn() {
        return orderedOn;
    }

    private void setOrderedOn(Instant orderedOn) {
        this.orderedOn = Objects.requireNonNull(orderedOn, "orderedOn must not be null");
    }


    public Boolean getStatus() {
        return status;
    }

    private void setStatus(Boolean status) {
        this.status = Objects.requireNonNull(status, "status cannot be null");
    }

    public OrderState getState() {
        return state;
    }

    private void setState(OrderState state,Instant changedOn) {
        Objects.requireNonNull(state, "state must not be null");
        Objects.requireNonNull(changedOn, "changedOn must not be null");


//        if (stateChangeHistory.stream().anyMatch(stateChange -> stateChange.state().equals(state))) {
//            throw new IllegalStateException("Order has already been in state " + state);
//
//        }

        this.state = state;
        var stateChange = new OrderStateChange(changedOn, state);
        stateChangeHistory.add(stateChange);
        if (stateChangeHistory.size() > 1) {
            new OrderStateChange(changedOn, state);
        }
    }

    public RecipientAddress getShippingAddress() {
        return shippingAddress;
    }

    private void setShippingAddress(RecipientAddress shippingAddress) {
        this.shippingAddress = Objects.requireNonNull(shippingAddress, "shippingAddress must not be null");
    }


    public Set<OrderStateChange> getStateChangeHistory() {
        return stateChangeHistory;
    }


    public OrderItem addItem( Product product, int qty) {
        Objects.requireNonNull(product, "product must not be null");
        var item = new OrderItem(product.id(), product.name(), product.price(), qty);
        items.add(item);
        return item;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void cancel(Instant instant) {
        setState(OrderState.CANCELLED, instant);
    }

    public void startProcessing(Instant instant) {
        setState(OrderState.PROCESSING, instant);
    }

    public void finishProcessing(Instant instant) {
        setState(OrderState.PROCESSED, instant);
    }


    @Override
    public Long version() {
        return null;
    }
}
