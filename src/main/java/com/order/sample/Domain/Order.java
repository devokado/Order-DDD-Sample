package com.order.sample.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.order.sample.Domain.Exceptions.IllegalStateException;
import com.order.sample.Domain.SeedWork.Base.AbstractAggregateRoot;
import com.order.sample.Domain.SeedWork.Base.ConcurrencySafeDomainObject;
import com.order.sample.Domain.SeedWork.Base.DomainObjectId;
import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Enums.OrderState;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;
import com.order.sample.Presentation.Rest.Request.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;


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

    private void StateValidation(){

    }

    @NonNull
    @JsonProperty("currency")
    public Currency currency() {
        return currency;
    }

    private void setCurrency(@NonNull Currency currency) {
        this.currency = Objects.requireNonNull(currency, "currency must not be null");
    }

    @NonNull
    @JsonProperty("orderedOn")
    public Instant orderedOn() {
        return orderedOn;
    }

    private void setOrderedOn(@NonNull Instant orderedOn) {
        this.orderedOn = Objects.requireNonNull(orderedOn, "orderedOn must not be null");
    }

    @NonNull
    @JsonProperty("status")
    public Boolean status() {
        return status;
    }

    private void setStatus(@NonNull Boolean status) {
        this.status = Objects.requireNonNull(status, "status cannot be null");
    }

    @NonNull
    @JsonProperty("state")
    public OrderState state() {
        return state;
    }


    private void setState(@NonNull OrderState state, @NonNull Instant changedOn) {
        Objects.requireNonNull(state, "state must not be null");
        Objects.requireNonNull(changedOn, "changedOn must not be null");
        if (stateChangeHistory.stream().anyMatch(stateChange -> stateChange.state().equals(state))) {
            throw new IllegalStateException("Order has already been in state " + state, HttpStatus.BAD_REQUEST);
        }
        this.state = state;
        var stateChange = new OrderStateChange(changedOn, state);
        stateChangeHistory.add(stateChange);
        if (stateChangeHistory.size() > 1) {
            new OrderStateChange(changedOn, state);
        }
    }

    @NonNull
    @JsonProperty("shippingAddress")
    public RecipientAddress shippingAddress() {
        return shippingAddress;
    }

    private void setShippingAddress(@NonNull RecipientAddress shippingAddress) {
        this.shippingAddress = Objects.requireNonNull(shippingAddress, "shippingAddress must not be null");
    }

    @NonNull
    @JsonProperty("stateChangeHistory")
    public Stream<OrderStateChange> stateChangeHistory() {
        return stateChangeHistory.stream();
    }


    @NonNull
    public OrderItem addItem(@NonNull Product product, int qty) {
        Objects.requireNonNull(product, "product must not be null");
        var item = new OrderItem(product.id(), product.name(), product.price(), qty);
        items.add(item);
        return item;
    }

    @NonNull
    @JsonProperty("items")
    public Stream<OrderItem> items() {
        return items.stream();
    }

    public void cancel(@NonNull Instant instant) {
        setState(OrderState.CANCELLED, instant);
    }

    public void startProcessing(@NonNull Instant instant) {
        setState(OrderState.PROCESSING, instant);
    }

    public void finishProcessing(@NonNull Instant instant) {
        setState(OrderState.PROCESSED, instant);
    }

    @Nullable
    public Long version() {
        return version;
    }


}
