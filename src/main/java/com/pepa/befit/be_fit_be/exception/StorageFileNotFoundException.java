package com.pepa.befit.be_fit_be.exception;

public class StorageFileNotFoundException extends RuntimeException {
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
