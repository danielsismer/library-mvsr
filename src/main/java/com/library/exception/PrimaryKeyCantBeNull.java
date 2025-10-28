package com.library.exception;

public class PrimaryKeyCantBeNull extends RuntimeException{
    public PrimaryKeyCantBeNull(String type) {
        super("The " + type + " cant be null, please, try again");
    }
}
