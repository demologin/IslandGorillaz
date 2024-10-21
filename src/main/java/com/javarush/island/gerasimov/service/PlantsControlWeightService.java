package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.constants.Constants;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.repository.EntityCreator;

import java.util.concurrent.ThreadLocalRandom;

public class PlantsControlWeightService extends Thread {

    public static int deadCounter = 0;

    @Override
    public void run() {
        controlWeight();
    }

    public void controlWeight() {
        for (Cell[] row : EntityCreator.gameMap.getCells()) {
            for (Cell cell : row) {
                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Plant) {
                        ThreadLocalRandom random = ThreadLocalRandom.current();
                        int ran = random.nextInt(2);
                        if (ran == 0 && organism.getWeight() < organism.getMaxWeight()) {
                            organism.setWeight(organism.getWeight() + Constants.PERCENT_INCREMENT_WEIGHT_FOR_PLANT);
                        } else {
                            organism.setWeight(organism.getWeight() - Constants.PERCENT_DECREMENT_WEIGHT_FOR_PLANT);
                            if (organism.getWeight() <= organism.getCriticalWeight()) {
                                cell.getOrganisms().remove(organism);
                                deadCounter++;
                            }
                        }
                    }
                }
            }
        }
    }
}
