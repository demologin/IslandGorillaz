package com.javarush.island.siberia2;

import com.javarush.island.siberia2.config.ConfigLoader;
import com.javarush.island.siberia2.config.Settings;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentHashMap;

public class TestRunner {
    public static void main(String[] args) {

        Settings settings = ConfigLoader.loadSettings();


        JFrame frame = new JFrame("Test runner for draw animals");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ConcurrentHashMap<String, BufferedImage> animalImages = new ConcurrentHashMap<>();
                settings.getAnimalsSettings().forEach((name, animalSettings) -> {
                    try {
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream(animalSettings.getImgPath()));
                        animalImages.put(name, image);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                int x = 0;
                int y = 0;
                int size = 32;
                int margin = 16;

                for (BufferedImage animalImage : animalImages.values()) {
                    g.drawImage(animalImage, x, y, size, size, null);
                    x += size + margin;
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }
}
