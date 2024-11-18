package com.javarush.island.kozlov.logic;

import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.HashMap;
import java.util.Map;

public class StatisticTask implements Runnable {
    private final Island island;

    public StatisticTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        int totalPlantsGrown = 0;
        int totalPlantsEaten = 0;
        Map<String, Integer> totalAnimals = new HashMap<>();
        Map<String, Integer> eatenAnimals = new HashMap<>();
        Map<String, Integer> bornAnimals = new HashMap<>();

        for (Location[] row : island.getLocations()) {
            for (Location loc : row) {
                totalPlantsGrown += loc.getPlantsGrown();
                totalPlantsEaten += loc.getPlantsEaten();

                for (Animal animal : loc.getAnimals()) {
                    String name = animal.getClass().getSimpleName();
                    totalAnimals.put(name, totalAnimals.getOrDefault(name, 0) + 1);

                    if (animal.isBornRecently()) {
                        bornAnimals.put(name, bornAnimals.getOrDefault(name, 0) + 1);
                    }

                    if (animal.isEatenRecently()) {
                        eatenAnimals.put(name, eatenAnimals.getOrDefault(name, 0) + 1);
                    }
                }
            }
        }

        // Вывод статистики
        System.out.println("Plants grown: " + totalPlantsGrown);
        System.out.println("Plants eaten: " + totalPlantsEaten);
        System.out.println("Animals: " + totalAnimals);
        System.out.println("Eaten animals: " + eatenAnimals);
        System.out.println("Born animals: " + bornAnimals);

    }
}
