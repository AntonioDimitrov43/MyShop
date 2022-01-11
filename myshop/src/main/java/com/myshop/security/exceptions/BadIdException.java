package com.myshop.security.exceptions;

public class BadIdException extends RuntimeException{

    public BadIdException(String message) {
        super(message);
    }
}
