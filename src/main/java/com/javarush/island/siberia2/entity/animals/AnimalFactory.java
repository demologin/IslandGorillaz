package com.javarush.island.siberia2.entity.animals;

import com.javarush.island.siberia2.config.AnimalSettings;
import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.entity.animals.carnivores.*;
import com.javarush.island.siberia2.entity.animals.herbivores.*;

public class AnimalFactory {
    public static Animal createAnimal(String name, AnimalSettings animalSettings) {
        switch (name) {
            case "Wolf": return new Wolf(animalSettings);
            case "Boa": return new Boa(animalSettings);
            case "Fox": return new Fox(animalSettings);
            case "Bear": return new Bear(animalSettings);
            case "Eagle": return new Eagle(animalSettings);
            case "Horse" : return new Horse(animalSettings);
            case "Deer" : return new Deer(animalSettings);
            case "Rabbit" : return new Rabbit(animalSettings);
            case "Mouse" : return new Mouse(animalSettings);
            case "Goat" : return new Goat(animalSettings);
            case "Sheep" : return new Sheep(animalSettings);
            case "Boar" : return new Boar(animalSettings);
            case "Buffalo" : return new Buffalo(animalSettings);
            case "Duck" : return new Duck(animalSettings);
            case "Caterpillar" : return new Caterpillar(animalSettings);
            default: throw new IllegalArgumentException(Constants.FACTORY_UNKNOWN_ANIMAL + name);
        }
    }
}