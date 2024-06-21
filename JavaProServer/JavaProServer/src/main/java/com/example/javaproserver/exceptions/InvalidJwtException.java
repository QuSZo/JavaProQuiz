package com.example.javaproserver.exceptions;

public class InvalidJwtException extends Exception {
    public InvalidJwtException(String errorMessage) {
        super(errorMessage);
    }
}
