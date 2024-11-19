package com.javarush.island.nikitin;

import com.javarush.island.nikitin.application.AppRunner;
import com.javarush.island.nikitin.presentation.api.View;
import com.javarush.island.nikitin.presentation.SwingView;

public class SwingRunner {
    public static void main(String[] args) {
        View swingView = new SwingView();
        AppRunner appRunner = new AppRunner(swingView);
        appRunner.start();
    }
}
