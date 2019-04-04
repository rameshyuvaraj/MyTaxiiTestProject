package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some constraints are violated ...")
public class NoCarAssignedException extends Exception {
    public NoCarAssignedException(String message) {
        super(message);
    }
}
