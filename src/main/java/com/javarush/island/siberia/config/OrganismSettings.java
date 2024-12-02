package com.javarush.island.siberia.config;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

/**
 * The OrganismSettings class represents the configuration settings for each organism species,
 * including parameters such as weight, maximum population per cell, speed, food capacity, and more.
 * These settings are loaded from the configuration file for each species.
 */

@Getter
@Setter
public class OrganismSettings {
    private double weight;
    private int maxCountPerCell;
    private int speed;
    private double maxFood;
    private int initialCount;
    private int maxOffspring;
    private double growthRate;
    private double reproductionSatietyThreshold;
    private String icon;


    public OrganismSettings(Map<String, Object> settingsMap) {
        this.weight = ((Number) settingsMap.get("weight")).doubleValue();
        this.maxCountPerCell = (int) settingsMap.get("maxCountPerCell");
        this.speed = settingsMap.containsKey("speed") ? (int) settingsMap.get("speed") : 0;
        this.maxFood = settingsMap.containsKey("maxFood") ? ((Number) settingsMap.get("maxFood")).doubleValue() : 0;
        this.initialCount = (int) settingsMap.get("initialCount");
        this.maxOffspring = (int) settingsMap.get("maxOffspring");
        this.growthRate = settingsMap.containsKey("growthRate") ? ((Number) settingsMap.get("growthRate")).doubleValue() : 0;
        this.reproductionSatietyThreshold = settingsMap.containsKey("reproductionSatietyThreshold") ?
                ((Number) settingsMap.get("reproductionSatietyThreshold")).doubleValue() : 0.5;
        this.icon = (String) settingsMap.get("icon");
    }
}