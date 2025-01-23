package com.javarush.island.nikitin;

import com.javarush.island.nikitin.application.AppRunner;
import com.javarush.island.nikitin.application.controllers.AppController;
import com.javarush.island.nikitin.presentation.controllers.View;
import com.javarush.island.nikitin.presentation.controllers.ViewGui;
import com.javarush.island.nikitin.presentation.view.Printer;
import com.javarush.island.nikitin.presentation.view.PrinterGui;

public class SwingRunner {

    public static void main(String[] args) {
        Printer printer = new PrinterGui();
        View swingView = new ViewGui(printer);
        var appController = new AppController(swingView);
        var appRunner = new AppRunner(appController);
        appRunner.start();
    }
}
