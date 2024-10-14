package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.map.Cell;

public class AnimalDecrementWeightService extends Thread {

    public static int deadCounter = 0;

    public synchronized void decrementWeight() {
        for (Cell[] row : EntityService.gameMap.getCells()) {
            for (Cell cell : row) {
                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Animal) {
                        organism.setWeight(organism.getWeight() - organism.getWeight() / 100 * 1);
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
