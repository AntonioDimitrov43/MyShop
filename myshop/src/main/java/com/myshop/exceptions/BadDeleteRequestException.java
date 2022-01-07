package com.myshop.exceptions;

public class BadDeleteRequestException extends RuntimeException{

    public BadDeleteRequestException(String message) {
        super(message);
    }
}
