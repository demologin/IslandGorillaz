package com.javarush.island.kozlov.actions;

import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.logic.ProbabilityTable;
import com.javarush.island.kozlov.map.Location;

import java.util.List;

public interface AnimalsEat {

    default void eat(Location location, Animal predator) {

        List<Animal> animals = location.getAnimals();

        for (Animal prey : animals) {
            if (prey == predator) continue;

            boolean success = ProbabilityTable.tryToEat(predator.getClass(), prey.getClass());

            if (success) {
                System.out.println(predator.getClass().getSimpleName() + " eats " + prey.getClass().getSimpleName());
                predator.foodNeed = Math.max(predator.foodNeed - prey.weight, 0);
                location.removeAnimal(prey);
                if (predator.foodNeed == 0) {
                    System.out.println(predator.getClass().getSimpleName() + " is full ");
                    break;
                }
            }
        }
        if (predator.foodNeed > 0) {
            System.out.println(predator.getClass().getSimpleName() + " is still hungry");
        }
    }
}
