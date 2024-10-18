package com.javarush.island.siberia2.ui;

import com.javarush.island.siberia2.config.Constants;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.FileNotFoundException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        try {
            soundURL[0] = getClass().getClassLoader().getResource(Constants.MAIN_SOUND_PATH);
            if (soundURL[0] == null) {
                throw new FileNotFoundException(Constants.NO_MUSIC_FILE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();

        new Thread(() -> {
            try {
                while (clip.isRunning()) {
                    Thread.sleep(Constants.SLEEP_TIME_CHECK_THREAD);
                }
                clip.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}