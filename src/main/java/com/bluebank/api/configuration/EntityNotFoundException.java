package com.bluebank.api.configuration;

public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String cause) {
        super(cause);
    }

}