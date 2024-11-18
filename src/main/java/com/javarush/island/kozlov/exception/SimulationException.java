package com.javarush.island.kozlov.exception;

public class SimulationException extends Exception{

    public SimulationException(String message) {
        super(message);
    }

    public SimulationException(String message, Throwable cause) {
        super(message, cause);
    }
}
