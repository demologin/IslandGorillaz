package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.Game;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameWorker extends Thread {
    public static final int CORES = Runtime.getRuntime().availableProcessors();
    private final Game game;
    private final int PERIOD = Setting.get().getPeriod();

    @Override
    public void run(){

    }
}
