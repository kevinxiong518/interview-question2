package com.backbase.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArrayNotFoundException extends RuntimeException {
    public ArrayNotFoundException(String msg) {
        super(msg);
    }
}
