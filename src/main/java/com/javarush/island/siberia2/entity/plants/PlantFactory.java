package com.javarush.island.siberia2.entity.plants;

import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.config.PlantSettings;

public class PlantFactory {

    public static Plant createPlant(String name, PlantSettings plantSettings) {
        switch (name) {
            case "Grass": return new Grass(plantSettings);
            case "Tree": return new Tree(plantSettings);
            default: throw new IllegalArgumentException(Constants.FACTORY_UNKNOWN_ORGANISM  + name);
        }
    }
}