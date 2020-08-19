package com.backbase.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingNumbersException extends RuntimeException {
    public MissingNumbersException(String msg) {
        super(msg);
    }
}
