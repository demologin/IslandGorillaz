package com.javarush.island.nikitin.domain.exception;

public class DomainException extends RuntimeException{

    public DomainException(String message) {
        super(message);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }
}
