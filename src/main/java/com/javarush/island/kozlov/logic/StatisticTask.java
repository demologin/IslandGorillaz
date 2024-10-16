package com.javarush.island.kozlov.logic;

import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.HashMap;
import java.util.Map;

public class StatisticTask implements Runnable{
    private final Island island;

    public StatisticTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        printIslandStatistics();
    }

    private void printIslandStatistics() {
        Map<String, Integer> animalCounts = new HashMap<>();
        int totalPlants = 0;

        for (Location[] row : island.getLocations()) {
            for (Location loc : row) {
                for (Animal animal : loc.getAnimals()) {
                    String animalName = animal.getClass().getSimpleName();
                    animalCounts.put(animalName, animalCounts.getOrDefault(animalName, 0) + 1);
                }

                totalPlants += loc.getVegetations().size();
            }
        }

        System.out.println("=== Island Statistics ===");
        System.out.println("Animal Population");
        for (Map.Entry<String, Integer> entry : animalCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Total Plants: " + totalPlants);
        System.out.println("====================");
    }
}
