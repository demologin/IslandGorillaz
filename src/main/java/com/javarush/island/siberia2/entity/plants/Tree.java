package com.javarush.island.siberia2.entity.plants;

import com.javarush.island.siberia2.config.PlantSettings;

public class Tree extends Plant {
    public Tree(PlantSettings settings) {
        super(settings);
    }

    @Override
    public void grow() {
        lock.lock();
        try {
            weight += settings.getGrowthRate();
            if (weight > settings.getWeight()) {
                weight = settings.getWeight();
            }
        } finally {
            lock.unlock();
        }
    }

}