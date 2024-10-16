package com.javarush.island.kozlov.exception;

public class EatingException extends SimulationException{

    public EatingException(String message) {
        super(message);
    }

    public EatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
