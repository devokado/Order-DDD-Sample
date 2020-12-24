package com.order.sample.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.order.sample.Domain.SeedWork.Base.AbstractAggregateRoot;
import com.order.sample.Domain.SeedWork.Base.ConcurrencySafeDomainObject;
import com.order.sample.Domain.SeedWork.Base.DomainObjectId;
import com.order.sample.Domain.SeedWork.Enums.Currency;
import com.order.sample.Domain.SeedWork.Enums.OrderState;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class Order extends AbstractAggregateRoot<OrderId> implements ConcurrencySafeDomainObject {
    @Version
    private Long version;
    private Instant orderedOn;
    private Currency currency;
    @Column(name = "order_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_state_changes")
    private Set<OrderStateChange> stateChangeHistory;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "shipping_name", nullable = false)),
            @AttributeOverride(name = "addressLine1", column = @Column(name = "shipping_addr1", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "shipping_country", nullable = false))
    })
    private RecipientAddress shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Set<OrderItem> items;

    public Order() {
    }

    public Order(Instant orderedOn, Currency currency, RecipientAddress recipientAddress) {
        super(DomainObjectId.randomId(OrderId.class));
        this.stateChangeHistory = new HashSet<>();
        this.items = new HashSet<>();
        setOrderedOn(orderedOn);
        setCurrency(currency);
        setState(OrderState.RECEIVED, orderedOn);
        setShippingAddress(recipientAddress);
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
    @JsonProperty("state")
    public OrderState state() {
        return state;
    }

    private void setState(@NonNull OrderState state, @NonNull Clock clock) {
        Objects.requireNonNull(clock, "clock must not be null");
        setState(state, clock.instant());
    }
    private void setState(@NonNull OrderState state, @NonNull Instant changedOn) {
        Objects.requireNonNull(state, "state must not be null");
        Objects.requireNonNull(changedOn, "changedOn must not be null");
        if (stateChangeHistory.stream().anyMatch(stateChange -> stateChange.state().equals(state))) {
            throw new IllegalStateException("Order has already been in state " + state);
        }
        this.state = state;
        var stateChange = new OrderStateChange(changedOn, state);
        stateChangeHistory.add(stateChange);
        if (stateChangeHistory.size() > 1) {// Don't fire an event for the initial state
           // registerEvent(new OrderStateChanged(id(), stateChange.state(), stateChange.changedOn()));
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
        var item = new OrderItem(product.id(), product.name(),product.price());
        item.setQuantity(qty);
        items.add(item);
        return item;
    }

    @NonNull
    @JsonProperty("items")
    public Stream<OrderItem> items() {
        return items.stream();
    }

    public void cancel(@NonNull Clock clock) {
        setState(OrderState.CANCELLED, clock);
    }

    public void startProcessing(@NonNull Clock clock) {
        setState(OrderState.PROCESSING, clock);
    }

    public void finishProcessing(@NonNull Clock clock) {
        setState(OrderState.PROCESSED, clock);
    }

    @Nullable
    public Long version() {
        return version;
    }
}
