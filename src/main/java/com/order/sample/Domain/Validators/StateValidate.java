package com.order.sample.Domain.Validators;

import com.order.sample.Domain.OrderStateChange;
import com.order.sample.Domain.SeedWork.Enums.OrderState;

import java.util.Set;

public class StateValidate {
    static Notification note = new Notification();
    public static void validate(OrderState state, Set<OrderStateChange> stateChangeHistory) {
        if (stateChangeHistory.stream().anyMatch(stateChange -> stateChange.state().equals(state))) {
           note.addError("state has been already in this state",null);

        }
    }
}
