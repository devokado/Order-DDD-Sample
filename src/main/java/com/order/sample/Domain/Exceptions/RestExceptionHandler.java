package com.order.sample.Domain.Exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestException.class)
    protected ResponseEntity<ExceptionResponse> handleRestException(RestException ex) {
        return new ResponseEntity<>(ExceptionResponse.create(ex.getStatus().value(), ex.getMessage()), ex.getStatus());
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Object> handleCustomException(CustomException ex) {
        return new ResponseEntity<>(ex.getObject(), HttpStatus.valueOf(ex.getStatus()));
    }
}
