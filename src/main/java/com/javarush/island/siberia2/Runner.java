package com.javarush.island.siberia2;

import com.javarush.island.siberia2.config.ConfigLoader;
import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.simulation.Simulation;
import com.javarush.island.siberia2.ui.WindowFrame;

import javax.swing.*;

public class Runner {
    public static void main(String[] args) {

        Settings settings = ConfigLoader.loadSettings();

        SwingUtilities.invokeLater(() -> {
            WindowFrame windowFrame = new WindowFrame();
            windowFrame.run();

            Simulation simulation = new Simulation(settings);
            new Thread(simulation).start();
        });
    }
}
