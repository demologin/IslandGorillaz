package com.javarush.island.gerasimov.view;

import com.javarush.island.gerasimov.Runner;
import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.creatures.herbivores.*;
import com.javarush.island.gerasimov.entity.creatures.predators.*;
import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.repository.EntityCreator;
import com.javarush.island.gerasimov.service.HerbivoresDecrementWeightService;
import com.javarush.island.gerasimov.service.PlantsControlWeightService;
import com.javarush.island.gerasimov.service.PredatorsDecrementWeightService;

import java.util.HashMap;
import java.util.Map;

public class ConsoleView extends Thread {

    private int weekCounter = 0;
    private int sumOrganisms = 0;
    private Map<String, Integer> organismsMap = new HashMap<>();

    @Override
    public void run() {
        countingAllOrganism();
        consoleView();
    }

    private void countingAllOrganism() {
        for (Cell[] row : EntityCreator.cells) {
            for (Cell cell : row) {
                for (Organism organism : cell.getOrganisms()) {
                    if (!organismsMap.containsKey(organism.getName())) {
                        int organismCounter = 1;
                        organismsMap.put(organism.getName(), organismCounter);
                    } else {
                        organismsMap.put(organism.getName(), organismsMap.get(organism.getName()) + 1);
                    }
                }
            }
        }
    }

    private void consoleView() {
        for (Map.Entry<String, Integer> entry : organismsMap.entrySet()) {
            sumOrganisms += entry.getValue();
        }
        if (sumOrganisms == 0) {
            Runner.threadPool.shutdown();
        }
        weekCounter++;

        System.out.println("*** Week on the island: " + weekCounter + " ***" + "\n" +
                "Total organisms on the islands: " + sumOrganisms + ". Animals: " + (sumOrganisms - organismsMap.get("Grass")) + ", plants: " + organismsMap.get("Grass") + ".\n" +
                "Animals: " +
                "\uD83D\uDC17" + "=" + organismsMap.get("Boar") +
                " \uD83D\uDC3A" + "=" + organismsMap.get("Wolf") +
                " \uD83D\uDC0E" + "=" + organismsMap.get("Horse") +
                " \uD83D\uDC03" + "=" + organismsMap.get("Buffalo") +
                " \uD83D\uDC3B" + "=" + organismsMap.get("Bear") +
                " \uD83E\uDD85" + "=" + organismsMap.get("Eagle") +
                " \uD83E\uDD8A" + "=" + organismsMap.get("Fox") +
                " \uD83D\uDC0D" + "=" + organismsMap.get("Snake") +
                " \uD83D\uDC1B" + "=" + organismsMap.get("Caterpillar") +
                " \uD83E\uDD8C" + "=" + organismsMap.get("Deer") +
                " \uD83E\uDD86" + "=" + organismsMap.get("Duck") +
                " \uD83D\uDC10" + "=" + organismsMap.get("Goat") +
                " \uD83D\uDC2D" + "=" + organismsMap.get("Mouse") +
                " \uD83D\uDC07" + "=" + organismsMap.get("Rabbit") +
                " \uD83D\uDC11" + "=" + organismsMap.get("Sheep") + "\n" +
                "Plants: " + "\uD83C\uDF3F" + organismsMap.get("Grass") + "\n\n" +
                "-Plants grown: " + Plant.reproduceCounter + "\n" +
                "-Plants eaten: " + Herbivore.eatCounter + "\n" +
                "-Plants died naturally: " + PlantsControlWeightService.deadCounter + "\n" +
                "-Movement of animals to other locations: " + Animal.moveCounter + "\n" +
                "-Animals eaten: " + Predator.eatCounter + "\n" +
                "-Animals born: " + Animal.reproduceCounter + "\n" +
                "-Predators died of starvation: " + PredatorsDecrementWeightService.deadCounter + "\n" +
                "-Herbivores died of starvation: " + HerbivoresDecrementWeightService.deadCounter + "\n" +
                "____________________________________________________________________________________________________");

        organismsMap.clear();
        sumOrganisms = 0;
        Animal.moveCounter = 0;
        Animal.reproduceCounter = 0;
        Predator.eatCounter = 0;
        Plant.reproduceCounter = 0;
        PlantsControlWeightService.deadCounter = 0;
        Herbivore.eatCounter = 0;
        PredatorsDecrementWeightService.deadCounter = 0;
        HerbivoresDecrementWeightService.deadCounter = 0;
    }
}
