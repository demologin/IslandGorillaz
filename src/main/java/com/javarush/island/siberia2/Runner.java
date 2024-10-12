package com.javarush.island.siberia2;

import com.javarush.island.siberia2.config.ConfigLoader;
import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.ui.WindowFrame;

public class Runner {
    public static void main(String[] args) {

        //for testing only. will remove in future
        Settings settings = ConfigLoader.loadSettings();
        if (settings != null) {
            System.out.println("Island Width: " + settings.getIslandSettings().getWidth());
            System.out.println("Wolf speed: " + settings.getAnimalsSettings().get("Wolf").getSpeed());
            System.out.println("Wolf what to eat: " + settings.getAnimalsSettings().get("Wolf").getEatProbability());
        }
        Thread windowThread = new Thread(new WindowFrame()); {
            windowThread.start();
        }

    }
}