package com.javarush.island.kozlov.entities.animals.herbivores;


import com.javarush.island.kozlov.entities.animals.Animal;

import com.javarush.island.kozlov.entities.plants.Vegetation;
import com.javarush.island.kozlov.logic.ProbabilityTable;

import com.javarush.island.kozlov.map.Location;

import java.util.List;

public class Duck extends Animal{

    public Duck() {
        super(1, 200, 4, 0.15);
    }

    @Override
    public void eat(Location location, Animal predator) {
        boolean hasEaten = false;

        List<Animal> animals = location.getAnimals();
        for (Animal prey : animals) {
            if (prey instanceof Worm) {
                boolean success = ProbabilityTable.tryToEat(predator.getClass(), prey.getClass());
                if (success) {
                    predator.foodNeed = Math.max(predator.foodNeed - prey.weight, 0);
                    location.removeAnimal(prey);
                    System.out.println("Duck eats " + prey.getClass().getSimpleName());
                    hasEaten = true;
                    if (predator.foodNeed == 0) {
                        System.out.println("Duck is full");
                        return;
                    }
                }
            }
        }

        if (!hasEaten) {
            List<Vegetation> plants = location.getVegetations();
            if (!plants.isEmpty()) {
                Vegetation plant = plants.remove(0);
                predator.foodNeed = Math.max(predator.foodNeed - plant.getNutritionalValue(), 0);
                System.out.println("Duck eats vegetation");
                if (predator.foodNeed == 0) {
                    System.out.println("Duck is full");
                }
            } else {
                System.out.println("No plants available for Duck to eat");
            }
        }


        if (predator.foodNeed > 0) {
            System.out.println("Duck is still hungry");
        }
    }



}
