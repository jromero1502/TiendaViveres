package com.example.app.exceptions;

public class DuplicatedProductException extends RuntimeException{

    public DuplicatedProductException(String message) {
        super(message);
    }
}
