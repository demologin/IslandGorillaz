package com.javarush.island.nikitin;

import com.javarush.island.nikitin.application.AppRunner;
import com.javarush.island.nikitin.application.controllers.AppController;
import com.javarush.island.nikitin.presentation.controllers.View;
import com.javarush.island.nikitin.presentation.controllers.ViewGui;

public class SwingRunner {

    public static void main(String[] args) {
        View swingView = new ViewGui();
        var appController = new AppController(swingView);
        var appRunner = new AppRunner(appController);
        appRunner.start();
    }
}
