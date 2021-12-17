package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "First name not found.")
public class FirstNameNotFoundException extends RuntimeException {

    public FirstNameNotFoundException(String s) {
    }
}