package com.javarush.island.siberia2.simulation;

import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import com.javarush.island.siberia2.services.PopulateOrganisms;
import com.javarush.island.siberia2.services.StatisticsService;
import com.javarush.island.siberia2.ui.WindowFrame;
import lombok.Getter;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation implements Runnable {

    @Getter
    private final Island island;
    private final StatisticsService statisticsService;
    private final int simulationStepDuration;
    private final AtomicInteger bornCount = new AtomicInteger(0);
    private final AtomicInteger eatenCount = new AtomicInteger(0);
    private final AtomicInteger starvedCount = new AtomicInteger(0);
    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduler;
    private final WindowFrame windowFrame;
    private final SimulationStepHandler simulationStepHandler;

    public Simulation(Island island, Settings settings, WindowFrame windowFrame) {
        this.island = island;
        this.windowFrame = windowFrame;
        this.simulationStepDuration = settings.getIslandSettings().getSimulationStepDuration();
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.statisticsService = new StatisticsService(island);
        this.simulationStepHandler = new SimulationStepHandler(bornCount, eatenCount, starvedCount);
        OrganismPopulation organismPopulation = new OrganismPopulation(new PopulateOrganisms());

        organismPopulation.populateIslandAtStart(island, settings);
    }

    @Override
    public void run() {
        scheduler.scheduleAtFixedRate(() -> {
            simulateStep();
            statisticsService.printStatistics(bornCount.get(), eatenCount.get(), starvedCount.get());
            bornCount.set(0);
            eatenCount.set(0);
            starvedCount.set(0);
            SwingUtilities.invokeLater(() -> windowFrame.getTilePanel().repaint());
        }, 0, simulationStepDuration, TimeUnit.MILLISECONDS);
    }

    private void simulateStep() {
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                final Cell cell = island.getCell(x, y);
                tasks.add(() -> {
                    simulationStepHandler.processCell(cell);
                    return null;
                });
            }
        }
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}