package com.order.sample.Domain.Validators;

import com.order.sample.Domain.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return Order.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        Order o =(Order) obj;
//        if (o.getStateChangeHistory().anyMatch(stateChange -> stateChange.state().equals(o.getState()))) {
//
//          e.rejectValue("state","is already in this state");
//
//        }

    }
}
