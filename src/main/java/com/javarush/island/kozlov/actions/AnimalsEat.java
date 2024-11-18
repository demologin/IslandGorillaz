package com.javarush.island.kozlov.actions;

import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.plants.Vegetation;
import com.javarush.island.kozlov.map.Location;

import java.util.List;


public interface AnimalsEat {
    int MAX_CYCLES_WITHOUT_FOOD = 3;  // Максимальное количество циклов без еды

    default void eat(Location location) {
        if (this instanceof Animal) {
            Animal animal = (Animal) this;
            List<Vegetation> plants = location.getPlants(); // Пример для травоядных животных
            if (!plants.isEmpty()) {
                Vegetation plant = plants.remove(0);
                animal.setFoodNeed(animal.getFoodNeed() - plant.getNutritionalValue());
                System.out.println(animal.getClass().getSimpleName() + " eats vegetation.");
                animal.resetHungerCycles();
            } else {
                animal.increaseHungerCycles();
                if (animal.getHungerCycles() >= MAX_CYCLES_WITHOUT_FOOD) {
                    System.out.println(animal.getClass().getSimpleName() + " has died from hunger.");
                    location.removeAnimal(animal);
                } else {
                    System.out.println(animal.getClass().getSimpleName() + " is still hungry.");
                }
            }
        }
    }
}
