package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.herbivores.*;
import com.javarush.island.gerasimov.entity.map.Cell;

public class HerbivoresDecrementWeight extends Thread{

    public static int deadCounter = 0;

    public synchronized void decrementWeight() {
        for (Cell[] row : EntityCreator.gameMap.getCells()) {
            for (Cell cell : row) {
                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Herbivore &&
                            !(organism instanceof Mouse) &&
                            !(organism instanceof Caterpillar) &&
                            !(organism instanceof Duck) &&
                            !(organism instanceof Rabbit)) {
                        organism.setWeight(organism.getWeight() - 0.0005);
                        if (organism.getWeight() <= 0) {
                            cell.getOrganisms().remove(organism);
                            deadCounter++;
                        }
                    } else if (organism instanceof Duck || organism instanceof Rabbit) {
                        organism.setWeight(organism.getWeight() - 0.00005);
                        if (organism.getWeight() <= 0) {
                            cell.getOrganisms().remove(organism);
                            deadCounter++;
                        }
                    } else if (organism instanceof Mouse) {
                        organism.setWeight(organism.getWeight() - 0.000005);
                        if (organism.getWeight() <= 0) {
                            cell.getOrganisms().remove(organism);
                            deadCounter++;
                        }
                    }
                    else if (organism instanceof Caterpillar) {
                        organism.setWeight(organism.getWeight() - 0.00000005);
                        if (organism.getWeight() <= 0) {
                            cell.getOrganisms().remove(organism);
                            deadCounter++;
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