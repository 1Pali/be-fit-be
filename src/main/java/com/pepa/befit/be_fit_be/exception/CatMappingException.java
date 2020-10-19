package com.pepa.befit.be_fit_be.exception;

public class CatMappingException extends RuntimeException {
    public CatMappingException(final String message) {
        super(message);
    }

    public CatMappingException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
