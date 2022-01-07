package com.myshop.exceptions;

public class BadRequestContentException extends RuntimeException{

    public BadRequestContentException() {
    }

    public BadRequestContentException(String message) {
        super(message);
    }
}
