package com.javarush.island.ivanov;

import com.javarush.island.ivanov.api.engine.Executor;

public class AppRunner {
    public static void main(String[] args) {
        Executor executor = new Executor();
        executor.startGame();
    }
}
