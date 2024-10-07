package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.api.init.Initialization;
import com.javarush.island.khmelov.api.view.View;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.Game;
import com.javarush.island.khmelov.util.Rnd;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
public class GameWorker extends Thread {
    public static final int CORE_POOL_SIZE = 4;
    private final Game game;
    private final int cols;
    private final int rows;
    private final int PERIOD = Setting.get().life.getPeriod();
    private Initialization entityFactory;
    private ScheduledExecutorService mainPool;

    public GameWorker(Game game) {
        this.game = game;
        cols = Setting.get().getLife().getCols();
        rows = Setting.get().getLife().getRows();
    }


    @Override
    public void run() {
        View view = game.getView();
        mainPool = Executors.newSingleThreadScheduledExecutor();
        entityFactory = game.getEntityFactory();
        List<OrganismWorker> workers = entityFactory
                .getAllPrototypes()
                .stream()
                .map(o -> new OrganismWorker(o, game.getGameMap()))
                .toList();
        mainPool.scheduleWithFixedDelay(() -> doOneStepGame(view, workers)
                , 0, PERIOD, TimeUnit.MILLISECONDS);
    }

    private void doOneStepGame(View view, List<OrganismWorker> workers) {
        try (ExecutorService servicePool = Executors.newFixedThreadPool(CORE_POOL_SIZE)) {
            workers.forEach(servicePool::submit);
            servicePool.shutdown();
            awaitAndRepaint(view, servicePool);
            if (game.isFinished()) {
                mainPool.shutdown();
            } else {
                int row = Rnd.random(0, rows);
                int col = Rnd.random(0, cols);
                entityFactory.fill(game.getGameMap().getCells()[row][col], 1);
            }
        }
    }

    private void awaitAndRepaint(View view, ExecutorService servicePool) {
        if (awaitPool(servicePool)) {
            game.getGameMap().updateStatistics();
            view.show();
        }
    }

    @SneakyThrows
    private boolean awaitPool(ExecutorService servicePool) {
        return servicePool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }

}
