package com.javarush.island.kozlov.exception;

public class ReproductionException extends SimulationException{

    public ReproductionException(String message) {
        super(message);
    }

    public ReproductionException(String message, Throwable cause) {
        super(message, cause);
    }
}
