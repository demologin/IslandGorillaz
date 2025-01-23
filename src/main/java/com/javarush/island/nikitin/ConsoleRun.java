package com.javarush.island.nikitin;

import com.javarush.island.nikitin.application.AppRunner;
import com.javarush.island.nikitin.application.controllers.AppController;
import com.javarush.island.nikitin.presentation.controllers.ViewConsole;
import com.javarush.island.nikitin.presentation.controllers.View;
import com.javarush.island.nikitin.presentation.view.Printer;
import com.javarush.island.nikitin.presentation.view.PrinterConsole;

public class ConsoleRun {

    public static void main(String[] args) {
        Printer printer = new PrinterConsole();
        View consoleController = new ViewConsole(printer);
        var appController = new AppController(consoleController);
        var appRunner = new AppRunner(appController);
        appRunner.start();
    }
}