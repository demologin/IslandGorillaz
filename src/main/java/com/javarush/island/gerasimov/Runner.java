package com.javarush.island.gerasimov;

import com.javarush.island.gerasimov.service.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Runner {

    public static ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(4);

    public static void main(String[] args) throws InterruptedException {

        EntityCreator entityCreator = new EntityCreator();
        entityCreator.start();
        entityCreator.join();

        StartLife startLife = new StartLife();
        Counter counter = new Counter();
        PredatorsDecrementWeight predatorsDecrementWeight = new PredatorsDecrementWeight();
        HerbivoresDecrementWeight herbivoresDecrementWeight = new HerbivoresDecrementWeight();
        PlantsControlWeight plantsControlWeight = new PlantsControlWeight();

        threadPool.scheduleAtFixedRate(startLife, 2000, 1, TimeUnit.MICROSECONDS);
        threadPool.scheduleAtFixedRate(counter, 1, 2, TimeUnit.SECONDS);
        threadPool.scheduleAtFixedRate(predatorsDecrementWeight, 1, 1, TimeUnit.MICROSECONDS);
        threadPool.scheduleAtFixedRate(herbivoresDecrementWeight, 1, 1, TimeUnit.MICROSECONDS);
        threadPool.scheduleAtFixedRate(plantsControlWeight, 1, 1, TimeUnit.MILLISECONDS);
    }
}
