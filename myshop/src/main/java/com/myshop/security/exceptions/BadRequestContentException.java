package com.myshop.security.exceptions;

public class BadRequestContentException extends RuntimeException{

    public BadRequestContentException() {
    }

    public BadRequestContentException(String message) {
        super(message);
    }
}
