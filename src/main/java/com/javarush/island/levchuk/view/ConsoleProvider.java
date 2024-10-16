package com.javarush.island.levchuk.view;

import java.util.Scanner;

public class ConsoleProvider {
    private final Scanner scanner = new Scanner(System.in);

    public String read() {
        return scanner.nextLine();
    }

    public void print(Object message) {
        System.out.print(message);
    }

    public void println(Object message) {
        System.out.println(message);
    }
    public void printfMessage(String contextMessage, String... message) {
        System.out.printf(contextMessage, message);
    }

}