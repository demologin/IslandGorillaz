package com.javarush.island.siberia2;

import com.javarush.island.siberia2.ui.WindowFrame;

public class Runner {
    public static void main(String[] args) {

        WindowFrame windowFrame = new WindowFrame();
        Thread windowThread = new Thread(windowFrame);
        windowThread.start();
    }

}