package com.javarush.island.siberia2.services;

import com.javarush.island.siberia2.entity.animals.Animal;

public class ReproductionService {

    public void reproduce(Animal animal) {
        if (animal.getCurrentFoodLevel() < animal.getSettings().getMaxFood()) {
            return;
        }

        animal.getLock().lock();
        try {
            long partners = animal.getCurrentCell().getAnimals().stream()
                    .filter(a -> a.getClass() == animal.getClass() && a != animal)
                    .count();
            if (partners > 0
                    && animal.getCurrentCell().getAnimals().size()
                    < animal.getSettings().getMaxCountPerCell()) {
                try {
                    Animal offspring = animal.clone();
                    offspring.setCurrentFoodLevel(offspring.getSettings().getMaxFood() / 2);
                    offspring.setCurrentCell(animal.getCurrentCell());
                    animal.getCurrentCell().addAnimal(offspring);
                    animal.setCurrentFoodLevel(animal.getCurrentFoodLevel()
                            - animal.getSettings().getMaxFood() / 2);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            animal.getLock().unlock();
        }
    }
}