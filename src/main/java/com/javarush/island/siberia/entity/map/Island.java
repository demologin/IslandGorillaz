package com.javarush.island.siberia.entity.map;

import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;


    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[width][height];

        initializeLocations();
    }

    public void initializeLocations() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                locations[x][y] = new Location(x, y);
            }
        }
    }

    public Location getLocation(int x, int y) {
        return locations[x][y];
    }

}