package com.javarush.island.siberia2;

import com.javarush.island.siberia2.config.ConfigLoader;
import com.javarush.island.siberia2.config.Settings;

public class Runner {
    public static void main(String[] args) {

        Settings settings = ConfigLoader.loadSettings();
        if (settings != null) {
            System.out.println("Island Width: " + settings.getIsland().getWidth());
            System.out.println("Wolf speed: " + settings.getAnimals().get("Wolf").getSpeed());
        }
    }
}