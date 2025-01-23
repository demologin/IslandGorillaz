package com.javarush.island.nikitin;

import com.javarush.island.nikitin.application.AppRunner;
import com.javarush.island.nikitin.application.controllers.AppController;
import com.javarush.island.nikitin.presentation.controllers.View;
import com.javarush.island.nikitin.presentation.controllers.ViewConsole;

public class ConsoleRun {

    public static void main(String[] args) {
        View consoleController = new ViewConsole();
        var appController = new AppController(consoleController);
        var appRunner = new AppRunner(appController);
        appRunner.start();
    }
}