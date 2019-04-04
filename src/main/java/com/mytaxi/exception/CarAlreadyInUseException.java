package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Car is already in use by other driver")
public class CarAlreadyInUseException extends Exception {

    public CarAlreadyInUseException(String message) {
        super(message);
    }

}
