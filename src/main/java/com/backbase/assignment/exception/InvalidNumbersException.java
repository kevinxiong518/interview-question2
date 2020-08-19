package com.backbase.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidNumbersException extends NumberFormatException {
    public InvalidNumbersException(String msg) {
        super(msg);
    }
}
