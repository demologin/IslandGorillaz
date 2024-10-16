package com.javarush.island.kozlov;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.exception.MoveException;
import com.javarush.island.kozlov.exception.ReproductionException;
import com.javarush.island.kozlov.logic.AnimalLifecycleTask;
import com.javarush.island.kozlov.logic.PlantGrowthTask;
import com.javarush.island.kozlov.logic.StatisticTask;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) {
        Island island = new Island(100, 20);

        ScheduledExecutorService sheduler = Executors.newScheduledThreadPool(5);
        sheduler.scheduleAtFixedRate(new PlantGrowthTask(island), 0, 3, TimeUnit.SECONDS);

        sheduler.scheduleAtFixedRate(() -> {
            for (Location[] row : island.getLocations()) {
                for (Location loc : row) {
                    try {
                        loc.reproduceAnimals();
                    } catch (ReproductionException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, 0, 3, TimeUnit.SECONDS);

        sheduler.scheduleAtFixedRate(new StatisticTask(island), 0, 3, TimeUnit.SECONDS);

        sheduler.scheduleAtFixedRate(() -> {
            for (Location[] row : island.getLocations()) {
                for (Location loc : row) {
                    for (Animal animal : loc.getAnimals()) {
                        try {
                            animal.move(loc, island);
                        } catch (MoveException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }, 0, 3, TimeUnit.SECONDS);

        sheduler.scheduleAtFixedRate(() -> {
            for (Location[] row : island.getLocations()) {
                for (Location loc : row) {
                    for (Animal animal : loc.getAnimals()) {
                        animal.eat(loc, animal);
                    }
                }
            }
        }, 0, 3, TimeUnit.SECONDS);


        ExecutorService animalPool = Executors.newFixedThreadPool(10);
        sheduler.scheduleAtFixedRate(() -> {
            for (Location[] row : island.getLocations()) {
                for (Location loc : row) {
                    for (Animal animal : loc.getAnimals()) {
                        animalPool.submit(new AnimalLifecycleTask(animal, loc, island));
                    }
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
    }
}
