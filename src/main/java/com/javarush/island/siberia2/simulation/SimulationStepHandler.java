package com.javarush.island.siberia2.simulation;

import com.javarush.island.siberia2.entity.Organism;
import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.map.Cell;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;

public class SimulationStepHandler {

    private final AtomicInteger bornCount;
    private final AtomicInteger eatenCount;
    private final AtomicInteger starvedCount;

    public SimulationStepHandler(AtomicInteger bornCount, AtomicInteger eatenCount, AtomicInteger starvedCount) {
        this.bornCount = bornCount;
        this.eatenCount = eatenCount;
        this.starvedCount = starvedCount;
    }

    public void processCell(Cell cell) {
        List<Organism> organisms = new ArrayList<>();
        organisms.addAll(cell.getAnimals());
        organisms.addAll(cell.getPlants());

        for (Organism organism : organisms) {
            if (organism instanceof Animal animal) {
                double previousFoodLevel = animal.getCurrentFoodLevel();
                animal.liveCycle();
                if (animal.getCurrentFoodLevel() <= 0) {
                    starvedCount.incrementAndGet();
                }
                if (cell.getAnimals().size() > organisms.size()) {
                    bornCount.incrementAndGet();
                }
                if (animal.getCurrentFoodLevel() > previousFoodLevel) {
                    eatenCount.incrementAndGet();
                }
            } else if (organism instanceof com.javarush.island.siberia2.entity.plants.Plant plant) {
                plant.liveCycle();
            }
        }
    }

}