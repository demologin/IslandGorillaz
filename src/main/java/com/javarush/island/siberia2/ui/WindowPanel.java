package com.javarush.island.siberia2.ui;

import com.javarush.island.siberia2.config.WindowSettings;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JFrame implements Runnable {
    private JButton startButton;

    public WindowPanel(int width, int height) {
        initUI(width, height);
    }

    private void initUI(int width, int height) {
        setTitle("Island simulation");
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        startButton = new JButton("Start simulation");
        startButton.setBackground(Color.GREEN);
        startButton.setEnabled(true);
        startButton.addActionListener(e -> System.out.println("start was pressed"));

        setLayout(new BorderLayout());
        add(startButton, BorderLayout.CENTER);

        Sound sound = new Sound();
        sound.setFile(0);
        sound.play();
        sound.loop();

        pack();
    }


    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

}