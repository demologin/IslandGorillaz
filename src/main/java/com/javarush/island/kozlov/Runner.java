package com.javarush.island.kozlov;

import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.exception.MoveException;
import com.javarush.island.kozlov.logic.PlantGrowthTask;
import com.javarush.island.kozlov.logic.StatisticTask;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) {
        Island island = new Island(100, 20);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
        scheduler.scheduleAtFixedRate(new PlantGrowthTask(island), 0, 3, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(new StatisticTask(island), 0, 3, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            for (Location[] row : island.getLocations()) {
                for (Location loc : row) {
                    for (Animal animal : loc.getAnimals()) {
                        if (animal != null) {
                            System.out.println("Animal " + animal.getClass().getSimpleName() + " is moving.");
                            try {
                                animal.move(loc, island);
                            } catch (MoveException e) {
                                System.out.println("Move failed for " + animal.getClass().getSimpleName());
                            }
                        }
                    }
                }
            }
        }, 0, 3, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            for (Location[] row : island.getLocations()) {
                for (Location loc : row) {
                    for (Animal animal : loc.getAnimals()) {
                        System.out.println("Animal " + animal.getClass().getSimpleName() + " is eating.");
                        animal.eat(loc);
                    }
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
    }
}
