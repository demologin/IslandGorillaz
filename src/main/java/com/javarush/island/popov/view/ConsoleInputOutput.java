package com.javarush.island.popov.view;

import java.util.Scanner;

public class ConsoleInputOutput {
    private final Scanner scanner = new Scanner(System.in);

    public int read(){
       return scanner.nextInt();
    }
    public String readEnter(){
        return scanner.nextLine();
    }
    public void write(String message){
        System.out.println(message);
    }

}
