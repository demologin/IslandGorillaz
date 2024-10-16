package com.javarush.island.siberia2.simulation;

import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.Organism;
import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import com.javarush.island.siberia2.entity.map.MapData;
import com.javarush.island.siberia2.services.PopulateOrganisms;
import com.javarush.island.siberia2.services.StatisticsService;
import lombok.Getter;
import lombok.SneakyThrows;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation implements Runnable{
    @Getter
    private final Island island;
    private final StatisticsService statisticsService;
    private final int simulationStepDuration;
    int numberOfThreads = Runtime.getRuntime().availableProcessors();
    private final AtomicInteger bornCount = new AtomicInteger(0);
    private final AtomicInteger eatenCount = new AtomicInteger(0);
    private final AtomicInteger starvedCount = new AtomicInteger(0);

    public Simulation(Settings settings) {
        this.simulationStepDuration = settings.getIslandSettings().getSimulationStepDuration();
        MapData mapData = new MapData(settings.getIslandSettings().getWidth(), settings.getIslandSettings().getHeight());
        this.island = new Island(settings.getIslandSettings().getWidth(), settings.getIslandSettings().getHeight(), mapData);
        this.statisticsService = new StatisticsService(island);

        PopulateOrganisms populateOrganisms = new PopulateOrganisms();
        populateOrganisms.populate(island, settings);
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            simulateStep();
            statisticsService.printStatistics(bornCount.get(), eatenCount.get(), starvedCount.get());
            bornCount.set(0);
            eatenCount.set(0);
            starvedCount.set(0);
            //Thread.sleep(simulationStepDuration);
            try {
                Thread.sleep(simulationStepDuration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void simulateStep() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        int totalCells = island.getWidth() * island.getHeight();
        List<Callable<Void>> tasks = getTasks(totalCells);

        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private List<Callable<Void>> getTasks(int totalCells) {
        int cellsPerThread = totalCells / numberOfThreads;

        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            int startCellIndex = i * cellsPerThread;
            int endCellIndex = (i == numberOfThreads - 1) ? totalCells : startCellIndex + cellsPerThread;

            tasks.add(() -> {
                try {
                    processCells(startCellIndex, endCellIndex);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            });
        }
        return tasks;
    }

    private void processCells(int startCellIndex, int endCellIndex) {
        int width = island.getWidth();

        for (int index = startCellIndex; index < endCellIndex; index++) {
            int x = index % width;
            int y = index / width;

            Cell cell = island.getCell(x, y);

            List<Organism> organisms = new ArrayList<>();
            organisms.addAll(cell.getAnimals());
            organisms.addAll(cell.getPlants());

            for (Organism organism : organisms) {
                if (organism instanceof Animal animal) {
                    double previousFoodLevel = animal.getCurrentFoodLevel();

                    animal.liveCycle();

                    if (animal.getCurrentFoodLevel() <= 0) {
                        starvedCount.incrementAndGet();
                    }

                    if (cell.getAnimals().size() > organisms.size()) {
                        bornCount.incrementAndGet();
                    }

                    if (animal.getCurrentFoodLevel() > previousFoodLevel) {
                        eatenCount.incrementAndGet();
                    }
                }
            }
        }
    }

}