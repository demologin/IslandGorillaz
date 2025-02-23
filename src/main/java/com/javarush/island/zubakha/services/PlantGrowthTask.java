package com.javarush.island.zubakha.services;

import com.javarush.island.zubakha.entity.map.IslandSimulation;

import java.util.Random;

public class PlantGrowthTask implements Runnable {
    @Override
    public void run() {
        Random random = new Random();
        int countPlants = random.nextInt(100);
        if (IslandSimulation.
                getInstance().
                getTimeNow() >= 2) {
            IslandSimulation.
                    getInstance().
                    placePlants(countPlants / 2);
        } else {
            IslandSimulation.
                    getInstance().
                    placePlants(countPlants);
        }
    }
}
