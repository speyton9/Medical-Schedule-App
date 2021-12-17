package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "Last name not found.")
public class LastNameNotFoundException extends RuntimeException {

    public LastNameNotFoundException(String s) {
    }
}