package com.javarush.island.siberia2.config;

import lombok.Data;

@Data
public class PlantSettings {
    private String name;
    private String icon;
    private String imgPath;
    private double weight;
    private int maxCountPerCell;
    private double growthRate;
}