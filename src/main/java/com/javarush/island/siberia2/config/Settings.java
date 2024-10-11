package com.javarush.island.siberia2.config;

import lombok.Data;
import java.util.Map;

@Data
public class Settings {
    private IslandSettings island;
    private Map<String, AnimalSettings> animals;
    private Map<String, PlantSettings> plants;
}