package com.valtech.amel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidValueException extends RuntimeException {

    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValueException() {

    }
}
