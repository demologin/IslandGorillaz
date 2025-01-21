package com.javarush.island.stepanov.services.workers;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.Game;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.services.workers.cellworkers.CellWorker;
import com.javarush.island.stepanov.services.workers.cellworkers.EatWorker;
import com.javarush.island.stepanov.services.workers.cellworkers.MoveWorker;
import com.javarush.island.stepanov.services.workers.cellworkers.ReproduceWorker;
import com.javarush.island.stepanov.view.View;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RequiredArgsConstructor
public class GameWorker extends Thread {
    public static final int CORES = Runtime.getRuntime().availableProcessors();
    private final Game game;
    private final int PERIOD = Setting.get().getStepDelay();
    private final List<CellWorker> eatWorkers = new ArrayList<>();
    private final List<CellWorker> reproduceWorkers = new ArrayList<>();
    private final List<CellWorker> moveWorkers = new ArrayList<>();
    private final ExecutorService servicePool = Executors.newFixedThreadPool(CORES);

    @Override
    public void run() {
        View view = game.getView();
        getWorkersLists(game.getGameMap());
        int step = 0;
        int stepDelay = Setting.get().getStepDelay();

        try {
            while (step < Setting.get().getTurns()) {
                runWorkers(eatWorkers);
                runWorkers(reproduceWorkers);
                runWorkers(moveWorkers);
                sleep(stepDelay);
                step++;
                view.show(step);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // Завершаем пул после выполнения всех итераций
            servicePool.shutdown();
            try {
                // Ожидаем завершения всех задач
                if (!servicePool.awaitTermination(60, TimeUnit.SECONDS)) {
                    servicePool.shutdownNow(); // Принудительно завершаем, если задачи не завершились
                }
            } catch (InterruptedException e) {
                servicePool.shutdownNow(); // Принудительно завершаем, если поток был прерван
                Thread.currentThread().interrupt(); // Восстанавливаем статус прерывания
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
