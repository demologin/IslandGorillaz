package com.javarush.island.siberia2.entity.plants;

import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.config.PlantSettings;

public class PlantFactory {

    public static Plant createPlant(String name, PlantSettings plantSettings) {
        return switch (name) {
            case "Grass" -> new Grass(plantSettings);
            case "Tree" -> new Tree(plantSettings);
            default -> throw new IllegalArgumentException(Constants.FACTORY_UNKNOWN_ORGANISM + name);
        };
    }
}