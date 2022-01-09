package com.myshop.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {

        super("Incorrect password please try again!");
    }
}
