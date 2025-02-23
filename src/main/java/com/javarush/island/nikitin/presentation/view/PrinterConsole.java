package com.javarush.island.nikitin.presentation.view;

public class PrinterConsole implements Printer {
    @Override
    public void print(String... string) {
        System.out.print(string[0]);
    }
}

