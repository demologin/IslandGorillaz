package com.javarush.island.gerasimov;

import com.javarush.island.gerasimov.entity.Game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Runner {
    static ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
    public static void main(String[] args) throws InterruptedException {

        Game game = new Game();
        game.addOrganisms();
        threadPool.scheduleWithFixedDelay(game, 1,1, TimeUnit.SECONDS);

    }
}
