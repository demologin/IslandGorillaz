package com.javarush.island.siberia2;

import com.javarush.island.siberia2.config.ConfigLoader;
import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.ui.Sound;

public class Runner {
    public static void main(String[] args) {

        Sound sound = new Sound();

        Settings settings = ConfigLoader.loadSettings();
        if (settings != null) {
            System.out.println("Island Width: " + settings.getIslandSettings().getWidth());
            System.out.println("Wolf speed: " + settings.getAnimalsSettings().get("Wolf").getSpeed());
        }

        sound.setFile(0);
        sound.play();
        //sound.loop();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}