package com.myshop.security.exceptions;

public class BadDeleteRequestException extends RuntimeException{

    public BadDeleteRequestException(String message) {
        super(message);
    }
}
