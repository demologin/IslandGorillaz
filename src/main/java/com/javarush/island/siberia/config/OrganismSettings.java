package com.javarush.island.siberia.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganismSettings {
    private double weight;
    private int maxCountPerCell;
    private int speed;
    private double maxFood;
    private int initialCount;
    private int maxOffspring;
    private String icon;
}