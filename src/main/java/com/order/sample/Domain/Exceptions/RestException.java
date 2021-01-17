package com.order.sample.Domain.Exceptions;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {
    private HttpStatus status;

    public RestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
