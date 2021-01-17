package com.order.sample.Application.Exceptions;

import com.order.sample.Domain.Exceptions.RestException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RestException {

    public NotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
