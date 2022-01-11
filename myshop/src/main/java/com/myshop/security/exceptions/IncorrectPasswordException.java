package com.myshop.security.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {

        super("Incorrect password please try again!");
    }
}
