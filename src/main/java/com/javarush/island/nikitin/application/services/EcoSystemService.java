package com.javarush.island.nikitin.application.services;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.controllers.AppController;
import com.javarush.island.nikitin.application.exception.AppException;
import com.javarush.island.nikitin.application.util.TimeTracker;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EcoSystemService {
    private final EcoSystem ecoSystem;
    private final AppController appController;
    private final Settings settings;
    private final int coreCount;
    private final TimeTracker timeTracker;
    private final HashSet<Future<Long>> bufferFutures;

    public EcoSystemService(EcoSystem ecoSystem, AppController appController, Settings settings) {
        this.ecoSystem = ecoSystem;
        this.appController = appController;
        this.settings = settings;
        this.timeTracker = new TimeTracker();
        this.coreCount = Runtime.getRuntime().availableProcessors();
        this.bufferFutures = new HashSet<>();
    }

    /**
     * Begins the survival simulation process.
     * It runs the simulation for a specified number of periods,
     * updating the ecosystem and notifying the app controller at each step.
     */

    public void beginSurvival() {
        informController();
        try (var threadPool = Executors.newScheduledThreadPool(coreCount)) {
            for (int i = 0; i < settings.getPeriodLife(); i++) {
                timeTracker.beginTiming();

                ecoSystem.fillingQueueTask();
                while (!ecoSystem.isSimulateDayFinish()) {
                    if (ecoSystem.isEmptyQueue()) {
                        ecoSystem.fillingQueueTask();
                    }
                    Callable<Long> takeTask = ecoSystem.takeTaskFromQueue();
                    Future<Long> futureResultAllDeath = threadPool.submit(takeTask);
                    bufferFutures.add(futureResultAllDeath);
                }
                setNumberDeathsDay();

                timeTracker.endTiming();
                timeTracker.writeTimeLog();
                informController();
            }
            threadPool.shutdown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setNumberDeathsDay() {
        long countDeath = workAtResult(bufferFutures);
        bufferFutures.clear();
        ecoSystem.setNumberDeathsDay(countDeath);
    }

    private long workAtResult(Set<Future<Long>> futures) {
        return futures.stream().mapToLong(this::getValueFuture).sum();
    }

    private Long getValueFuture(Future<Long> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new AppException(e);
        }
    }

    private void informController() {
        appController.notifyView(ecoSystem, settings);
    }
}
