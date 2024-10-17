package com.javarush.island.kozlov.logic;

import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.HashMap;
import java.util.Map;

public class StatisticTask implements Runnable{

    private final Island island;
    private int vegetationsGrown = 0;
    private int vegetationsEaten = 0;
    private final Map<String, Integer> totalAnimals = new HashMap<>();
    private final Map<String, Integer> eatenAnimals = new HashMap<>();
    private final Map<String, Integer> bornAnimals = new HashMap<>();

    public StatisticTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        collectStatistics();
        printStatistics();
    }

    private void collectStatistics() {
        totalAnimals.clear();
        eatenAnimals.clear();
        bornAnimals.clear();
        vegetationsEaten = 0;
        vegetationsGrown = 0;

        for (Location[] row : island.getLocations()) {
            for (Location loc : row) {
                vegetationsGrown += loc.getVegetationsGrown();
                vegetationsEaten += loc.getVegetationsEaten();

                for (Animal animal : loc.getAnimals()) {
                    String animalName = animal.getClass().getSimpleName();
                    totalAnimals.put(animalName, totalAnimals.getOrDefault(animalName, 0) + 1);

                    if (animal.isBornRecently()) {
                        bornAnimals.put(animalName, bornAnimals.getOrDefault(animalName, 0) + 1);
                    }

                    if (animal.isEatenRecently()) {
                        eatenAnimals.put(animalName, eatenAnimals.getOrDefault(animalName, 0) + 1);
                    }
                }
            }
        }
    }

    private void printStatistics() {
        System.out.println("=== Island Statistics ===");


        System.out.println("Plants grown: " + vegetationsGrown);
        System.out.println("Plants eaten: " + vegetationsEaten);


        System.out.println("Total Animals on the Island:");
        totalAnimals.forEach((animal, count) ->
                System.out.println(animal + ": " + count)
        );

        System.out.println("Animals eaten:");
        eatenAnimals.forEach((animal, count) ->
                System.out.println(animal + ": " + count)
        );

        System.out.println("Animals born:");
        bornAnimals.forEach((animal, count) ->
                System.out.println(animal + ": " + count)
        );

        System.out.println("=========================");
    }
}
