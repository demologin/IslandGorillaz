package com.javarush.island.nikitin;

import com.javarush.island.nikitin.application.AppRunner;
import com.javarush.island.nikitin.presentation.ConsoleView;
import com.javarush.island.nikitin.presentation.api.View;

public class ConsoleRun {

    public static void main(String[] args) {
        View consoleView = new ConsoleView();
        AppRunner appRunner = new AppRunner(consoleView);

        appRunner.start();
    }
}