package com.order.sample.Domain.Exceptions;

import org.springframework.http.HttpStatus;

public class IllegalStateException extends RestException{
    public IllegalStateException(String message, HttpStatus status) {
        super(message, status);
    }
}
