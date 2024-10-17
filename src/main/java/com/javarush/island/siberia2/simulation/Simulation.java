package com.javarush.island.siberia2.simulation;

import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.map.Island;
import com.javarush.island.siberia2.services.PopulateOrganisms;
import com.javarush.island.siberia2.services.Printer;
import com.javarush.island.siberia2.services.StatisticsService;
import com.javarush.island.siberia2.ui.WindowFrame;
import lombok.Getter;
import javax.swing.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation implements Runnable {

    @Getter
    private final Island island;
    private final StatisticsService statisticsService;
    private final int simulationStepDuration;
    private final AtomicInteger bornCount = new AtomicInteger(0);
    private final AtomicInteger eatenCount = new AtomicInteger(0);
    private final AtomicInteger starvedCount = new AtomicInteger(0);
    private final ForkJoinPool forkJoinPool;
    private final WindowFrame windowFrame;
    private final SimulationStepHandler simulationStepHandler;

    public Simulation(Island island, Settings settings, WindowFrame windowFrame) {
        this.island = island;
        this.windowFrame = windowFrame;
        this.simulationStepDuration = settings.getIslandSettings().getSimulationStepDuration();
        this.forkJoinPool = new ForkJoinPool();
        Printer printer = new Printer();
        this.statisticsService = new StatisticsService(island, printer);
        this.simulationStepHandler = new SimulationStepHandler(bornCount, eatenCount, starvedCount);
        OrganismPopulation organismPopulation = new OrganismPopulation(new PopulateOrganisms());

        organismPopulation.populateIslandAtStart(island, settings);
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> windowFrame.setVisible(true));

        while (true) {
            forkJoinPool.invoke(new CellTask(0, island.getHeight(), island, simulationStepHandler));
            statisticsService.printStatistics(bornCount.get(), eatenCount.get(), starvedCount.get());

            bornCount.set(0);
            eatenCount.set(0);
            starvedCount.set(0);

            SwingUtilities.invokeLater(() -> windowFrame.getTilePanel().repaint());

            try {
                TimeUnit.MILLISECONDS.sleep(simulationStepDuration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}