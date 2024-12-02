package com.javarush.island.siberia2.entity.animals;

import com.javarush.island.siberia2.config.AnimalSettings;
import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.entity.animals.carnivores.*;
import com.javarush.island.siberia2.entity.animals.herbivores.*;

public class AnimalFactory {
    public static Animal createAnimal(String name, AnimalSettings animalSettings) {
        return switch (name) {
            case "Wolf" -> new Wolf(animalSettings);
            case "Boa" -> new Boa(animalSettings);
            case "Fox" -> new Fox(animalSettings);
            case "Bear" -> new Bear(animalSettings);
            case "Eagle" -> new Eagle(animalSettings);
            case "Horse" -> new Horse(animalSettings);
            case "Deer" -> new Deer(animalSettings);
            case "Rabbit" -> new Rabbit(animalSettings);
            case "Mouse" -> new Mouse(animalSettings);
            case "Goat" -> new Goat(animalSettings);
            case "Sheep" -> new Sheep(animalSettings);
            case "Boar" -> new Boar(animalSettings);
            case "Buffalo" -> new Buffalo(animalSettings);
            case "Duck" -> new Duck(animalSettings);
            case "Caterpillar" -> new Caterpillar(animalSettings);
            default -> throw new IllegalArgumentException(Constants.FACTORY_UNKNOWN_ORGANISM + name);
        };
    }
}