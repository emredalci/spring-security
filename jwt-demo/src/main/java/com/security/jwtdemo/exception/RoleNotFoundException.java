package com.security.jwtdemo.exception;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(String message) {
        super(message);
    }
}
