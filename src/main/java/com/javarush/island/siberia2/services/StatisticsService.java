package com.javarush.island.siberia2.services;

import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import java.util.HashMap;
import java.util.Map;

public class StatisticsService {
    private final Island island;
    private int cycleCounter = 0;
    private final Printer printer;

    public StatisticsService(Island island, Printer printer) {
        this.island = island;
        this.printer = printer;
    }

    public void printStatistics(int bornCount, int eatenCount, int starvedCount) {
        int totalAnimals = 0;
        int totalPlants = 0;

        Map<String, Integer> animalCountMap = new HashMap<>();

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = island.getCell(x, y);
                totalAnimals += cell.getAnimals().size();
                totalPlants += cell.getPlants().size();

                for (Animal animal : cell.getAnimals()) {
                    String animalName = animal.getSettings().getName();
                    String animalIcon = animal.getSettings().getIcon();
                    String nameWithIcon = animalIcon + " " + animalName;
                    animalCountMap.put(nameWithIcon, animalCountMap.getOrDefault(nameWithIcon, 0) + 1);
                }
            }
        }

        StringBuilder statsOutput = new StringBuilder();
        statsOutput.append("*** Week on the island: ").append(++cycleCounter).append(" ***\n")
                .append("Total organisms on the island: ").append(totalAnimals)
                .append(" animals and ").append(totalPlants).append(" plants.\n")
                .append("Animals born: ").append(bornCount).append("\n")
                .append("Animals eaten: ").append(eatenCount).append("\n")
                .append("Animals starved: ").append(starvedCount).append("\n");

        animalCountMap.forEach((nameWithIcon, count) ->
                statsOutput.append(nameWithIcon).append(" = ").append(count).append("\n"));

        statsOutput.append("________________________________________________________________________________\n");

        printer.print(statsOutput.toString());
    }
}