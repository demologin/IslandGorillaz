package com.javarush.island.siberia2.config;

import lombok.Data;
import java.util.Map;

@Data
public class AnimalSettings {
    private String name;
    private String icon;
    private double weight;
    private int maxCountPerCell;
    private int speed;
    private double maxFood;
    private Map<String, Integer> eatProbability;
}