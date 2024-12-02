package com.javarush.island.siberia2.config;

import lombok.Data;

@Data
public class IslandSettings {
    private int width;
    private int height;
    private int simulationStepDuration;
}