package com.javarush.island.nikitin.application.services;

import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import com.javarush.island.nikitin.presentation.api.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EcoSystemService {
    private final EcoSystem ecoSystem;
    private final View view;
    private final int coreCount;
    public static final Logger LOGGER = LoggerFactory.getLogger(EcoSystemService.class);

    public EcoSystemService(EcoSystem ecoSystem, View view) {
        this.ecoSystem = ecoSystem;
        this.view = view;
        this.coreCount = Runtime.getRuntime().availableProcessors();
    }

    /*public void simulateDay(int periodLive) {
        notifyView();
        long start = System.currentTimeMillis();
        LOGGER.info("Start {}", start);
        for (int i = 0; i < periodLive; i++) {

            ecoSystem.act();
            notifyView();
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        long stop = System.currentTimeMillis();
        long fulllTime = stop - start;
        LOGGER.info("Stop {}, fullTime {}", stop, fulllTime);

    }*/
    public void simulateDay(int periodLive) {
        try (var scheduledExecutorService = Executors.newScheduledThreadPool(coreCount)) {


            notifyView();
            long start = System.currentTimeMillis();
            LOGGER.info("Start {}", start);
            for (int i = 0; i < periodLive; i++) {

                ecoSystem.act();
                notifyView();
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            long stop = System.currentTimeMillis();
            long fulllTime = stop - start;
            LOGGER.info("Stop {}, fullTime {}", stop, fulllTime);
        }
    }

    private void notifyView() {
        view.displayData(ecoSystem);
    }


}
