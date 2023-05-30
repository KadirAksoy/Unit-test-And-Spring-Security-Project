package com.kadiraksoy.UnittestAndSpringSecurityProject.exception;

public class MisketNotFoundException extends RuntimeException{

    public MisketNotFoundException(String message) {
        super(message);
    }
}
