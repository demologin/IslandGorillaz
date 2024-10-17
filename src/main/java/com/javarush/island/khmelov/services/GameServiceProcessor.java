package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.Game;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
public class GameServiceProcessor extends Thread {
    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final Game game;
    private final List<Runnable> services;
    private final int PERIOD = Setting.get().life.getPeriod();
    private ScheduledExecutorService mainPool;

    public GameServiceProcessor(Game game, List<Runnable> services) {
        this.game = game;
        this.services = services;
    }

    @Override
    public void run() {
        mainPool = Executors.newSingleThreadScheduledExecutor();
        mainPool.scheduleWithFixedDelay(this::doOneStepGame, 0, PERIOD, TimeUnit.MILLISECONDS);
    }

    private void doOneStepGame() {
        if (!game.isFinished()) {
            try (ExecutorService servicePool = Executors.newFixedThreadPool(CORE_POOL_SIZE)) {
                services.forEach(servicePool::submit);
                servicePool.shutdown();
            }
        } else {
            mainPool.shutdown();
        }
    }
}
