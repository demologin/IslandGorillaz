package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.predators.Bear;
import com.javarush.island.gerasimov.entity.creatures.predators.Eagle;
import com.javarush.island.gerasimov.entity.creatures.predators.Fox;
import com.javarush.island.gerasimov.entity.creatures.predators.Predator;
import com.javarush.island.gerasimov.entity.map.Cell;

public class PredatorsDecrementWeight extends Thread {

    public static int deadCounter = 0;

    public synchronized void decrementWeight() {
        for (Cell[] row : EntityCreator.gameMap.getCells()) {
            for (Cell cell : row) {
                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Predator &&
                            !(organism instanceof Fox) &&
                            !(organism instanceof Eagle) &&
                            !(organism instanceof Bear)) {
                        organism.setWeight(organism.getWeight() - 0.0005);
                        if (organism.getWeight() <= 0) {
                            deadCounter++;
                            cell.getOrganisms().remove(organism);
                        }
                    }
                    else if (organism instanceof Bear) {
                        organism.setWeight(organism.getWeight() - 0.009);
                        if (organism.getWeight() <= 0) {
                            deadCounter++;
                            cell.getOrganisms().remove(organism);
                        }
                    }else if ((organism instanceof Fox) || (organism instanceof Eagle)) {
                        organism.setWeight(organism.getWeight() - 0.00005);
                        if (organism.getWeight() <= 0) {
                            deadCounter++;
                            cell.getOrganisms().remove(organism);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        decrementWeight();
    }
}
