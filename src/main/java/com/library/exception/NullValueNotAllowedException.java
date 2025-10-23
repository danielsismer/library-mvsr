package com.library.exception;

public class NullValueNotAllowedException extends RuntimeException {

    public NullValueNotAllowedException(String type) {
        super("The " + type + " cannot have any null values.");
    }

}