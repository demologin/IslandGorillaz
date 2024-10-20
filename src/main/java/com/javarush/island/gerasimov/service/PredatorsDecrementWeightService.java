package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.constants.Constants;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.predators.Predator;
import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.repository.EntityCreator;

public class PredatorsDecrementWeightService extends Thread {

    public static int deadCounter = 0;

    @Override
    public void run() {
        decrementWeight();
    }

    public synchronized void decrementWeight() {
        for (Cell[] row : EntityCreator.gameMap.getCells()) {
            for (Cell cell : row) {
                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Predator) {
                        organism.setWeight(organism.getWeight() - organism.getWeight() * Constants.PERCENT_DECREMENT_WEIGHT_FOR_ANIMAL);
                        if (organism.getWeight() < organism.getCriticalWeight()) {
                            cell.getOrganisms().remove(organism);
                            deadCounter++;
                        }
                    }
                }
            }
        }
    }
}
