package com.javarush.island.kozlov.exception;

public class MoveException extends SimulationException{

    public MoveException(String message) {
        super(message);
    }

    public MoveException(String message, Throwable cause) {
        super(message, cause);
    }
}
