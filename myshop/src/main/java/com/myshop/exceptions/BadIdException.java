package com.myshop.exceptions;

public class BadIdException extends RuntimeException{

    public BadIdException(String message) {
        super(message);
    }
}
