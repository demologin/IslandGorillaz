package com.javarush.island.siberia2.services;

import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import java.util.HashMap;
import java.util.Map;

public class StatisticsService {
    private final Island island;
    private int cycleCounter = 0;

    public StatisticsService(Island island) {
        this.island = island;
    }

    public void printStatistics(int bornCount, int eatenCount, int starvedCount) {
        int totalOrganisms = 0;
        int totalPlants = 0;

        Map<String, Integer> animalCountMap = new HashMap<>();

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = island.getCell(x, y);
                totalOrganisms += cell.getAnimals().size();
                totalPlants += cell.getPlants().size();

                for (Animal animal : cell.getAnimals()) {
                    String animalName = animal.getSettings().getName();
                    animalCountMap.put(animalName, animalCountMap.getOrDefault(animalName, 0) + 1);
                }
            }
        }

        System.out.println("*** Неделя на острове: " + (++cycleCounter) + " ***");
        System.out.println("Всего организмов на острове: " + totalOrganisms + " животных и " + totalPlants + " растений.");
        System.out.println("Родилось животных: " + bornCount);
        System.out.println("Съедено животных: " + eatenCount);
        System.out.println("Умерло от голода: " + starvedCount);
        animalCountMap.forEach((name, count) -> System.out.println(name + " = " + count));
        System.out.println("________________________________________________________________________________");
    }

}