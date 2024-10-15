package com.javarush.island.siberia2.entity.plants;

import com.javarush.island.siberia2.config.PlantSettings;

public class Tree extends Plant {
    public Tree(PlantSettings settings) {
        super(settings);
    }

    @Override
    public void grow() {
        weight += 0.05;
    }

}