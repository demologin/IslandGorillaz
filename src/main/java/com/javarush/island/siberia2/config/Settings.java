package com.javarush.island.siberia2.config;

import lombok.Data;
import java.util.Map;

@Data
public class Settings {
    private IslandSettings islandSettings;
    private Map<String, AnimalSettings> animalsSettings;
    private Map<String, PlantSettings> plantsSettings;
}