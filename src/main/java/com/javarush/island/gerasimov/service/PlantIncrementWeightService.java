package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.map.Cell;

public class PlantIncrementWeightService extends Thread {

    public synchronized void grassReproduce() {
        for (Cell[] row : EntityService.gameMap.getCells()) {
            for (Cell cell : row) {
                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Plant) {
                        if (organism.getWeight() < 1.5) {
                            organism.setWeight(organism.getWeight() + 0.1);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        grassReproduce();
    }
}

