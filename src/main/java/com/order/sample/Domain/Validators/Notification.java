package com.order.sample.Domain.Validators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {
    private List<Error> errors = new ArrayList<>();

    public void addError(String message, Exception e) {
        errors.add(new Error(message,e));
    }

    public String errorMessage() {
        return errors.stream()
                .map(e -> e.message)
                .collect(Collectors.joining(", "));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    private static class Error {
        String message;
        Exception cause;

        private Error(String message, Exception cause) {
            this.message = message;
            this.cause = cause;
        }
    }
}
