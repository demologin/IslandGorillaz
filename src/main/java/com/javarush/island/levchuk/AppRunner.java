package com.javarush.island.levchuk;


import com.javarush.island.levchuk.engine.Executor;

public class AppRunner {
    public static void main(String[] args) {
        Executor executor = new Executor();
        executor.startGame();
    }
}
