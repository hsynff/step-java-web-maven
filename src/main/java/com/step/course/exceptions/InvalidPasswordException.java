package com.step.course.exceptions;

public class InvalidPasswordException extends Exception {
    private String message;


    public InvalidPasswordException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
