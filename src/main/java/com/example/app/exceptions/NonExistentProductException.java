package com.example.app.exceptions;

public class NonExistentProductException extends RuntimeException {

    public NonExistentProductException(String message) {
        super(message);
    }
}
