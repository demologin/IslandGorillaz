package com.javarush.island.siberia.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

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
    private String icon;


    public OrganismSettings(Map<String, Object> settingsMap) {
        this.weight = ((Number) settingsMap.get("weight")).doubleValue();
        this.maxCountPerCell = (int) settingsMap.get("maxCountPerCell");
        this.speed = settingsMap.containsKey("speed") ? (int) settingsMap.get("speed") : 0;
        this.maxFood = settingsMap.containsKey("maxFood") ? ((Number) settingsMap.get("maxFood")).doubleValue() : 0;
        this.initialCount = (int) settingsMap.get("initialCount");
        this.maxOffspring = (int) settingsMap.get("maxOffspring");
        this.growthRate = settingsMap.containsKey("growthRate") ? ((Number) settingsMap.get("growthRate")).doubleValue() : 0;
        this.icon = (String) settingsMap.get("icon");
    }
}