package com.javarush.island.siberia2.services;

import com.javarush.island.siberia2.util.RandomUtils;
import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.map.Cell;
import java.util.List;

public class EatingService {

    public void eat(Animal animal) {
        if (animal.getCurrentFoodLevel() >= animal.getSettings().getMaxFood()) {
            return;
        }

        boolean hasEaten = false;

        animal.getLock().lock();
        try {
            Cell currentCell = animal.getCurrentCell();
            List<Animal> animalsInCell = currentCell.getAnimals();

            for (Animal prey : animalsInCell) {
                if (animal == prey) continue;

                Integer probability = animal.getSettings().getEatProbability().get(prey.getSettings().getName());
                if (probability != null && RandomUtils.chance(probability)) {
                    double preyWeight = prey.getSettings().getWeight();
                    double foodNeeded = animal.getSettings().getMaxFood() - animal.getCurrentFoodLevel();
                    double foodConsumed = Math.min(preyWeight, foodNeeded);

                    animal.setCurrentFoodLevel(animal.getCurrentFoodLevel() + foodConsumed);
                    prey.die(); //die from overEating =D
                    hasEaten = true;
                    break;
                }
            }

            if (!hasEaten) {
                animal.decreaseFoodLevel();
            }
        } finally {
            animal.getLock().unlock();
        }
    }
}