package com.codeapps.loundry.exceptions;

public class CustomException extends RuntimeException{
    public CustomException() {
        super("Custom exception!");
    }

    public CustomException(String message) {
        super(message);
    }
}
