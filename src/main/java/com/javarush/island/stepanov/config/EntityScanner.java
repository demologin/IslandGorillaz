package com.javarush.island.stepanov.config;

import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.services.AnimalService;
import com.javarush.island.stepanov.services.PlantService;

public class EntityScanner {
    private EntityScanner() {
    }

    public static Organism [] createPrototypes() {
        AnimalService animal = new AnimalService();
        AnimalService[] prototypes = new AnimalService[1];
        prototypes[0] = animal;
        return prototypes;
    }

    public static Organism generatePrototype(String name) {

        return null;
    }
}
