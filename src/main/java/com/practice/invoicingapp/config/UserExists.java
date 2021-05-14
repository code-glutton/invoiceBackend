package com.practice.invoicingapp.config;

public class UserExists extends RuntimeException{
    public UserExists(String message) {
        super(message);
    }

    public UserExists(String message, Throwable cause) {
        super(message, cause);
    }
}
