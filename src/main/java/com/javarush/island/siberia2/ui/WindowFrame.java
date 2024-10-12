package com.javarush.island.siberia2.ui;

import com.javarush.island.siberia2.config.ConfigLoader;
import com.javarush.island.siberia2.config.Settings;
import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame implements Runnable {

    Settings settings = ConfigLoader.loadSettings();
    int width = settings.getWindowSettings().getWidth();
    int height = settings.getWindowSettings().getHeight();
    private JButton startButton;

    public WindowFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Island simulation");
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        startButton = new JButton("Start simulation");
        startButton.setBounds(200, 100, 100, 50);
        startButton.setBackground(Color.GREEN);
        startButton.setEnabled(true);
        startButton.addActionListener(e -> System.out.println("start was pressed"));

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(startButton);

        Sound sound = new Sound();
        sound.setFile(0);
        sound.play();
        sound.loop();

    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

}