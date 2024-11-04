package com.javarush.island.levchuk;


import com.javarush.island.levchuk.engine.Executor;
import com.javarush.island.levchuk.view.ConsoleProvider;

public class AppRunner {
    public static void main(String[] args) {
        ConsoleProvider consoleProvider = new ConsoleProvider();
        Executor executor = new Executor(consoleProvider);
        executor.startGame();
    }
}
