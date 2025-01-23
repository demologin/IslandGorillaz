package com.javarush.island.nikitin.presentation.view;

import com.javarush.island.nikitin.presentation.constants.Messages;

import javax.swing.*;
import java.awt.*;

public class PrinterGui implements Printer {
    private final JTextArea textAreaStat;
    private final JTextArea textAreaCell;


    public PrinterGui() {
        JFrame frame = new JFrame(Messages.NAME_GAME);
        this.textAreaStat = new JTextArea();
        this.textAreaCell = new JTextArea();
        textAreaStat.setEditable(false);
        textAreaCell.setEditable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 500);
        frame.setLocationRelativeTo(null);

        Font monospaced = new Font("Monospaced", Font.PLAIN, 12);
        textAreaStat.setFont(monospaced);
        textAreaCell.setFont(monospaced);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Statistics", textAreaStat);
        tabbedPane.addTab("Locations", textAreaCell);
        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    @Override
    public void print(String... string) {
        textAreaStat.setText(string[0]);
        textAreaCell.setText(string[1]);
    }
}
