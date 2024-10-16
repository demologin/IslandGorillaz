package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.map.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class PlantsControlWeight extends  Thread{

    public static int deadCounter = 0;

    public synchronized void controlWeight(){
        for (Cell[] row : EntityCreator.gameMap.getCells()) {
            for (Cell cell : row) {
                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Plant) {
                        ThreadLocalRandom random = ThreadLocalRandom.current();
                        int ran = random.nextInt(2);
                        if (ran == 0 && organism.getWeight() < 1.1) {
                            organism.setWeight(organism.getWeight() + 0.01);
                        } else {
                            organism.setWeight(organism.getWeight() - 0.01);
                            if (organism.getWeight() <= 0.5) {
                                cell.getOrganisms().remove(organism);
                                deadCounter++;
                            }
                        }
                    }

                }
            }
        }
    }

    @Override
    public void run() {
        controlWeight();
    }
}
