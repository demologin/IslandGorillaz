package com.javarush.island.gerasimov;

//import com.javarush.island.gerasimov.entity.Game;



import com.javarush.island.gerasimov.service.*;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Runner {


    public static void main(String[] args) throws InterruptedException {

        EntityService entityService = new EntityService();
        entityService.start();
        entityService.join();

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(4);

        StartLife startLife = new StartLife();
        CounterService counterService = new CounterService();
        PlantIncrementWeightService plantIncrementWeightService = new PlantIncrementWeightService();
        AnimalDecrementWeightService animalDecrementWeightService = new AnimalDecrementWeightService();



        threadPool.scheduleAtFixedRate(startLife,  2000,1, TimeUnit.MILLISECONDS);
        threadPool.scheduleAtFixedRate(plantIncrementWeightService,  1,1, TimeUnit.MILLISECONDS);
        threadPool.scheduleAtFixedRate(counterService,  1,2, TimeUnit.SECONDS);
        threadPool.scheduleAtFixedRate(animalDecrementWeightService,  1,1, TimeUnit.MICROSECONDS);


    }
}
