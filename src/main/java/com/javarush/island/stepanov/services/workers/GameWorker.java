package com.javarush.island.stepanov.services.workers;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.Game;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.map.GeneralStatistic;
import com.javarush.island.stepanov.services.workers.cellworkers.*;
import com.javarush.island.stepanov.view.View;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.javarush.island.stepanov.constants.Constants.FIRST_STEP;

@RequiredArgsConstructor
public class GameWorker extends Thread {
    public static final int CORES = Runtime.getRuntime().availableProcessors();
    private final Game game;
    private final List<CellWorker> eatWorkers = new ArrayList<>();
    private final List<CellWorker> reproduceWorkers = new ArrayList<>();
    private final List<CellWorker> moveWorkers = new ArrayList<>();
    private final List<CellWorker> statisticWorkers = new ArrayList<>();
    private final List<CellWorker> starveworkers = new ArrayList<>();
    private final ExecutorService servicePool = Executors.newFixedThreadPool(CORES);

    @Override
    public void run() {
        GameMap gameMap = game.getGameMap();
        GeneralStatistic generalStatisticsMap = gameMap.getGeneralStatisticsMap();
        View view = game.getView();
        int step = FIRST_STEP;
        int stepDelay = Setting.get().getStepDelay();

        getWorkersLists(gameMap);
        try {
            while (step < Setting.get().getTurns()) {
                runWorkers(eatWorkers);
                runWorkers(reproduceWorkers);
                runWorkers(moveWorkers);
                generalStatisticsMap.clear();
                runWorkers(statisticWorkers);
                sleep(stepDelay);
                step++;
                view.show(step);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            servicePool.shutdown();
            try {
                if (!servicePool.awaitTermination(60, TimeUnit.SECONDS)) {
                    servicePool.shutdownNow();
                }
            } catch (InterruptedException e) {
                servicePool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void getWorkersLists(GameMap gameMap) {
        Cell[][] cells = gameMap.getCells();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                eatWorkers.add(new EatWorker(gameMap, cell));
                moveWorkers.add(new MoveWorker(gameMap, cell));
                reproduceWorkers.add(new ReproduceWorker(gameMap, cell));
                starveworkers.add(new StarveWorker(gameMap, cell));
                statisticWorkers.add(new StatisticWorker(gameMap, cell));
            }
        }
    }

    private void runWorkers(List<CellWorker> workers) {
        // Отправляем все задачи в пул
        List<Future<Void>> futures = workers.stream()
                .map(servicePool::submit)
                .toList();
        // Ожидаем завершения всех задач
        for (Future<Void> future : futures) {
            try {
                future.get(); // Ожидаем завершения каждой задачи
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
