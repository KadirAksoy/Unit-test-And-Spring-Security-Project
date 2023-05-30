package com.kadiraksoy.UnittestAndSpringSecurityProject.exception;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
