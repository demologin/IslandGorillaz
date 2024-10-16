package com.javarush.island.kozlov.logic;

import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.concurrent.Callable;

public class PlantGrowthTask implements Runnable {
    private final Island island;

    public PlantGrowthTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        for (Location[] row : island.getLocations()) {
            for (Location loc : row) {
                synchronized (loc) {
                    loc.growVegetations();
                }
            }
        }
    }
}
